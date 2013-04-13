package de.twenty11.skysail.server.ext.activiti.internal;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.restlet.Context;

import de.twenty11.skysail.server.ext.activiti.MyRootResource;
import de.twenty11.skysail.server.ext.activiti.RepositoryResource;
import de.twenty11.skysail.server.ext.activiti.TasksResource;
import de.twenty11.skysail.server.restlet.RouteBuilder;
import de.twenty11.skysail.server.restlet.SkysailApplication;
import de.twenty11.skysail.server.services.ApplicationProvider;

/**
 * @author carsten
 * 
 */
public class MyApplication extends SkysailApplication implements ApplicationProvider {

    private RepositoryService repositoryService;
    private RuntimeService runtimeService;
    private TaskService taskService;

    // non-arg constructor needed for scr
    public MyApplication() {
        this(null, null, null);
    }

    /**
     * @param staticPathTemplate
     * @param bundleContext
     */
    public MyApplication(Context componentContext, RepositoryService repositoryService, RuntimeService runtimeService) {
        super(componentContext == null ? null : componentContext.createChildContext());
        setDescription("RESTful Jenkins bundle");
        setOwner("twentyeleven");
        setName("activiti");
        this.repositoryService = repositoryService;
        this.runtimeService = runtimeService;
    }

    protected void attach() {
        // @formatter:off
        router.attach(new RouteBuilder("", MyRootResource.class).setVisible(false));
        router.attach(new RouteBuilder("/repository", RepositoryResource.class).setVisible(true));
        router.attach(new RouteBuilder("/tasks", TasksResource.class).setText("Activiti Tasks").setVisible(true));
//        router.attach(new RouteBuilder("/installation/{name}/jobs", JobsResource.class).setVisible(false));
        
        // @formatter:on
    }

    public RepositoryService getRepositoryService() {
        return repositoryService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public TaskService getActivitiTaskService() {
        return taskService;
    }
}
