package de.twenty11.skysail.server.ext.jgit.internal;

import org.restlet.data.Form;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.jgit.CloneIntoLocalRepositoryCommand;
import de.twenty11.skysail.server.ext.jgit.LocalRepositoryDescriptor;
import de.twenty11.skysail.server.restlet.AddServerResource;

@Presentation(preferred = PresentationStyle.EDIT)
public class CloneFormResource extends AddServerResource<CloneFormDescriptor> {

    private String id;

    @Override
    protected void doInit() throws ResourceException {
        id = (String) getRequest().getAttributes().get("id");
    }

    @Override
    @Get("html")
    public FormResponse<CloneFormDescriptor> createForm() {
        return new FormResponse<CloneFormDescriptor>(new CloneFormDescriptor(), "cloneform");
    }

    @Override
    public CloneFormDescriptor getData(Form form) {
        return new CloneFormDescriptor(form.getFirstValue("remotePath"));
    }

    @Override
    public SkysailResponse<CloneFormDescriptor> addEntity(CloneFormDescriptor entity) {
        LocalRepositoryDescriptor repositoryDescriptor = ((MyApplication) getApplication()).getRepository()
                .getLocalRepositoryDescriptor(id);
        new CloneIntoLocalRepositoryCommand(repositoryDescriptor, entity).execute();
        return new SkysailResponse<CloneFormDescriptor>();
    }

}
