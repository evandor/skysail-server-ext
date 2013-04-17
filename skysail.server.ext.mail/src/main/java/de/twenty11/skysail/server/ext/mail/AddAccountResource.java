package de.twenty11.skysail.server.ext.mail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.restlet.data.Form;
import org.restlet.resource.Get;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.common.responses.FailureResponse;
import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.responses.SuccessResponse;
import de.twenty11.skysail.server.ext.mail.internal.MyApplication;
import de.twenty11.skysail.server.restlet.AddServerResource;

@Presentation(preferred = PresentationStyle.EDIT)
public class AddAccountResource extends AddServerResource<AccountDescriptor> {

    public AddAccountResource() {
        setName("add account");
    }

    @Override
    @Get("html")
    public FormResponse<AccountDescriptor> createForm() {
        return new FormResponse<AccountDescriptor>(new AccountDescriptor(), "../accounts/");
    }

    @Override
    public AccountDescriptor getData(Form form) {
        return new AccountDescriptor(form.getFirstValue("name"));

    }

    @Override
    public SkysailResponse<AccountDescriptor> addEntity(AccountDescriptor entity) {
        MyApplication application = (MyApplication) getApplication();
        EntityManagerFactory emf = application.getEmf();
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            em.close();
            return new SuccessResponse<AccountDescriptor>();
        } catch (Exception e) {
            e.printStackTrace();
            return new FailureResponse<AccountDescriptor>(e.getMessage());
        }

    }

}
