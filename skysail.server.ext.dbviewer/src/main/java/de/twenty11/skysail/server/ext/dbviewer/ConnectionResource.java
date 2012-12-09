package de.twenty11.skysail.server.ext.dbviewer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.ext.dbviewer.RestfulConnection;
import de.twenty11.skysail.common.forms.ConstraintViolations;
import de.twenty11.skysail.common.responses.FailureResponse;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerApplication;
import de.twenty11.skysail.server.restlet.ListServerResource;

public class ConnectionResource extends ListServerResource<ConnectionDetails> implements RestfulConnection {

    private String connectionName;
    private EntityManager em;

    public ConnectionResource() {
        setName("dbviewer connection resource");
        setDescription("The resource describing a single connection");
    }

    @Override
    protected void doInit() throws ResourceException {
        connectionName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.CONNECTION_NAME);
        setName("dbviewer connection resource for " + connectionName);
        em = ((DbViewerApplication) getApplication()).getEntityManager();
    }

    @Override
    @Get
    public Response<ConnectionDetails> getConnection() {
        TypedQuery<ConnectionDetails> query = em.createQuery("SELECT c FROM ConnectionDetails c WHERE c.name = :name",
                ConnectionDetails.class);
        query.setParameter("name", connectionName);
        List<ConnectionDetails> resultList = query.getResultList();
        if (resultList.size() == 0) {
            return new FailureResponse<ConnectionDetails>("could not find connection for name '" + connectionName +"'");
        }
        return getEntity(resultList.get(0));
    }

    @Override
    @Put
    public Response<ConstraintViolations<ConnectionDetails>> updateConnection(ConnectionDetails connection) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Delete
    public Response<String> deleteConnection() {
        Response<ConnectionDetails> connectionResponse = getConnection();
        return deleteEntity(em, connectionResponse.getData());
    }
}
