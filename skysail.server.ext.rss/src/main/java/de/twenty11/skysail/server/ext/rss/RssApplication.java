package de.twenty11.skysail.server.ext.rss;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.restlet.routing.Router;
import org.restlet.routing.Template;

import de.twenty11.skysail.server.config.ServerConfiguration;
import de.twenty11.skysail.server.core.restlet.RouteBuilder;
import de.twenty11.skysail.server.ext.rss.resources.RssResource;
import de.twenty11.skysail.server.restlet.SkysailApplication;
import de.twenty11.skysail.server.services.ApplicationProvider;
import de.twenty11.skysail.server.services.MenuEntry;
import de.twenty11.skysail.server.services.MenuProvider;

/**
 * The restlet application defined in this bundle.
 * 
 */
public class RssApplication extends SkysailApplication implements ApplicationProvider, MenuProvider {

    public RssApplication() {
        setDescription("RESTful skysail.server.ext.rss bundle");
        setOwner("twentyeleven");
        setName("rss");
    }

    protected void attach() {
        // make sure to match proper resource even if request url contains add. information
        router.setDefaultMatchingMode(Template.MODE_STARTS_WITH);
        router.setRoutingMode(Router.MODE_LAST_MATCH);

        // @formatter:off
        router.attach(new RouteBuilder("", RssResource.class).setVisible(true));
        // @formatter:on
    }

    public void setServerConfiguration(ServerConfiguration sc) {
        super.setServerConfiguration(sc);
    }

    @Override
    public List<MenuEntry> getMenuEntries() {
        return Arrays.asList(new MenuEntry("main", "RSS", "rss"));
    }

}
