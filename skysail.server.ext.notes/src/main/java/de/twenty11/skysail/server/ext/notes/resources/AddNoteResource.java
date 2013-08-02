package de.twenty11.skysail.server.ext.notes.resources;

import org.restlet.data.Form;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import de.twenty11.skysail.common.responses.EntityDetailsResponse;
import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.AddServerResource2;
import de.twenty11.skysail.server.ext.notes.NotesApplication;
import de.twenty11.skysail.server.ext.notes.domain.Note;

public class AddNoteResource extends AddServerResource2<Note> {

    @Get("json")
    // TODO use options instead?
    public EntityDetailsResponse jsonFormDetails() {
        EntityDetailsResponse response = new EntityDetailsResponse(new Note(null, null, ""), "note/");
        response.setMessage("note entity details");
        return response;
    }

    @Override
    @Get("html")
    public FormResponse<Note> createForm() {
        FormResponse<Note> formResponse = new FormResponse<Note>(new Note(null, null, ""), "note/");
        formResponse.setMessage("Add a new Note");
        return formResponse;
    }

    @Override
    public Note getData(Form form) {
        return new Note(null, form.getFirstValue("title"), form.getFirstValue("content"));
    }

    // @Override
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

    @Override
    public SkysailResponse<?> addEntity(Note entity) {
        // TODO Auto-generated method stub
        return null;
    }

}
