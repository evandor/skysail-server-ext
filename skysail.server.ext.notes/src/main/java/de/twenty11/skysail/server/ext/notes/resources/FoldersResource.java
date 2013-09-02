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

    @Override
    public String getMessage(String key) {
        return "Listing folders";
    }

    @Override
    public List<Folder> getData() {
        NotesApplication app = (NotesApplication) getApplication();
        return app.getFolderRepository().getComponents();
    }

    @Override
    public List<Folder> getData(Form form) {
        return Arrays.asList(new Folder(null, form.getFirstValue("folderName")));
    }

    @Override
    public SkysailResponse<?> addEntity(Folder entity) {
        NotesApplication app = (NotesApplication) getApplication();
        app.getFolderRepository().add(entity);
        return new SuccessResponse<Folder>(entity);
    }

    @Get("htmlform")
    public FormResponse<Folder> createForm() {
        FormResponse<Folder> formResponse = new FormResponse<Folder>(new Folder(null, ""),
                NotesApplication.getPostNewFolderPath());
        formResponse.setMessage("Add a new folder");
        return formResponse;
    }

}
