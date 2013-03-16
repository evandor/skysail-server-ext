package de.twenty11.skysail.server.ext.dbviewer;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.ext.dbviewer.RestfulConnection;
import de.twenty11.skysail.common.forms.ConstraintViolations;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.responses.SuccessResponse;
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
    public SkysailResponse<ConnectionDetails> getConnection() {
        ConnectionDetails connectionDetails = new ConnectionDetails();
        setMessage("Adding new Connection");
        return new SuccessResponse<ConnectionDetails>(connectionDetails);
    }

    @Override
    @Put
    public SkysailResponse<ConstraintViolations<ConnectionDetails>> updateConnection(ConnectionDetails connection) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Delete
    public SkysailResponse<String> deleteConnection() {
        // TODO Auto-generated method stub
        return null;
    }

    // @Override
    // @Post
    // public SkysailResponse<ConstraintViolations<ConnectionDetails>> addConnection(ConnectionDetails entity) {
    // logger.info("trying to persist connection {}", entity);
    // EntityManager em = ((DbViewerApplication) getApplication()).getEntityManager();
    // Set<ConstraintViolation<ConnectionDetails>> constraintViolations = getValidator().validate(entity);
    // ConstraintViolations<ConnectionDetails> violations = new ConstraintViolations<ConnectionDetails>(
    // constraintViolations);
    // return addEntity(em, entity, violations);
    // }

}
