package de.twenty11.skysail.server.ext.bookmarks;

import javax.persistence.EntityManagerFactory;

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
    private BookmarkApplication application;
    private EntityManagerFactory emf;

    public void activate() {
        logger.info("Activating Configuration Component for Skysail Bookmarks Extension");
        component = componentProvider.getComponent();
        application = new BookmarkApplication(component.getContext(), emf);
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void deactivate() {
        logger.info("Deactivating Configuration Component for Skysail Bookmarks Extension");
        application = null;
    }

    public void setComponentProvider(ComponentProvider componentProvider) {
        this.componentProvider = componentProvider;
    }

    @Override
    public Application getApplication() {
        return application;
    }

}
