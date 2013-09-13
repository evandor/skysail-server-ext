package de.twenty11.skysail.server.ext.jgit;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.commands.Command;
import de.twenty11.skysail.common.navigation.LinkedPage;
import de.twenty11.skysail.common.responses.FailureResponse;
import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.UniqueResultServerResource2;
import de.twenty11.skysail.server.ext.jgit.internal.MyApplication;

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
    @Get("html|json")
    public SkysailResponse<LocalRepositoryDescriptor> getEntity() {
        return getEntity("local git repository " + key);
    }

    @Override
    protected LocalRepositoryDescriptor getData() {
        registerCommand("create", new CreateLocalRepositoryCommand(result));
        CloneIntoLocalRepositoryCommand cloneCommand = new CloneIntoLocalRepositoryCommand(result, null);
        if (cloneCommand.applicable()) {
            registerLinkedPage(new ClonePage(result));
        }
        // registerCommand("showfiles", new ShowFilesCommand());
        registerLinkedPage(new LinkedPage() {
            @Override
            public boolean applicable() {
                return !(getReference().getRemainingPart().length() > 0);
            }

            @Override
            public String getHref() {
                return key + "/listdir/";
            }

            @Override
            public String getLinkText() {
                return "Show Files...";
            }
        });
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

    @Override
    public LocalRepositoryDescriptor getData(Form form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SkysailResponse<?> addEntity(LocalRepositoryDescriptor entity) {
        // TODO Auto-generated method stub
        return null;
    }

}
