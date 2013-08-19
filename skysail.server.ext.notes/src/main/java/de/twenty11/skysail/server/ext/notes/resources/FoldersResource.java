package de.twenty11.skysail.server.ext.notes.resources;

import java.util.List;

import org.restlet.data.Form;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.responses.SuccessResponse;
import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twenty11.skysail.server.ext.notes.NotesApplication;
import de.twenty11.skysail.server.ext.notes.domain.Folder;

public class FoldersResource extends ListServerResource2<Folder> {

    @Override
    protected List<Folder> getData() {
        NotesApplication app = (NotesApplication) getApplication();
        return app.getFolderRepository().getComponents();
    }

    @Override
    public Folder getData(Form form) {
        return new Folder(null, form.getFirstValue("folderName"));
    }

    @Override
    public SkysailResponse<?> addEntity(Folder entity) {
        NotesApplication app = (NotesApplication) getApplication();
        app.getFolderRepository().add(entity);
        return new SuccessResponse<Folder>(entity);
    }

    // EntityDetailsResponse response = new EntityDetailsResponse(entity, "folder");
    // response.setMessage("folder entity details");
    // return response;
    // }

}
