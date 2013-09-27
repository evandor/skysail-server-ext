package de.twenty11.skysail.server.ext.notes.resources;

import java.util.Arrays;
import java.util.List;

import org.restlet.data.Form;
import org.restlet.resource.Get;

import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.responses.SuccessResponse;
import de.twenty11.skysail.server.core.restlet.ListServerResource;
import de.twenty11.skysail.server.ext.notes.NotesApplication;
import de.twenty11.skysail.server.ext.notes.domain.Folder;

public class FoldersResource extends ListServerResource<Folder> {

    private NotesApplication app;

    public FoldersResource() {
        app = (NotesApplication) getApplication();
    }

    @Override
    public String getMessage(String key) {
        return "Listing folders";
    }

    @Override
    public List<Folder> getData() {
        return app.getFolderRepository().getComponents();
    }

    @Get("htmlform")
    public FormResponse<Folder> createForm() {
        FormResponse<Folder> formResponse = new FormResponse<Folder>(new Folder(null, ""),
                NotesApplication.getPostNewFolderPath());
        formResponse.setMessage("Add a new folder");
        return formResponse;
    }

    @Override
    public List<Folder> getData(Form form) {
        Folder folder = new Folder(null, form.getFirstValue("folderName"));
        folder.setOwner(app.getCurrentUser());
        return Arrays.asList(folder);
    }

    @Override
    public SkysailResponse<?> addEntity(List<Folder> entities) {
        NotesApplication app = (NotesApplication) getApplication();
        for (Folder folder : entities) {
            app.getFolderRepository().add(folder);
        }
        return new SuccessResponse<Folder>(null);
    }

}
