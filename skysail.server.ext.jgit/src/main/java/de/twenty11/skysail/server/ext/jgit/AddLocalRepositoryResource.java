package de.twenty11.skysail.server.ext.jgit;

import org.restlet.data.Form;

import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.responses.SuccessResponse;
import de.twenty11.skysail.server.ext.jgit.internal.MyApplication;
import de.twenty11.skysail.server.restlet.AddServerResource;

public class AddLocalRepositoryResource extends AddServerResource<LocalRepositoryDescriptor> {

    @Override
    public FormResponse<LocalRepositoryDescriptor> createForm() {
        return new FormResponse<LocalRepositoryDescriptor>(new LocalRepositoryDescriptor(), "../repos/");
    }

    @Override
    public LocalRepositoryDescriptor getData(Form form) {
        return new LocalRepositoryDescriptor(form.getFirstValue("name"), form.getFirstValue("path"));
    }

    @Override
    public SkysailResponse<LocalRepositoryDescriptor> addEntity(LocalRepositoryDescriptor entity) {
        MyApplication app = (MyApplication) getApplication();
        app.getRepository().addLocalRepositoryDescriptor(entity);
        return new SuccessResponse<LocalRepositoryDescriptor>();

    }
}
