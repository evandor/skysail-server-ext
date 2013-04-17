package de.twenty11.skysail.server.ext.jgit.internal;

import org.jgit.Scheduler;
import org.jgit.SchedulerException;
import org.jgit.impl.StdSchedulerFactory;
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
    private Scheduler scheduler;

    public void activate() {
        logger.info("Activating Configuration Component for Skysail Bookmarks Extension");
        component = componentProvider.getComponent();
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        application = new MyApplication(component.getContext(), scheduler);
    }

    public void deactivate() {
        logger.info("Deactivating Configuration Component for Skysail Bookmarks Extension");
        application = null;
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setComponentProvider(ComponentProvider componentProvider) {
        this.componentProvider = componentProvider;
    }

    @Override
    public Application getApplication() {
        return application;
    }

}
