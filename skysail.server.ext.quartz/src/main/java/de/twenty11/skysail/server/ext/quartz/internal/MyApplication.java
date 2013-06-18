package de.twenty11.skysail.server.ext.quartz.internal;

import org.quartz.Scheduler;
import org.restlet.Context;

import de.twenty11.skysail.server.core.restlet.RouteBuilder;
import de.twenty11.skysail.server.ext.quartz.AddJobResource;
import de.twenty11.skysail.server.ext.quartz.AddTriggerResource;
import de.twenty11.skysail.server.ext.quartz.JobsResource;
import de.twenty11.skysail.server.ext.quartz.MyRootResource;
import de.twenty11.skysail.server.ext.quartz.SchedulerResource;
import de.twenty11.skysail.server.ext.quartz.TriggersResource;
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
        super();
        if (getContext() != null) {
            setContext(getContext().createChildContext());
        }
        setDescription("RESTful Jenkins bundle");
        setOwner("twentyeleven");
        setName("quartz");
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
