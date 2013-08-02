package de.twenty11.skysail.server.ext.notes.resources;

import java.util.List;

import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twenty11.skysail.server.ext.notes.NotesApplication;
import de.twenty11.skysail.server.ext.notes.domain.Folder;

public class FoldersResource extends ListServerResource2<Folder> {

    @Override
    protected List<Folder> getData() {
        NotesApplication app = (NotesApplication) getApplication();
        return app.getFolderRepository().getComponents();
    }

}
