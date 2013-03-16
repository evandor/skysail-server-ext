package de.twenty11.skysail.server.ext.dbviewer;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;

import org.restlet.data.Form;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.ext.dbviewer.RestfulConnections;
import de.twenty11.skysail.common.forms.ConstraintViolations;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerApplication;
import de.twenty11.skysail.server.ext.dbviewer.links.ConnectionPage;
import de.twenty11.skysail.server.restlet.ListServerResource;

/**
 * Restlet Resource class for handling Connections.
 * 
 * Provides a method to retrieve the existing connections and to add a new one.
 * 
 * The managed entity is of type {@link ConnectionDetails}, providing details (like jdbc url, username and password
 * about what is needed to actually connect to a datasource.
 * 
 */
public class ConnectionsResource extends ListServerResource<ConnectionDetails> implements RestfulConnections {

    /** slf4j based logger implementation */
    private static Logger logger = LoggerFactory.getLogger(ConnectionsResource.class);

    public ConnectionsResource() {
        setName("dbviewer connections resource");
        setDescription("The resource containing the list of connections");
    }

    // @Override
    @Get("html|json")
    public SkysailResponse<List<ConnectionDetails>> getConnections() {
        registerLinkedPage(new ConnectionPage());
        List<ConnectionDetails> allConnections = allConnections();
        return getEntities(allConnections, "all Connections (" + allConnections.size() + ")");
    }

    @SuppressWarnings("unchecked")
    private List<ConnectionDetails> allConnections() {
        EntityManager em = ((DbViewerApplication) getApplication()).getEntityManager();
        return em.createQuery("SELECT c FROM ConnectionDetails c").getResultList();
    }

    // @Get("putform")
    // public SkysailResponse<ConnectionDetails> formWithPut() {
    // ConnectionDetails connectionDetails = new ConnectionDetails();
    // return new SuccessResponse<ConnectionDetails>(connectionDetails);
    // }

    @Post("x-www-form-urlencoded")
    public SkysailResponse<ConstraintViolations<ConnectionDetails>> addConnection(Form form) {
        logger.info("trying to persist connection");
        EntityManager em = ((DbViewerApplication) getApplication()).getEntityManager();
        ConnectionDetails details = new ConnectionDetails(form.getFirstValue("username").toString(), form.getFirst(
                "username").toString(), form.getFirst("password").toString(), form.getFirst("driverName").toString(),
                form.getFirst("url").toString());
        Set<ConstraintViolation<ConnectionDetails>> constraintViolations = getValidator().validate(details);
        ConstraintViolations<ConnectionDetails> violations = new ConstraintViolations<ConnectionDetails>(
                constraintViolations);
        return addEntity(em, details, violations);
    }

    @Override
    @Post("html")
    public SkysailResponse<ConstraintViolations<ConnectionDetails>> addConnection(ConnectionDetails entity) {
        logger.info("trying to persist connection {}", entity);
        EntityManager em = ((DbViewerApplication) getApplication()).getEntityManager();
        Set<ConstraintViolation<ConnectionDetails>> constraintViolations = getValidator().validate(entity);
        ConstraintViolations<ConnectionDetails> violations = new ConstraintViolations<ConnectionDetails>(
                constraintViolations);
        return addEntity(em, entity, violations);
    }

}
