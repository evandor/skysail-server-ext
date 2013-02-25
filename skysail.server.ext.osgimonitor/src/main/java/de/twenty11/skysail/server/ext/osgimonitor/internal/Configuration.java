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

import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.component.ComponentContext;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.services.ApplicationProvider;
import de.twenty11.skysail.server.services.ComponentProvider;

public class Configuration implements ManagedService {

    private static Logger logger = LoggerFactory.getLogger(Configuration.class);
    private Server server;
    private ServiceRegistration registration;
    private ComponentProvider componentProvider;
    private Component component;

    protected void activate(ComponentContext componentContext) throws ConfigurationException {
        logger.info("Activating Skysail Ext Osgimonitor Configuration Component");

        component = componentProvider.getComponent();
        OsgiMonitorViewerApplication application = new OsgiMonitorViewerApplication(
                componentContext.getBundleContext());
        application.setVerifier(componentProvider.getVerifier());
        logger.info("attaching application and starting {}", this.toString());
        component.getDefaultHost().attach(application);

    }

    protected void deactivate(ComponentContext ctxt) {
        logger.info("Deactivating Skysail Ext Osgimonitor Configuration Component");
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

    @SuppressWarnings("rawtypes")
    @Override
    public synchronized void updated(Dictionary properties) throws ConfigurationException {
        logger.info("Configuring Skysail Ext Osgimonitor...");
    }

    public void setApplicationProvider(ApplicationProvider provider) {
        logger.info("adding new application from {}", provider);
        Application application = provider.getApplication();
        component.getDefaultHost().attach("/" + application.getName(), application);
    }

    public void setComponentProvider(ComponentProvider componentProvider) {
        this.componentProvider = componentProvider;
    }

    public void unsetApplicationProvider(ApplicationProvider provider) {
        component.getDefaultHost().detach(provider.getApplication());
    }

}
