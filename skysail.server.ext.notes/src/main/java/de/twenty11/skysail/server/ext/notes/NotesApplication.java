package de.twenty11.skysail.server.ext.notes;

import javax.persistence.EntityManagerFactory;

import org.restlet.routing.Router;
import org.restlet.routing.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.config.ServerConfiguration;
import de.twenty11.skysail.server.core.restlet.RouteBuilder;
import de.twenty11.skysail.server.ext.notes.resources.AddFolderResource;
import de.twenty11.skysail.server.ext.notes.resources.FolderResource;
import de.twenty11.skysail.server.ext.notes.resources.NotesRootResource;
import de.twenty11.skysail.server.restlet.SkysailApplication;
import de.twenty11.skysail.server.services.ApplicationProvider;

/**
 * The restlet application defined in this bundle.
 * 
 */
public class NotesApplication extends SkysailApplication implements ApplicationProvider {

    private EntityManagerFactory enitityManagerFactory;
    private static Logger logger = LoggerFactory.getLogger(NotesApplication.class);

    public NotesApplication() {
        super(null);
        logger.info("Instanciating new NotesApplication");
        setDescription("RESTful skysail.server.ext.notes bundle");
        setOwner("twentyeleven");
        setName("notes");
    }

    protected void attach() {
        // make sure to match proper resource even if request url contains add. information
        router.setDefaultMatchingMode(Template.MODE_STARTS_WITH);
        router.setRoutingMode(Router.MODE_LAST_MATCH);

        // @formatter:off
        router.attach(new RouteBuilder("", NotesRootResource.class).setVisible(false));
        //router.attach(new RouteBuilder("/me", MeResource.class).setText("Me").setVisible(true));
       // router.attach(new RouteBuilder("/folders", FoldersResource.class).setText("Home").setVisible(true));
//        router.attach(new RouteBuilder("/note/{id}", NoteResource.class).setText("Home").setVisible(true));
        router.attach(new RouteBuilder("/folder", AddFolderResource.class).setVisible(false));
        router.attach(new RouteBuilder("/folder/{id}", FolderResource.class).setVisible(false));
        // @formatter:on
    }

    public synchronized void setEntityManager(EntityManagerFactory emf) {
        this.enitityManagerFactory = emf;
    }

    public void setServerConfiguration(ServerConfiguration sc) {
        super.setServerConfiguration(sc);
    }

}
