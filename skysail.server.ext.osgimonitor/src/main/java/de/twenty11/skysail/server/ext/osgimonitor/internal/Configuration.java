/**
 *  Copyright 2011 Carsten Gräf
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * 
 */

package de.twenty11.skysail.server.ext.osgimonitor.internal;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Enumeration;

import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.component.ComponentContext;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.VirtualHost;
import org.restlet.security.MapVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.config.ServerConfiguration;

public class Configuration implements ManagedService {

    private static Logger logger = LoggerFactory.getLogger(Configuration.class);
    private OsgiMonitorComponent restletComponent;
    private Server server;
    private ComponentContext context;
    private ConfigurationAdmin configadmin;
    private ServerConfiguration serverConfig;
    private ServiceRegistration registration;

    protected void activate(ComponentContext componentContext) throws ConfigurationException {
        logger.info("Activating Skysail Ext Osgimonitor Configuration Component");
        this.context = componentContext;

        logger.info("Configuring Skysail Ext Osgimonitor...");
        if (serverConfig.startComponent()) {
            String port = (String) serverConfig.getConfigForKey("port");
            logger.info("port was configured on {}", port);
            MapVerifier verifier = new MapVerifier();
            try {
                if (!setSecretVerifier(verifier)) {
                    logger.warn("not starting up the application due to encountered configuration problems.");
                    return;
                }
            } catch (Exception e) {
                logger.error("Configuring secretVerifier encountered a problem: {}", e.getMessage());
                e.printStackTrace();
                throw new ConfigurationException("secrets", "file not found", e);
            }
            logger.info("Starting standalone osgimonitor server on port {}", port);
            restletComponent = new OsgiMonitorComponent(this.context, verifier);
            startStandaloneServer(port);
        } else {
            VirtualHost virtualHost = createVirtualHost();
            if (componentContext.getBundleContext() != null) {
                this.registration = componentContext.getBundleContext().registerService(
                        "org.restlet.routing.VirtualHost", virtualHost, null);
            }
        }

    }

    private VirtualHost createVirtualHost() {
        OsgiMonitorViewerApplication application = new OsgiMonitorViewerApplication("/static",
                context.getBundleContext());

        VirtualHost vh = new VirtualHost();
        vh.attach(application);
        return vh;
    }

    protected void deactivate(ComponentContext ctxt) {
        logger.info("Deactivating Skysail Ext Osgimonitor Configuration Component");
        this.context = null;
        try {
            if (server != null) {
                server.stop();
            }
        } catch (Exception e) {
            logger.error("Exception when trying to stop standalone server", e);
        }
        if (restletComponent != null && restletComponent.getRegistration() != null) {
            restletComponent.getRegistration().unregister();
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public synchronized void updated(Dictionary properties) throws ConfigurationException {
        logger.info("Configuring Skysail Ext Osgimonitor...");
    }

    public synchronized void setConfigAdmin(ConfigurationAdmin configadmin) {
        logger.info("setting configadmin in OsgiMonitor Configuration");
        this.configadmin = configadmin;
    }

    public synchronized void setServerConfiguration(de.twenty11.skysail.server.config.ServerConfiguration serverConfig) {
        logger.info("setting configadmin in OsgiMonitor Configuration");
        this.serverConfig = serverConfig;
    }

    private boolean setSecretVerifier(MapVerifier verifier) throws IOException {
        org.osgi.service.cm.Configuration secrets;
        logger.info("gettings 'secrets' configuration...");
        if (configadmin == null) {
            logger.error("configadmin is not set, cannot proceed with configuration; no one will be able to log in!");
            return false;
        }
        secrets = configadmin.getConfiguration("secrets");
        if (secrets == null) {
            logger.error("could not find 'secrets' configuration; will try to use eclipse preference mechanism");
            if (settingEclipsePreferences(verifier)) {
                logger.info("found eclipse preference store");
                return true;
            }
            logger.error("could not find 'secrets' configuration; cannot proceed with configuration; no one will be able to log in!");
            return false;
        }
        Dictionary secretsProperties = secrets.getProperties();
        if (secretsProperties == null || secretsProperties.keys() == null) {
            logger.error("secretProperties is null or empty; no one will be able to log in!");
            return false;
        }
        Enumeration keys = secretsProperties.keys();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            if (key.startsWith("user.")) {
                String passCandidate = (String) secretsProperties.get(key);
                if (!passCandidate.startsWith("password.")) {
                    continue;
                }
                logger.info("setting password for user {}", key.substring("user.".length()));
                verifier.getLocalSecrets().put(key.substring("user.".length()),
                        passCandidate.substring("password.".length()).toCharArray());
            }
        }
        return true;
    }

    private boolean settingEclipsePreferences(MapVerifier verifier) {
        // Preferences preferences = ConfigurationScope..getNode("skysail.server.ext.osgimonitor");
        return false;
    }

    private void startStandaloneServer(String portAsString) {
        try {
            server = new Server(Protocol.HTTP, Integer.valueOf(portAsString), restletComponent);
            server.start();
        } catch (Exception e) {
            logger.error("Exception when starting standalone server", e);
        }
    }

}
