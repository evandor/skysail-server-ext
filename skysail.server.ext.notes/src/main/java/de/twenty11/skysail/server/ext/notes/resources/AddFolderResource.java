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
import de.twenty11.skysail.server.ext.notes.domain.Folder;

public class AddFolderResource extends AddServerResource2<Folder> {

    @Override
    @Get("html")
    public FormResponse<Folder> createForm() {
        FormResponse<Folder> formResponse = new FormResponse<Folder>(new Folder(null, ""), "folder");
        formResponse.setMessage("Add a new folder");
        return formResponse;
    }

    @Override
    public Folder getData(Form form) {
        return new Folder(null, form.getFirstValue("folderName"));
    }

    @Override
    @Post("x-www-form-urlencoded:html")
    public SkysailResponse<List<Component>> addEntity(Folder entity) {
        NotesApplication app = (NotesApplication) getApplication();
        app.getFolderRepository().add(entity);
        return new FoldersResource().getEntities();
    }

    // @Post("x-www-form-urlencoded:html")
    // public SkysailResponse<Folder> addConnection(Form form) {
    // logger.info("trying to persist connection");
    // EntityManager em = ((DbViewerApplication) getApplication()).getEntityManager();
    // ConnectionDetails details = new ConnectionDetails(form.getFirstValue("username"),
    // form.getFirstValue("username"), form.getFirstValue("password"), form.getFirstValue("url"),
    // form.getFirstValue("driverName"));
    // Set<ConstraintViolation<ConnectionDetails>> constraintViolations = getValidator().validate(details);
    // return addEntity(em, details, constraintViolations);
    // }

}
