package de.twenty11.skysail.server.ext.activiti.internal;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
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
    private RepositoryService repositoryService;
    private RuntimeService runtimeService;
    private TaskService taskService;

    public void activate() {
        logger.info("Activating Configuration Component for Skysail Activiti Extension");
        component = componentProvider.getComponent();
        application = new MyApplication(component.getContext(), repositoryService, runtimeService);
        application.setTaskService(this.taskService);
    }

    public void deactivate() {
        logger.info("Deactivating Configuration Component for Skysail Activiti Extension");
        application = null;
    }

    public void setComponentProvider(ComponentProvider componentProvider) {
        this.componentProvider = componentProvider;
    }

    @Override
    public Application getApplication() {
        return application;
    }

    public void setRepositoryService(RepositoryService service) {
        this.repositoryService = service;
    }

    public void setRuntimeService(RuntimeService service) {
        this.runtimeService = service;
    }

    public void setTaskService(TaskService service) {
        this.taskService = service;
    }

}
