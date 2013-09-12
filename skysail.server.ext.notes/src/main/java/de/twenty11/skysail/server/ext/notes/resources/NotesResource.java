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
import de.twenty11.skysail.server.ext.notes.domain.Note;

public class NotesResource extends ListServerResource<Note> {

    private NotesApplication app;

    public NotesResource() {
        setName("notes");
        setDescription("shows notes");
        app = (NotesApplication) getApplication();
    }

    @Override
    public String getMessage(String key) {
        return "Listing notes";
    }

    @Get("htmlform")
    public FormResponse<Note> createForm() {
        FormResponse<Note> formResponse = new FormResponse<Note>(new Note(null, "", ""),
                NotesApplication.getPostNewFolderPath());
        formResponse.setMessage("Add a new note");
        return formResponse;
    }

    @Override
    public List<Note> getData() {
        return app.getNotesRepository().getComponents();
    }

    @Override
    public List<Note> getData(Form form) {
        Note note = new Note(null, form.getFirstValue(Note.TITLE), form.getFirstValue(Note.CONTENT));
        note.setOwner(app.getCurrentUser());
        return Arrays.asList(note);
    }

    @Override
    public SkysailResponse<?> addEntity(List<Note> entities) {
        for (Note note : entities) {
            app.getNotesRepository().add(note);
        }
        return new SuccessResponse<Folder>(null);
    }

}
