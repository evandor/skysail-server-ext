package de.twenty11.skysail.server.ext.jgit.internal;

import java.util.GregorianCalendar;

import org.restlet.data.Form;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.jgit.ExecuteMavenCommand;
import de.twenty11.skysail.server.ext.jgit.LocalRepositoryDescriptor;
import de.twenty11.skysail.server.restlet.AddServerResource;

@Presentation(preferred = PresentationStyle.EDIT)
public class MavenFormResource extends AddServerResource<MavenFormDescriptor> {

    private LocalRepositoryDescriptor repositoryDescriptor;
    private String path;

    @Override
    protected void doInit() throws ResourceException {
        String id = (String) getRequest().getAttributes().get("id");
        repositoryDescriptor = ((MyApplication) getApplication()).getRepository().getLocalRepositoryDescriptor(id);
        path = repositoryDescriptor.getPath() + getReference().getRemainingPart();
    }

    @Override
    @Get("html")
    public FormResponse<MavenFormDescriptor> createForm() {
        return new FormResponse<MavenFormDescriptor>(new MavenFormDescriptor(), "../maven"
                + getReference().getRemainingPart());
    }

    @Override
    public MavenFormDescriptor getData(Form form) {
        return new MavenFormDescriptor(form.getFirstValue("command"), path);
    }

    @Override
    public SkysailResponse<MavenFormDescriptor> addEntity(MavenFormDescriptor entity) {
        ExecuteMavenCommand command = new ExecuteMavenCommand(repositoryDescriptor, entity);
        ((MyApplication) getApplication()).addExecutedCommand(new GregorianCalendar().getTimeInMillis(), command);
        command.execute();
        return new SkysailResponse<MavenFormDescriptor>();
    }
}
