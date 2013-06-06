package de.twenty11.skysail.server.ext.facebook.internal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.restlet.Context;

import de.twenty11.skysail.server.core.restlet.RouteBuilder;
import de.twenty11.skysail.server.ext.facebook.MyRootResource;
import de.twenty11.skysail.server.restlet.SkysailApplication;
import de.twenty11.skysail.server.services.ApplicationProvider;

/**
 * @author carsten
 * 
 */
public class MyApplication extends SkysailApplication implements ApplicationProvider {

    private EntityManagerFactory emf;

    // non-arg constructor needed for scr
    public MyApplication() {
        this(null, null);
    }

    /**
     * @param staticPathTemplate
     * @param bundleContext
     */
    public MyApplication(Context componentContext, EntityManagerFactory emf) {
        super(componentContext == null ? null : componentContext.createChildContext());
        setDescription("RESTful skysail.server.ext.facebook bundle");
        setOwner("twentyeleven");
        setName("skysail.server.ext.facebook");
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