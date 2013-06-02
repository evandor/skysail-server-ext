package de.twenty11.skysail.server.ext.activiti;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.commands.Command;
import de.twenty11.skysail.common.responses.FailureResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.UniqueResultServerResource2;
import de.twenty11.skysail.server.ext.activiti.internal.MyApplication;

public class ProcessResource extends UniqueResultServerResource2<ProcessDescriptor> {

    private String id;
    private String action;


    @Override
    @Get("html|json")
    public SkysailResponse<ProcessDescriptor> getEntity() {
        return getEntity("Activiti process " + id);
    }

    @Override
    protected ProcessDescriptor getData() {
        MyApplication application = (MyApplication) getApplication();
        RepositoryService repositoryService = application.getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.getProcessDefinition(id);
        registerCommand("start", new StartCommand(application.getRuntimeService(), processDefinition));
        return new ProcessDescriptor(processDefinition);
    }

    @Override
    protected void doInit() throws ResourceException {
        id = (String) getRequest().getAttributes().get("id");
        Form form = new Form(getRequest().getEntity());
        action = form.getFirstValue("action");
    }

    @Put("html|json")
    public SkysailResponse<ProcessDescriptor> startOrStopBundle(Representation entity) {
        Command command = getCommand(action);
        if (command != null) {
            try {
                command.execute();
            } catch (Exception e) {
                return new FailureResponse<ProcessDescriptor>(e);
            }
        }
        setMessage("Success");
        return getEntity();
    }
}
