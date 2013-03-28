package de.twenty11.skysail.server.ext.dbviewer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.ext.dbviewer.RestfulConnection;
import de.twenty11.skysail.common.responses.FailureResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerApplication;
import de.twenty11.skysail.server.restlet.ListServerResource;

public class ConnectionResource extends ListServerResource<ConnectionDetails> implements RestfulConnection {

    private String connectionName;
    private EntityManager em;

    /** slf4j based logger implementation */
    private static Logger logger = LoggerFactory.getLogger(ConnectionResource.class);

    public ConnectionResource() {
        setName("dbviewer connection resource");
        setDescription("The resource describing a single connection");
    }

    @Override
    protected void doInit() throws ResourceException {
        connectionName = (String) getRequest().getAttributes().get(Constants.CONNECTION_NAME);
        setName("dbviewer connection resource for " + connectionName);
        em = ((DbViewerApplication) getApplication()).getEntityManager();
    }

    @Override
    @Get("html|json")
    public SkysailResponse<ConnectionDetails> getConnection() {
        TypedQuery<ConnectionDetails> query = em.createQuery("SELECT c FROM ConnectionDetails c WHERE c.name = :name",
                ConnectionDetails.class);
        query.setParameter("name", connectionName);
        List<ConnectionDetails> resultList = query.getResultList();
        if (resultList.size() == 0) {
            return new FailureResponse<ConnectionDetails>("could not find connection for name '" + connectionName +"'");
        }
        return getEntity(resultList.get(0));
    }

    // @Override
    // @Put
    // public SkysailResponse<ConstraintViolations<ConnectionDetails>> updateConnection(ConnectionDetails connection) {
    // // TODO Auto-generated method stub
    // return null;
    // }

    @Override
    @Delete
    public SkysailResponse<String> deleteConnection() {
        SkysailResponse<ConnectionDetails> connectionResponse = getConnection();
        return deleteEntity(em, connectionResponse.getData());
    }
}
