package de.twenty11.skysail.server.ext.notes.resources;

import java.util.List;

import org.restlet.data.Form;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twenty11.skysail.server.ext.notes.NotesApplication;
import de.twenty11.skysail.server.ext.notes.domain.Note;

@Presentation(preferred = PresentationStyle.LIST2)
public class NotesResource extends ListServerResource2<Note> {

    public NotesResource() {
        setName("notes");
        setDescription("shows notes");
    }

    @Override
    public SkysailResponse<List<Note>> getEntities() {
        return super.getEntities("Notes");
    }

    @Override
    protected List<Note> getData() {
        NotesApplication app = (NotesApplication) getApplication();
        return app.getNotesRepository().getComponents();
    }

    @Override
    public Note getData(Form form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SkysailResponse<?> addEntity(Note entity) {
        // TODO Auto-generated method stub
        return null;
    }

}
