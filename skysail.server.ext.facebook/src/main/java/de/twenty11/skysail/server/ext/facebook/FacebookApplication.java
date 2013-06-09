package de.twenty11.skysail.server.ext.facebook;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.core.restlet.RouteBuilder;
import de.twenty11.skysail.server.ext.facebook.resources.FacebookRootResource;
import de.twenty11.skysail.server.ext.facebook.resources.FriendsResource;
import de.twenty11.skysail.server.ext.facebook.resources.MeResource;
import de.twenty11.skysail.server.restlet.SkysailApplication;
import de.twenty11.skysail.server.services.ApplicationProvider;

/**
 * The restlet application defined in this bundle.
 * 
 */
public class FacebookApplication extends SkysailApplication implements ApplicationProvider {

    private EntityManagerFactory emf;
    private Map<String, String> facebookLogins = new HashMap<String, String>();

    private static Logger logger = LoggerFactory.getLogger(FacebookApplication.class);

    public FacebookApplication() {
        super(null, null);
        setDescription("RESTful skysail.server.ext.facebook bundle");
        setOwner("twentyeleven");
        setName("facebook");
    }

    protected void attach() {
        // @formatter:off
        router.attach(new RouteBuilder("", FacebookRootResource.class).setVisible(false));
        router.attach(new RouteBuilder("/me", MeResource.class).setText("Me").setVisible(true));
        router.attach(new RouteBuilder("/me/friends", FriendsResource.class).setVisible(false));
        // @formatter:on
    }

    public EntityManager getEntityManager() {
        return this.emf != null ? this.emf.createEntityManager() : null;
    }

    public synchronized void setEntityManager(EntityManagerFactory emf) {
        this.emf = emf;
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
