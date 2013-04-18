package de.twenty11.skysail.server.ext.jgit;

import de.twenty11.skysail.server.ext.jgit.internal.MyApplication;
import de.twenty11.skysail.server.restlet.UniqueResultServerResource2;
import org.restlet.data.Form;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class LocalRepositoryResource extends UniqueResultServerResource2<LocalRepositoryDescriptor> {

    private String key;

    @Override
    protected void doInit() throws ResourceException {
        key = (String) getRequest().getAttributes().get("id");
        Form form = new Form(getRequest().getEntity());
        //action = form.getFirstValue("action");
    }

    @Override
    protected LocalRepositoryDescriptor getData() {

        MyApplication app = (MyApplication)getApplication();
        EntityManager em = app.getEntityManager();
        TypedQuery<LocalRepositoryDescriptor> query = em.createQuery("SELECT c FROM LocalRepositoryDescriptor c WHERE c.name = :name",
                LocalRepositoryDescriptor.class);
        query.setParameter("name", key);
        LocalRepositoryDescriptor result = (LocalRepositoryDescriptor) query.getSingleResult();
        registerCommand("create", new CreateLocalRepositoryCommand(result));
        return result;
    }


}
