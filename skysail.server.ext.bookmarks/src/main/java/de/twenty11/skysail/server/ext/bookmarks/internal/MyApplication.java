package de.twenty11.skysail.server.ext.bookmarks.internal;

import org.restlet.Context;

import de.twenty11.skysail.server.ext.bookmarks.MyRootResource;
import de.twenty11.skysail.server.restlet.RouteBuilder;
import de.twenty11.skysail.server.restlet.SkysailApplication;

/**
 * @author carsten
 * 
 */
public class MyApplication extends SkysailApplication {

    /**
     * @param staticPathTemplate
     * @param bundleContext
     */
    public MyApplication(Context componentContext) {
        super(componentContext == null ? null : componentContext.createChildContext());
        setDescription("RESTful Jenkins bundle");
        setOwner("twentyeleven");
        setName("bookmarks");
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
