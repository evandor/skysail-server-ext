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
    private DbViewerApplication application;
    private ServiceRegistration currentApplicationService;
    private EntityManagerFactory emf;

    protected void activate(ComponentContext componentContext) throws ConfigurationException {
        logger.info("Activating Configuration Component for Skysail Osgimonitor Extension");
        component = componentProvider.getComponent();
        application = new DbViewerApplication(componentContext.getBundleContext(), component.getContext(), emf);
        // application.setVerifier(componentProvider.getVerifier());

        currentApplicationService = componentContext.getBundleContext().registerService(
                ApplicationProvider.class.getName(), this, null);
    }

    protected void deactivate(ComponentContext componentContext) {
        logger.info("Deactivating Configuration Component for Skysail Osgimonitor Extension");
        componentContext.getBundleContext().ungetService(currentApplicationService.getReference());
        component.getDefaultHost().detach(application);
        application = null;
    }

    public void setComponentProvider(ComponentProvider componentProvider) {
        this.componentProvider = componentProvider;
    }

    @Override
    public Application getApplication() {
        return application;
    }

    public synchronized void setEntityManager(EntityManagerFactory emf) {
        this.emf = emf;
    }

}
