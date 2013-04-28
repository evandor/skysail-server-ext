package de.twenty11.skysail.server.ext.bookmarks.resources;

import org.restlet.data.Form;
import org.restlet.resource.Get;

import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.responses.SuccessResponse;
import de.twenty11.skysail.server.ext.bookmarks.BookmarkApplication;
import de.twenty11.skysail.server.ext.bookmarks.domain.Bookmark;
import de.twenty11.skysail.server.restlet.AddServerResource;

public class AddBookmarkResource extends AddServerResource<Bookmark> {

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
    public SkysailResponse<Bookmark> addEntity(Bookmark entity) {
        BookmarkApplication app = (BookmarkApplication) getApplication();
        app.getRepository().addBookmark(entity);
        return new SuccessResponse<Bookmark>();
    }

}
