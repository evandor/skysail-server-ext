package de.twenty11.skysail.server.ext.bookmarks.resources;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.bookmarks.BookmarkApplication;
import de.twenty11.skysail.server.ext.bookmarks.domain.Bookmark;
import de.twenty11.skysail.server.ext.bookmarks.domain.BookmarkOrFolder;
import de.twenty11.skysail.server.restlet.ListServerResource2;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.util.List;

/**
 * Restlet Root Resource for dbViewer application.
 * 
 */
public class BookmarksAndFoldersResource extends ListServerResource2<BookmarkOrFolder> {

    public BookmarksAndFoldersResource() {
        setAutoDescribing(false);
        setName("bookmarks root resource");
        setDescription("The root resource of the bookmarks application");
    }

    public BookmarksAndFoldersResource(ServerResource sr) {
        super(sr);
    }

    @Override
    @Get("html|json|csv")
    public SkysailResponse<List<BookmarkOrFolder>> getEntities() {
        addResourceLink("Add Bookmark", AddBookmarkResource.class);
        addResourceLink("Add Folder", AddFolderResource.class);
        return getEntities("Bookmarks Manager");
    }

    @Override
    protected List<BookmarkOrFolder> getData() {
        BookmarkApplication app = (BookmarkApplication) getApplication();
        return app.getBookmarkRepository().getAll(null);
    }

}
