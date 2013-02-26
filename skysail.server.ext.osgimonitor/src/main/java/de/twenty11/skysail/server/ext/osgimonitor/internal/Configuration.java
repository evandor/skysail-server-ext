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

import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.ComponentContext;
import org.restlet.Application;
import org.restlet.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.services.ApplicationProvider;
import de.twenty11.skysail.server.services.ComponentProvider;

public class Configuration implements ApplicationProvider {

    private static Logger logger = LoggerFactory.getLogger(Configuration.class);
    private ComponentProvider componentProvider;
    private Component component;
    private OsgiMonitorViewerApplication application;
    private ServiceRegistration thisApplicationService;

    protected void activate(ComponentContext componentContext) throws ConfigurationException {
        logger.info("Activating Skysail Ext Osgimonitor Configuration Component");

        component = componentProvider.getComponent();
        application = new OsgiMonitorViewerApplication(
                componentContext.getBundleContext());
        application.setVerifier(componentProvider.getVerifier());
        // logger.info("attaching application and starting {}", this.toString());
        // component.getDefaultHost().attach(application);

        thisApplicationService = componentContext.getBundleContext().registerService(
                ApplicationProvider.class.getName(), this, null);

    }

    protected void deactivate(ComponentContext componentContext) {
        logger.info("Deactivating Skysail Ext Osgimonitor Configuration Component");
        componentContext.getBundleContext().ungetService(thisApplicationService.getReference());
        component.getDefaultHost().detach(application);
        application = null;
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

    @Override
    public Application getApplication() {
        return application;
    }

}
