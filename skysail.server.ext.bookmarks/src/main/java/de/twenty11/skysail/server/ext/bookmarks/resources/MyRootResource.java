package de.twenty11.skysail.server.ext.bookmarks.resources;

import java.util.List;

import org.restlet.resource.Get;

import de.twenty11.skysail.common.navigation.LinkedPage;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.bookmarks.BookmarkApplication;
import de.twenty11.skysail.server.ext.bookmarks.domain.Bookmark;
import de.twenty11.skysail.server.restlet.ListServerResource2;

/**
 * Restlet Root Resource for dbViewer application.
 * 
 */
public class MyRootResource extends ListServerResource2<Bookmark> {

    public MyRootResource() {
        setAutoDescribing(false);
        setName("bookmarks root resource");
        setDescription("The root resource of the bookmarks application");
    }

    @Override
    @Get("html|json|csv")
    public SkysailResponse<List<Bookmark>> getEntities() {
        return getEntities("Bookmarks Manager");
    }

    @Override
    protected List<Bookmark> getData() {
        registerAddBookmarkPage();
        registerAddFolderPage();
        BookmarkApplication app = (BookmarkApplication) getApplication();
        return app.getRepository().getAll();
    }

    private void registerAddBookmarkPage() {
        registerLinkedPage(new LinkedPage() {
            @Override
            public boolean applicable() {
                return true;
            }

            @Override
            public String getHref() {
                return "bookmarks/bookmark/";
            }

            @Override
            public String getLinkText() {
                return "Add Bookmark...";
            }
        });
    }

    private void registerAddFolderPage() {
        registerLinkedPage(new LinkedPage() {
            @Override
            public boolean applicable() {
                return true;
            }

            @Override
            public String getHref() {
                return ((BookmarkApplication) getApplication()).getLinkTo(getReference(), AddBookmarkResource.class);
            }

            @Override
            public String getLinkText() {
                return "Add Folder...";
            }
        });
    }

}
