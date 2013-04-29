//package de.twenty11.skysail.server.ext.bookmarks.resources;
//
//import java.util.List;
//
//import de.twenty11.skysail.server.ext.bookmarks.BookmarkApplication;
//import de.twenty11.skysail.server.ext.bookmarks.domain.Bookmark;
//import de.twenty11.skysail.server.restlet.ListServerResource2;
//
//public class BookmarksResource extends ListServerResource2<Bookmark> {
//
//    @Override
//    protected List<Bookmark> getData() {
//        BookmarkApplication app = (BookmarkApplication) getApplication();
//        return app.getRepository().getAll();
//    }
//
// }
