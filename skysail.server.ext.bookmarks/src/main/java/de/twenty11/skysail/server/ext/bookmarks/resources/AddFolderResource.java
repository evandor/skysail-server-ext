package de.twenty11.skysail.server.ext.bookmarks.resources;

import java.util.List;

import org.restlet.data.Form;
import org.restlet.resource.Get;

import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.AddServerResource2;
import de.twenty11.skysail.server.ext.bookmarks.BookmarkApplication;
import de.twenty11.skysail.server.ext.bookmarks.domain.Bookmark;
import de.twenty11.skysail.server.ext.bookmarks.domain.Folder;

public class AddFolderResource extends AddServerResource2<Folder> {

    @Override
    @Get("html")
    public FormResponse<Folder> createForm() {
        FormResponse<Folder> formResponse = new FormResponse<Folder>(new Folder());
        formResponse.setMessage("Add a new folder");
        return formResponse;
    }

    @Override
    public Folder getData(Form form) {
        return new Folder(form.getFirstValue("name"), null);
    }

    @Override
    public SkysailResponse<List<Bookmark>> addEntity(Folder entity) {
        BookmarkApplication app = (BookmarkApplication) getApplication();
        app.getFolderRepository().add(entity);
        return new BookmarksResource(this).getEntities();
    }

}
