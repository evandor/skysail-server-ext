package de.twenty11.skysail.server.ext.facebook.internal;

import javax.persistence.EntityManagerFactory;

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
    private MyApplication application;
    private EntityManagerFactory emf;

    protected void activate(ComponentContext componentContext) throws ConfigurationException {
        logger.info("Activating Configuration Component for Skysail Facebook Extension");
        component = componentProvider.getComponent();
        application = new MyApplication(component.getContext(), emf);
    }

    protected void deactivate(ComponentContext componentContext) {
        logger.info("Deactivating Configuration Component for Skysail Faceboook Extension");
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
