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
import java.util.Properties;

import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.component.ComponentContext;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.security.MapVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configuration implements ManagedService {

    private static Logger logger = LoggerFactory.getLogger(Configuration.class);
    private OsgiMonitorComponent restletComponent;
    private Server server;
    private ComponentContext context;
    private ConfigurationAdmin configadmin;

    // Component itself is started once the configuration has been retrieved (in method "updated")
    protected void activate(ComponentContext ctxt) {
        logger.info("Activating Skysail Ext Osgimonitor Configuration Component");
        this.context = ctxt;
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
        Dictionary config = properties == null ? getDefaultConfig() : properties;
        if (startStandaloneServer()) {
            String port = (String) config.get("port");
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
        }
    }

    public synchronized void setConfigAdmin(ConfigurationAdmin configadmin) {
        logger.info("setting configadmin in OsgiMonitor Configuration");
        this.configadmin = configadmin;
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
            logger.error("could not find 'secrets' configuration; no one will be able to log in!");
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

    @SuppressWarnings("rawtypes")
    private Dictionary getDefaultConfig() {
        logger.info("Configuring Skysail Ext Osgimonitor with defaults");
        Properties properties = new Properties();
        properties.put("port", "8554");
        return properties;
    }

    private boolean startStandaloneServer() {
        // for now
        return true;
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
