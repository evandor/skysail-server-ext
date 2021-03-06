package de.twenty11.skysail.server.ext.bookmarks;

import javax.persistence.EntityManagerFactory;

import org.restlet.Context;

import de.twenty11.skysail.server.core.restlet.RouteBuilder;
import de.twenty11.skysail.server.ext.bookmarks.repository.BookmarkRepository;
import de.twenty11.skysail.server.ext.bookmarks.repository.FolderRepository;
import de.twenty11.skysail.server.ext.bookmarks.resources.AddBookmarkResource;
import de.twenty11.skysail.server.ext.bookmarks.resources.AddFolderResource;
import de.twenty11.skysail.server.ext.bookmarks.resources.BookmarksAndFoldersResource;
import de.twenty11.skysail.server.restlet.SkysailApplication;

/**
 * @author carsten
 * 
 */
public class BookmarkApplication extends SkysailApplication {

    private BookmarkRepository bookmarkRepository;
    private FolderRepository folderRepository;

    /**
     * @param componentContext
     * @param emf
     */
    public BookmarkApplication(Context componentContext, EntityManagerFactory emf) {
        super();
        if (getContext() != null) {
            setContext(getContext().createChildContext());
        }
        setDescription("RESTful Jenkins bundle");
        setOwner("twentyeleven");
        setName("bookmarks");
        bookmarkRepository = new BookmarkRepository(emf);
        folderRepository = new FolderRepository(emf);

    }

    protected void attach() {
        // @formatter:off
        router.attach(new RouteBuilder("", BookmarksAndFoldersResource.class).setVisible(false));
        router.attach(new RouteBuilder("/bookmark/", AddBookmarkResource.class).setVisible(false));
        router.attach(new RouteBuilder("/folder/", AddFolderResource.class).setVisible(false));
        //router.attach(new RouteBuilder("/baf/", BookmarksAndFoldersResource.class).setVisible(false));

        // @formatter:on
    }

    public BookmarkRepository getBookmarkRepository() {
        return bookmarkRepository;
    }

    public FolderRepository getFolderRepository() {
        return folderRepository;
    }

}
