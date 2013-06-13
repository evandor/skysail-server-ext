package de.twenty11.skysail.server.ext.notes;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.core.restlet.RouteBuilder;
import de.twenty11.skysail.server.ext.notes.resources.FacebookRootResource;
import de.twenty11.skysail.server.ext.notes.resources.MeResource;
import de.twenty11.skysail.server.restlet.SkysailApplication;
import de.twenty11.skysail.server.services.ApplicationProvider;

/**
 * The restlet application defined in this bundle.
 * 
 */
public class NotesApplication extends SkysailApplication implements ApplicationProvider {

    private EntityManagerFactory enitityManagerFactory;
    private Map<String, String> facebookLogins = new HashMap<String, String>();

    private static Logger logger = LoggerFactory.getLogger(NotesApplication.class);

    public NotesApplication() {
        super(null, null);
        setDescription("RESTful skysail.server.ext.notes bundle");
        setOwner("twentyeleven");
        setName("notes");
    }

    protected void attach() {
        // @formatter:off
        router.attach(new RouteBuilder("", FacebookRootResource.class).setVisible(false));
        router.attach(new RouteBuilder("/me", MeResource.class).setText("Me").setVisible(true));
        // @formatter:on
    }

    public synchronized void setEntityManager(EntityManagerFactory emf) {
        this.enitityManagerFactory = emf;
    }

    public void setAccessToken(String identifier, String token) {
        facebookLogins.put(identifier, token);
    }

    public String getAccessToken(String identifier) {
        return facebookLogins.get(identifier);
    }

    public void logoutUser(String currentUser) {
        facebookLogins.remove(currentUser);
    }

}
