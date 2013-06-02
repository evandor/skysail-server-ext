package de.twenty11.skysail.server.ext.quartz;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.commands.Command;
import de.twenty11.skysail.common.responses.FailureResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.UniqueResultServerResource2;
import de.twenty11.skysail.server.ext.quartz.internal.MyApplication;

public class SchedulerResource extends UniqueResultServerResource2<SchedulerDescriptor> {

    private String action;

    @Override
    protected void doInit() throws ResourceException {
        Form form = new Form(getRequest().getEntity());
        action = form.getFirstValue("action");
    }

    @Override
    @Get("html|json")
    public SkysailResponse<SchedulerDescriptor> getEntity() {
        MyApplication application = (MyApplication) getApplication();
        registerCommand("start", new StartCommand(application.getScheduler()));
        registerCommand("stop", new StopCommand(application.getScheduler()));
        return getEntity("Quartz Scheduler");
    }

    @Override
    protected SchedulerDescriptor getData() {
        // registerCommand("start", new StartCommand(bundle));
        MyApplication application = (MyApplication) getApplication();
        return new SchedulerDescriptor(application.getScheduler());
    }

    @Put("html|json")
    public SkysailResponse<SchedulerDescriptor> executeCommand(Representation entity) {
        Command command = getCommand(action);
        if (command != null) {
            try {
                command.execute();
            } catch (Exception e) {
                return new FailureResponse<SchedulerDescriptor>(e);
            }
        }
        setMessage("Success");
        return getEntity();
    }

}
