package de.twenty11.skysail.server.ext.notes.resources;

import java.util.List;

import org.restlet.data.Form;

import de.twenty11.skysail.common.responses.SkysailResponse;
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
        return null;
    }

    // @Post("x-www-form-urlencoded:html")
    // public SkysailResponse<ConnectionDetails> addConnection(Form form) {
    // logger.info("trying to persist connection");
    // EntityManager em = ((DbViewerApplication) getApplication()).getEntityManager();
    // ConnectionDetails details = new ConnectionDetails(form.getFirstValue("username"), form.getFirstValue(
    // "username"), form.getFirstValue("password"), form.getFirstValue("url"),
    // form.getFirstValue("driverName"));
    // Set<ConstraintViolation<ConnectionDetails>> constraintViolations = getValidator().validate(details);
    // return addEntity(em, details, constraintViolations);
    // }

    // @Post("x-www-form-urlencoded:html")
    // public EntityDetailsResponse addEntity2(Form form) {
    //
    // Folder entity = getData(form);
    // Set<ConstraintViolation<T>> violations = validate(entity);
    // if (violations.size() > 0) {
    // return new ConstraintViolationsResponse(entity, violations);
    // }
    //
    // // Set<ConstraintViolation<ConnectionDetails>> constraintViolations = getValidator().validate(details);
    // // return addEntity(em, details, constraintViolations);
    //
    // return addEntity(entity);
    //
    // NotesApplication app = (NotesApplication) getApplication();
    // app.getFolderRepository().add(entity);
    // // return new FoldersResource().getEntities();
    // EntityDetailsResponse response = new EntityDetailsResponse(entity, "folder");
    // response.setMessage("folder entity details");
    // return response;
    // }

}
