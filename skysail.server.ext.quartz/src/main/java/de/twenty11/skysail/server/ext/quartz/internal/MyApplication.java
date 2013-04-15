package de.twenty11.skysail.server.ext.quartz.internal;

import org.quartz.Scheduler;
import org.restlet.Context;

import de.twenty11.skysail.server.ext.quartz.MyRootResource;
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
        setName("quartz");
        this.scheduler = scheduler;
    }

    protected void attach() {
        // @formatter:off
        router.attach(new RouteBuilder("", MyRootResource.class).setVisible(false));
//        router.attach(new RouteBuilder("/repository", ProcessesResource.class).setText("Activiti Repository"));
//        router.attach(new RouteBuilder("/repository/{id}", ProcessResource.class).setVisible(false));
//        router.attach(new RouteBuilder("/tasks", TasksResource.class).setText("Activiti Tasks").setVisible(true));
//        router.attach(new RouteBuilder("/installation/{name}/jobs", JobsResource.class).setVisible(false));
        
        // @formatter:on
    }

}
