package de.twenty11.skysail.server.ext.jgit.internal;

import de.twenty11.skysail.server.ext.jgit.*;

import org.eclipse.jgit.lib.Repository;
import org.restlet.Context;

import de.twenty11.skysail.server.restlet.RouteBuilder;
import de.twenty11.skysail.server.restlet.SkysailApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * @author carsten
 * 
 */
public class MyApplication extends SkysailApplication {

    private final Repository defaultRepository;
    private javax.persistence.EntityManagerFactory emf;

    public MyApplication(Context componentContext, Repository defaultRepository, EntityManagerFactory emf) {
        super(componentContext == null ? null : componentContext.createChildContext());
        setDescription("RESTful Jenkins bundle");
        setOwner("twentyeleven");
        setName("jgit");
        this.defaultRepository = defaultRepository;
        this.emf = emf;

    }

    protected void attach() {
        // @formatter:off
        router.attach(new RouteBuilder("", MyRootResource.class).setVisible(false));
        router.attach(new RouteBuilder("repos", LocalRepositoriesResource.class).setText("Local Repositories"));
        router.attach(new RouteBuilder("repos/", AddLocalRepositoryResource.class).setVisible(false));
        router.attach(new RouteBuilder("repos/{id}", LocalRepositoryResource.class).setVisible(false));
        // @formatter:on
    }

    public EntityManager getEntityManager() {
        return this.emf != null ? this.emf.createEntityManager() : null;
    }
}
