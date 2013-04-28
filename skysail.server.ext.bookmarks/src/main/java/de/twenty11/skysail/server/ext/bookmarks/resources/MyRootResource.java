package de.twenty11.skysail.server.ext.bookmarks.resources;

import java.util.ArrayList;
import java.util.List;

import org.restlet.resource.Get;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.restlet.ListServerResource2;

/**
 * Restlet Root Resource for dbViewer application.
 * 
 */
public class MyRootResource extends ListServerResource2<BookmarkNode> {

    public MyRootResource() {
        setAutoDescribing(false);
        setName("bookmarks root resource");
        setDescription("The root resource of the bookmarks application");
    }

    @Override
    @Get("html|json|csv")
    public SkysailResponse<List<BookmarkNode>> getEntities() {
        return getEntities("Bookmarks Manager");
    }

    @Override
    protected List<BookmarkNode> getData() {
        return new ArrayList<BookmarkNode>();
    }
}
