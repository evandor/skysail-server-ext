package de.twenty11.skysail.server.ext.notes.resources;

import org.restlet.data.Form;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.common.responses.EntityDetailsResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.UniqueResultServerResource2;
import de.twenty11.skysail.server.ext.notes.NotesApplication;
import de.twenty11.skysail.server.ext.notes.domain.Note;

@Presentation(preferred = PresentationStyle.LIST2)
public class NoteResource extends UniqueResultServerResource2<Note> {

    private int noteId;

    @Override
    protected void doInit() throws ResourceException {
        // noteId = Long.valueOf((String) getRequest().getAttributes().get("id"));
        noteId = Integer.valueOf((String) getRequest().getAttributes().get("id"));
    }

    @Override
    public SkysailResponse<Note> getEntity() {
        return super.getEntity("Note #" + noteId);
    }

    @Override
    protected Note getData() {
        NotesApplication app = (NotesApplication) getApplication();
        return app.getNotesRepository().getById(new Long(noteId));
    }

    @Override
    public Note getData(Form form) {
        return new Note(null, form.getFirstValue("title"), form.getFirstValue("content"));
    }

    // @Override
    // TODO check addNoteResource
    @Post("x-www-form-urlencoded:html")
    public EntityDetailsResponse addEntity2(Form form) {

        Note entity = getData(form);
        // Set<ConstraintViolation<T>> violations = validate(entity);
        // if (violations.size() > 0) {
        // return new ConstraintViolationsResponse(entity, violations);
        // }
        // return addEntity(entity);

        NotesApplication app = (NotesApplication) getApplication();
        app.getNotesRepository().add(entity);
        // return new FoldersResource().getEntities();
        EntityDetailsResponse response = new EntityDetailsResponse(entity, "note");
        response.setMessage("note entity details");
        return response;
    }

    @Put("x-www-form-urlencoded:html")
    public EntityDetailsResponse put(Form form) {

        Note entity = getData(form);
        entity.setPid(noteId);
        // Set<ConstraintViolation<T>> violations = validate(entity);
        // if (violations.size() > 0) {
        // return new ConstraintViolationsResponse(entity, violations);
        // }
        // return addEntity(entity);

        NotesApplication app = (NotesApplication) getApplication();
        app.getNotesRepository().update(entity);
        // return new FoldersResource().getEntities();
        EntityDetailsResponse response = new EntityDetailsResponse(entity, "note");
        response.setMessage("note entity details");
        return response;
    }

    @Override
    public SkysailResponse<?> addEntity(Note entity) {
        // TODO Auto-generated method stub
        return null;
    }

}
