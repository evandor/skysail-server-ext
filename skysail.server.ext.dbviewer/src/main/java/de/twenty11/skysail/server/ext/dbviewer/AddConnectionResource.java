package de.twenty11.skysail.server.ext.dbviewer;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;

import org.restlet.data.Form;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.ext.dbviewer.RestfulConnection;
import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerApplication;
import de.twenty11.skysail.server.restlet.UniqueResultServerResource;

@Presentation(preferred = PresentationStyle.EDIT)
public class AddConnectionResource extends UniqueResultServerResource<ConnectionDetails> implements RestfulConnection {

    /** slf4j based logger implementation */
    private static Logger logger = LoggerFactory.getLogger(AddConnectionResource.class);

    public AddConnectionResource() {
        setName("new connection ");
        setDescription("Providing a form to add a new Connection");
    }

    @Override
    @Get("html")
    public FormResponse<ConnectionDetails> getConnection() {
        ConnectionDetails connectionDetails = new ConnectionDetails();
        setMessage("Adding new Connection");
        return new FormResponse<ConnectionDetails>(connectionDetails, "../connections/");
    }

    @Post("x-www-form-urlencoded:html")
    public SkysailResponse<ConnectionDetails> addConnection(Form form) {
        logger.info("trying to persist connection");
        EntityManager em = ((DbViewerApplication) getApplication()).getEntityManager();
        ConnectionDetails details = new ConnectionDetails(form.getFirstValue("username"), form.getFirstValue(
                "username"), form.getFirstValue("password"), form.getFirstValue("url"),
                form.getFirstValue("driverName"));
        Set<ConstraintViolation<ConnectionDetails>> constraintViolations = getValidator().validate(details);
        return addEntity(em, details, constraintViolations);
    }

    // @Override
    // @Put
    // public SkysailResponse<ConstraintViolations<ConnectionDetails>> updateConnection(ConnectionDetails connection) {
    // return null;
    // }

    @Override
    @Delete
    public SkysailResponse<String> deleteConnection() {
        return null;
    }

}