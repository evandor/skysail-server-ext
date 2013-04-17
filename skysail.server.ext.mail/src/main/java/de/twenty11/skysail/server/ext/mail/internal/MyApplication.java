package de.twenty11.skysail.server.ext.mail.internal;

import javax.persistence.EntityManagerFactory;

import org.restlet.Context;

import de.twenty11.skysail.server.ext.mail.AccountResource;
import de.twenty11.skysail.server.ext.mail.AccountsResource;
import de.twenty11.skysail.server.ext.mail.AddAccountResource;
import de.twenty11.skysail.server.ext.mail.MailboxesResource;
import de.twenty11.skysail.server.ext.mail.MyRootResource;
import de.twenty11.skysail.server.restlet.RouteBuilder;
import de.twenty11.skysail.server.restlet.SkysailApplication;

/**
 * @author carsten
 * 
 */
public class MyApplication extends SkysailApplication {

    private EntityManagerFactory emf;

    public MyApplication(Context componentContext, EntityManagerFactory emf) {
        super(componentContext == null ? null : componentContext.createChildContext());
        setDescription("RESTful Jenkins bundle");
        setOwner("twentyeleven");
        setName("mail");
        this.emf = emf;

    }

    protected void attach() {
        // @formatter:off
        router.attach(new RouteBuilder("", MyRootResource.class).setVisible(false));
        router.attach(new RouteBuilder("/accounts", AccountsResource.class).setText("Accounts"));
        router.attach(new RouteBuilder("/accounts/", AddAccountResource.class).setVisible(false));
        router.attach(new RouteBuilder("/accounts/{id}", AccountResource.class).setVisible(false));
        router.attach(new RouteBuilder("/accounts/{id}/boxes", MailboxesResource.class).setVisible(false));

        // @formatter:on
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
}
