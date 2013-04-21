package de.twenty11.skysail.server.ext.jgit.internal;

import javax.persistence.EntityManagerFactory;

import org.restlet.Context;
import org.restlet.routing.Router;
import org.restlet.routing.Template;

import de.twenty11.skysail.server.directory.SkysailDirectory;
import de.twenty11.skysail.server.ext.jgit.AddLocalRepositoryResource;
import de.twenty11.skysail.server.ext.jgit.ListDirResource;
import de.twenty11.skysail.server.ext.jgit.LocalRepositoriesResource;
import de.twenty11.skysail.server.ext.jgit.LocalRepositoryResource;
import de.twenty11.skysail.server.ext.jgit.MyRootResource;
import de.twenty11.skysail.server.ext.jgit.ShowFileResource;
import de.twenty11.skysail.server.restlet.RouteBuilder;
import de.twenty11.skysail.server.restlet.SkysailApplication;

/**
 * @author carsten
 * 
 */
public class MyApplication extends SkysailApplication {

    // private javax.persistence.EntityManagerFactory emf;
    private DbRepository repository;

    public MyApplication(Context componentContext, EntityManagerFactory emf) {
        super(componentContext == null ? null : componentContext.createChildContext());
        setDescription("RESTful Jenkins bundle");
        setOwner("twentyeleven");
        setName("jgit");
        repository = new Repository(emf);

    }

    protected void attach() {

        SkysailDirectory directory = new SkysailDirectory(getContext(), "file:///tmp");

        router.setDefaultMatchingMode(Template.MODE_STARTS_WITH);
        router.setRoutingMode(Router.MODE_LAST_MATCH);

        // @formatter:off
        router.attach(new RouteBuilder("", MyRootResource.class).setVisible(false));
        router.attach(new RouteBuilder("/repos", LocalRepositoriesResource.class).setText("Local Repositories"));
        router.attach(new RouteBuilder("/repos/", AddLocalRepositoryResource.class).setVisible(false));
        router.attach(new RouteBuilder("/repos/{id}", LocalRepositoryResource.class).setVisible(false));
        router.attach(new RouteBuilder("/repos/{id}/cloneform", CloneFormResource.class).setVisible(false));
        router.attach(new RouteBuilder("/repos/{id}/listdir/", ListDirResource.class).setVisible(false));
        router.attach(new RouteBuilder("/repos/{id}/showfile/", ShowFileResource.class).setVisible(false));
        // @formatter:on
    }

    // public EntityManager getEntityManager() {
    // return this.emf != null ? this.emf.createEntityManager() : null;
    // }

    public DbRepository getRepository() {
        return this.repository;
    }
}
