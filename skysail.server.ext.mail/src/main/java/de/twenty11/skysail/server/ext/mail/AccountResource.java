package de.twenty11.skysail.server.ext.mail;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.restlet.resource.ResourceException;

import de.twenty11.skysail.server.ext.mail.internal.MyApplication;
import de.twenty11.skysail.server.restlet.UniqueResultServerResource2;

public class AccountResource extends UniqueResultServerResource2<AccountDetails> {

    private String id;

    @Override
    protected void doInit() throws ResourceException {
        id = (String) getRequest().getAttributes().get("id");
        // Form form = new Form(getRequest().getEntity());
        // action = form.getFirstValue("action");

    }

    @Override
    protected AccountDetails getData() {
        MyApplication app = (MyApplication) getApplication();
        EntityManager em = app.getEmf().createEntityManager();
        TypedQuery<AccountDescriptor> query = em.createQuery("SELECT c FROM AccountDescriptor c WHERE c.name = :name",
                AccountDescriptor.class);
        query.setParameter("name", id);
        return (AccountDetails) query.getSingleResult();
    }

}
