package de.twenty11.skysail.server.ext.notes.resources;

import java.util.List;

import org.restlet.data.Form;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.AddServerResource2;
import de.twenty11.skysail.server.ext.notes.NotesApplication;
import de.twenty11.skysail.server.ext.notes.domain.Component;
import de.twenty11.skysail.server.ext.notes.domain.Note;

public class AddNoteResource extends AddServerResource2<Note> {

    @Override
    @Get("html")
    public FormResponse<Note> createForm() {
        FormResponse<Note> formResponse = new FormResponse<Note>(new Note(null, null, ""), "note");
        formResponse.setMessage("Add a new Note");
        return formResponse;
    }

    @Override
    public Note getData(Form form) {
        return new Note(null, form.getFirstValue("title"), form.getFirstValue("content"));
    }

    @Override
    @Post("x-www-form-urlencoded:html")
    public SkysailResponse<List<Component>> addEntity(Note entity) {
        NotesApplication app = (NotesApplication) getApplication();
        app.getNotesRepository().add(entity);
        return new FoldersResource().getEntities();
    }

}
