package de.twenty11.skysail.server.ext.bookmarks.resources;

import java.util.List;

import org.restlet.data.Form;
import org.restlet.resource.Get;

import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.AddServerResource2;
import de.twenty11.skysail.server.ext.bookmarks.BookmarkApplication;
import de.twenty11.skysail.server.ext.bookmarks.domain.Bookmark;

public class AddBookmarkResource extends AddServerResource2<Bookmark> {

    @Override
    @Get("html")
    public FormResponse<Bookmark> createForm() {
        return new FormResponse<Bookmark>(new Bookmark(), "../bookmark/");
    }

    @Override
    public Bookmark getData(Form form) {
        return new Bookmark(form.getFirstValue("name"), form.getFirstValue("url"));
    }

    @Override
    public SkysailResponse<List<Bookmark>> addEntity(Bookmark entity) {
        BookmarkApplication app = (BookmarkApplication) getApplication();
        app.getBookmarkRepository().add(entity);
        return new BookmarksResource(this).getEntities();
    }

}
