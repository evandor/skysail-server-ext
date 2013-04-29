package de.twenty11.skysail.server.ext.bookmarks;

import javax.persistence.EntityManagerFactory;

import org.restlet.Context;

import de.twenty11.skysail.server.ext.bookmarks.repository.BookmarkRepository;
import de.twenty11.skysail.server.ext.bookmarks.resources.AddBookmarkResource;
import de.twenty11.skysail.server.ext.bookmarks.resources.MyRootResource;
import de.twenty11.skysail.server.restlet.RouteBuilder;
import de.twenty11.skysail.server.restlet.SkysailApplication;

/**
 * @author carsten
 * 
 */
public class BookmarkApplication extends SkysailApplication {

    private BookmarkRepository repository;

    /**
     * @param componentContext
     * @param emf
     */
    public BookmarkApplication(Context componentContext, EntityManagerFactory emf) {
        super(componentContext == null ? null : componentContext.createChildContext());
        setDescription("RESTful Jenkins bundle");
        setOwner("twentyeleven");
        setName("bookmarks");
        repository = new BookmarkRepository(emf);

    }

    protected void attach() {
        // @formatter:off
        router.attach(new RouteBuilder("", MyRootResource.class).setVisible(false));
        router.attach(new RouteBuilder("/bookmark/", AddBookmarkResource.class).setVisible(false));
//        router.attach(new RouteBuilder("/folder/", AddBookmarkResource.class).setText("Bookmarks Manager"));
        
        // @formatter:on
    }

    public BookmarkRepository getRepository() {
        return repository;
    }

}
