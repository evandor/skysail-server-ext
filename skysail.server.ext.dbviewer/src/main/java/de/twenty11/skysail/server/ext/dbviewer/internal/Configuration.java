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

package de.twenty11.skysail.server.ext.dbviewer.internal;

import javax.persistence.EntityManagerFactory;

import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.ComponentContext;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.VirtualHost;
import org.restlet.security.MapVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.config.ServerConfiguration;

public class Configuration {

    private static Logger logger = LoggerFactory.getLogger(Configuration.class);
    private DbViewerComponent restletComponent;
    private Server server;
    private ComponentContext context;
    private ConfigurationAdmin configadmin;
    private ServerConfiguration serverConfig;
    private EntityManagerFactory emf;
    private ServiceRegistration registration;

    protected void activate(ComponentContext componentContext) throws ConfigurationException {
        logger.info("Activating Skysail Ext DbViewer Configuration Component");
        this.context = componentContext;

        logger.info("Configuring Skysail Ext DbViewer...");
        if (serverConfig.startComponent()) {
            String port = (String) serverConfig.getConfigForKey("port");
            logger.info("port was configured on {}", port);
            MapVerifier verifier = new MapVerifier();
            try {
                if (!serverConfig.setSecretVerifier(verifier, configadmin)) {
                    logger.warn("not starting up the application due to encountered configuration problems.");
                    return;
                }
            } catch (Exception e) {
                logger.error("Configuring secretVerifier encountered a problem: {}", e.getMessage());
                e.printStackTrace();
                throw new ConfigurationException("secrets", "file not found", e);
            }
            logger.info("Starting standalone osgimonitor server on port {}", port);
            restletComponent = new DbViewerComponent(this.context, verifier, emf);
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
        DbViewerApplication application = new DbViewerApplication("/static", context.getBundleContext(), emf);

        VirtualHost vh = new VirtualHost();
        vh.attach(application);
        return vh;
    }

    protected void deactivate(ComponentContext ctxt) {
        logger.info("Deactivating Skysail Ext DbViewer Configuration Component");
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

    public synchronized void setServerConfiguration(de.twenty11.skysail.server.config.ServerConfiguration serverConfig) {
        logger.info("setting configadmin in DbViewer Configuration");
        this.serverConfig = serverConfig;
    }

    public synchronized void setEntityManager(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public synchronized void setConfigAdmin(ConfigurationAdmin configadmin) {
        logger.info("setting configadmin in OsgiMonitor Configuration");
        this.configadmin = configadmin;
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
