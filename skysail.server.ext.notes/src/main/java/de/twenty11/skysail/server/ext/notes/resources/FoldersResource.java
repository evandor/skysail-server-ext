package de.twenty11.skysail.server.ext.notes.resources;

import java.util.Arrays;
import java.util.List;

import org.restlet.data.Form;

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

    @Override
    public List<Folder> getData(Form form) {
        Folder folder = new Folder(null, form.getFirstValue("folderName"));
        folder.setOwner(app.getCurrentUser());
        return Arrays.asList(folder);
    }

}
