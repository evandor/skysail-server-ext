package de.twenty11.skysail.server.ext.dbviewer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.restlet.resource.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
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
public class ConnectionsResource extends ListServerResource<ConnectionDetails> {

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
        return getEntities(allConnections, augmentWithFilterMsg("all Connections (" + allConnections.size() + ")"));
    }

    @SuppressWarnings("unchecked")
    private List<ConnectionDetails> allConnections() {
        EntityManager em = ((DbViewerApplication) getApplication()).getEntityManager();
        List<ConnectionDetails> resultList = em.createQuery("SELECT c FROM ConnectionDetails c").getResultList();
        List<ConnectionDetails> filteredResults = new ArrayList<ConnectionDetails>();
        for (ConnectionDetails details : resultList) {
            if (filterMatches(details)) {
                filteredResults.add(details);
            }
        }
        return filteredResults;
    }

    @Override
    protected boolean match(ConnectionDetails object, String pattern) {
        return object.getName().contains(pattern);
    }

    // @Override
    // @Post("html")
    // public SkysailResponse<ConstraintViolations<ConnectionDetails>> addConnection(ConnectionDetails entity) {
    // logger.info("trying to persist connection {}", entity);
    // EntityManager em = ((DbViewerApplication) getApplication()).getEntityManager();
    // Set<ConstraintViolation<ConnectionDetails>> constraintViolations = getValidator().validate(entity);
    // ConstraintViolations<ConnectionDetails> violations = new ConstraintViolations<ConnectionDetails>(
    // constraintViolations);
    // // return addEntity(em, entity, violations);
    //
    // return null;// TO BE DONE
    // }

}
