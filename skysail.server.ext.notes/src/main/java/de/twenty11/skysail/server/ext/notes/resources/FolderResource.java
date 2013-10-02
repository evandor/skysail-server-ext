package de.twenty11.skysail.server.ext.notes.resources;

import org.restlet.data.Form;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.responses.SuccessResponse;
import de.twenty11.skysail.server.core.restlet.UniqueResultServerResource;
import de.twenty11.skysail.server.ext.notes.NotesApplication;
import de.twenty11.skysail.server.ext.notes.domain.Folder;

/**
 * takes care of ".../folders/" and ".../folders/{id}" requests.
 * 
 */
public class FolderResource extends UniqueResultServerResource<Folder> {

    private Long folderId;
    private NotesApplication app;

    public FolderResource() {
        app = (NotesApplication) getApplication();
    }

    @Override
    protected void doInit() throws ResourceException {
        if (getRequest().getAttributes().get("id") != null) {
            folderId = new Long((String) getRequest().getAttributes().get("id"));
        }
    }

    @Get("htmlform")
    public FormResponse<Folder> createForm() {
        FormResponse<Folder> formResponse = new FormResponse<Folder>(new Folder(null, ""),
                NotesApplication.getPostNewFolderPath());
        formResponse.setMessage("Add a new folder");
        return formResponse;
    }

    @Delete
    public void deleteFolder() {
        app.getFolderRepository().delete(folderId);
    }

    @Override
    public Folder getData() {
        return app.getFolderRepository().getById(folderId);
    }

    @Override
    public Folder getData(Form form) {
        Folder folder = new Folder(null, form.getFirstValue("folderName"));
        folder.setOwner(app.getCurrentUser());
        return folder;

    }

    @Override
    public SkysailResponse<?> addEntity(Folder entity) {
        app.getFolderRepository().add(entity);
        return new SuccessResponse<Folder>(entity);
    }

    @Override
    public String getMessage(String key) {
        return null;
    }

}
