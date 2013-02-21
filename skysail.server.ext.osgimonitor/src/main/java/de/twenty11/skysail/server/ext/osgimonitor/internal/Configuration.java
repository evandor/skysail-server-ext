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

import java.util.Dictionary;
import java.util.logging.Level;

import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.component.ComponentContext;
import org.restlet.Application;
import org.restlet.Server;
import org.restlet.engine.Engine;
import org.restlet.routing.VirtualHost;
import org.restlet.security.MapVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.config.ServerConfiguration;
import de.twenty11.skysail.server.services.ApplicationProvider;

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

        if (serverConfig.shouldStartComponent(this.getClass().getName())) {
            // Engine.setLogLevel(Level.ALL);
            Engine.setRestletLogLevel(Level.ALL);
            // System.setProperty("java.util.logging.config.file", "logging.config");
            logger.info("Starting component for Skysail Ext Osgimonitor...");
            String port = (String) serverConfig.getConfigForKey("port");
            logger.info("port was configured on {}", port);

            MapVerifier verifier = serverConfig.getVerifier(configadmin);
            logger.info("Starting standalone osgimonitor server on port {}", port);
            restletComponent = new OsgiMonitorComponent(this.context, verifier);

            server = serverConfig.startStandaloneServer(port, restletComponent);
        } else {
            logger.info("Starting virtual host for Skysail Osgimonitor...");
            VirtualHost virtualHost = createVirtualHost();
            if (componentContext.getBundleContext() != null) {
                this.registration = componentContext.getBundleContext().registerService(
                        "org.restlet.routing.VirtualHost", virtualHost, null);
            }
        }
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
        if (registration != null) {
            registration.unregister();
        }
    }

    private VirtualHost createVirtualHost() {
        OsgiMonitorViewerApplication application = new OsgiMonitorViewerApplication("/static",
                context.getBundleContext());
        VirtualHost vh = new VirtualHost();
        vh.attach(application);
        return vh;
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

    public void setApplicationProvider(ApplicationProvider provider) {
        logger.info("adding new application from {}", provider);
        Application application = provider.getApplication();
        restletComponent.getDefaultHost().attach("/" + application.getName(), application);
    }

    public void unsetApplicationProvider(ApplicationProvider provider) {
        restletComponent.getDefaultHost().detach(provider.getApplication());
    }

}
