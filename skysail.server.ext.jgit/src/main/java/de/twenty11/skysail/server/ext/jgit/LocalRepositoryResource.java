package de.twenty11.skysail.server.ext.jgit;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.commands.Command;
import de.twenty11.skysail.common.responses.FailureResponse;
import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.jgit.internal.MyApplication;
import de.twenty11.skysail.server.restlet.UniqueResultServerResource2;

public class LocalRepositoryResource extends UniqueResultServerResource2<LocalRepositoryDescriptor> {

    private String key;
    private String action;
    private LocalRepositoryDescriptor result;

    @Override
    protected void doInit() throws ResourceException {
        key = (String) getRequest().getAttributes().get("id");
        Form form = new Form(getRequest().getEntity());
        action = form.getFirstValue("action");
        MyApplication app = (MyApplication) getApplication();
        result = app.getRepository().getLocalRepositoryDescriptor(key);
    }

    @Override
    protected LocalRepositoryDescriptor getData() {
        registerCommand("create", new CreateLocalRepositoryCommand(result));
        CloneIntoLocalRepositoryCommand cloneCommand = new CloneIntoLocalRepositoryCommand(result, null);
        if (cloneCommand.applicable()) {
            registerLinkedPage(new ClonePage(result));
        }
        return result;
    }

    /**
     * parameter needed for restlet!
     * 
     * @param entity
     * @return
     */
    @Put("html|json")
    public SkysailResponse<LocalRepositoryDescriptor> startOrStopBundle(Representation entity) {
        Command command = getCommand(action);
        if (command != null) {
            try {
                if (command.getName().equals("Clone")) {
                    return new FormResponse<LocalRepositoryDescriptor>(new LocalRepositoryDescriptor(), "../repos/");
                }
                command.execute();
            } catch (Exception e) {
                return new FailureResponse<LocalRepositoryDescriptor>(e);
            }
        }
        setMessage("Success");
        return new SkysailResponse<LocalRepositoryDescriptor>(getData());
    }

}
