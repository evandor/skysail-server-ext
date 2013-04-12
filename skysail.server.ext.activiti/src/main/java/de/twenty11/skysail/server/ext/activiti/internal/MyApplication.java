package de.twenty11.skysail.server.ext.activiti.internal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.restlet.Context;

import de.twenty11.skysail.server.ext.activiti.MyRootResource;
import de.twenty11.skysail.server.ext.activiti.RepositoryResource;
import de.twenty11.skysail.server.restlet.RouteBuilder;
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
        setDescription("RESTful Jenkins bundle");
        setOwner("twentyeleven");
        setName("activiti");
        this.emf = emf;
    }

    protected void attach() {
        // @formatter:off
        router.attach(new RouteBuilder("", MyRootResource.class).setVisible(false));
        router.attach(new RouteBuilder("/repository", RepositoryResource.class).setVisible(true));
//        router.attach(new RouteBuilder("/installation/", AddJenkinsResource.class).setText("Add Jenkins Installation"));
//        router.attach(new RouteBuilder("/installation/{name}/jobs", JobsResource.class).setVisible(false));
        
        // @formatter:on
    }

    public EntityManager getEntityManager() {
        return this.emf != null ? this.emf.createEntityManager() : null;
    }

}
