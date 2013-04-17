package de.twenty11.skysail.server.ext.jgit.internal;

import de.twenty11.skysail.server.ext.jgit.*;

import org.jgit.Scheduler;
import org.restlet.Context;

import de.twenty11.skysail.server.restlet.RouteBuilder;
import de.twenty11.skysail.server.restlet.SkysailApplication;

/**
 * @author carsten
 * 
 */
public class MyApplication extends SkysailApplication {

    private Scheduler scheduler;

    /**
     * @param scheduler
     * @param staticPathTemplate
     * @param bundleContext
     */
    public MyApplication(Context componentContext, Scheduler scheduler) {
        super(componentContext == null ? null : componentContext.createChildContext());
        setDescription("RESTful Jenkins bundle");
        setOwner("twentyeleven");
        setName("jgit");
        this.scheduler = scheduler;
    }

    protected void attach() {
        // @formatter:off
        router.attach(new RouteBuilder("", MyRootResource.class).setVisible(false));
        router.attach(new RouteBuilder("/scheduler", SchedulerResource.class).setText("Quartz Scheduler"));
        router.attach(new RouteBuilder("/jobs", JobsResource.class).setText("Quartz Jobs"));
        router.attach(new RouteBuilder("/jobs/", AddJobResource.class).setVisible(false));
        router.attach(new RouteBuilder("/triggers", TriggersResource.class).setText("Quartz Triggers"));
        router.attach(new RouteBuilder("/triggers/", AddTriggerResource.class).setVisible(false));
        // @formatter:on
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

}
