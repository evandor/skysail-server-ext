package de.twenty11.skysail.server.ext.jgit;

import de.twenty11.skysail.common.responses.FailureResponse;
import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.responses.SuccessResponse;
import de.twenty11.skysail.server.ext.jgit.internal.MyApplication;
import de.twenty11.skysail.server.restlet.AddServerResource;
import org.restlet.data.Form;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class AddLocalRepositoryResource extends AddServerResource<LocalRepositoryDescriptor> {
    @Override
    public FormResponse<LocalRepositoryDescriptor> createForm() {
        return new FormResponse<LocalRepositoryDescriptor>(new LocalRepositoryDescriptor(), "../repos/");
    }

    @Override
    public LocalRepositoryDescriptor getData(Form form) {
        return new LocalRepositoryDescriptor(form.getFirstValue("name"));
    }

    @Override
    public SkysailResponse<LocalRepositoryDescriptor> addEntity(LocalRepositoryDescriptor entity) {
        MyApplication app = (MyApplication)getApplication();
        EntityManager em = app.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            em.close();
            return new SuccessResponse<LocalRepositoryDescriptor>();
        } catch (Exception e) {
            e.printStackTrace();
            return new FailureResponse<LocalRepositoryDescriptor>(e.getMessage());
        }
    }
}
