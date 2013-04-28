package de.twenty11.skysail.server.ext.bookmarks.resources;

import java.util.List;

import de.twenty11.skysail.server.restlet.ListServerResource2;

public class BookmarksResource extends ListServerResource2<BookmarkNode> {

    @Override
    protected List<BookmarkNode> getData() {
        return null;
    }

}
