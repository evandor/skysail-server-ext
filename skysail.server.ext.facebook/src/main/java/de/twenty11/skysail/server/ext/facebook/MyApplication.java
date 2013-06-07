package de.twenty11.skysail.server.ext.facebook;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.restlet.Context;

import de.twenty11.skysail.server.core.restlet.RouteBuilder;
import de.twenty11.skysail.server.restlet.SkysailApplication;

/**
 * The restlet application defined in this bundle.
 * 
 */
public class MyApplication extends SkysailApplication {

    private EntityManagerFactory emf;

    /**
     * @param staticPathTemplate
     * @param bundleContext
     */
    public MyApplication(Context componentContext, EntityManagerFactory emf) {
        super(componentContext == null ? null : componentContext.createChildContext());
        setDescription("RESTful skysail.server.ext.facebook bundle");
        setOwner("twentyeleven");
        setName("facebook");
        this.emf = emf;
    }

    protected void attach() {
        // @formatter:off
        router.attach(new RouteBuilder("", MyRootResource.class).setVisible(true));
        // @formatter:on
    }

    public EntityManager getEntityManager() {
        return this.emf != null ? this.emf.createEntityManager() : null;
    }

}
