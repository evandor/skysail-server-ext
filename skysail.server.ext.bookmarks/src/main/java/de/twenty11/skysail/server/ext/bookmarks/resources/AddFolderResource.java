package de.twenty11.skysail.server.ext.bookmarks.resources;

import org.restlet.data.Form;
import org.restlet.resource.Get;

import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.responses.SuccessResponse;
import de.twenty11.skysail.server.ext.bookmarks.BookmarkApplication;
import de.twenty11.skysail.server.ext.bookmarks.domain.Folder;
import de.twenty11.skysail.server.restlet.AddServerResource;

public class AddFolderResource extends AddServerResource<Folder> {

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
    public SkysailResponse<Folder> addEntity(Folder entity) {
        BookmarkApplication app = (BookmarkApplication) getApplication();
        app.getFolderRepository().add(entity);
        return new SuccessResponse<Folder>();
    }

}
