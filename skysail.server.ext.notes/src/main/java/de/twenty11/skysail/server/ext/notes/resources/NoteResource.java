package de.twenty11.skysail.server.ext.notes.resources;

import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.server.core.restlet.UniqueResultServerResource2;
import de.twenty11.skysail.server.ext.notes.NotesApplication;
import de.twenty11.skysail.server.ext.notes.domain.Note;

@Presentation(preferred = PresentationStyle.LIST2)
public class NoteResource extends UniqueResultServerResource2<Note> {

    private Long noteId;

    @Override
    protected void doInit() throws ResourceException {
        noteId = Long.valueOf((String) getRequest().getAttributes().get("id"));

    }

    @Override
    protected Note getData() {
        NotesApplication app = (NotesApplication) getApplication();
        return app.getNotesRepository().getById(noteId);
    }

}
