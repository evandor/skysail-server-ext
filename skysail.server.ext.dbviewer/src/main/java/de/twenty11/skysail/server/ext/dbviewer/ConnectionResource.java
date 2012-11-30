package de.twenty11.skysail.server.ext.dbviewer;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.MapData;
import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.ext.dbviewer.RestfulConnection;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.common.responses.SkysailFailureResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.responses.SkysailSuccessResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;
import de.twenty11.skysail.server.restlet.ListServerResource;

public class ConnectionResource extends ListServerResource<ConnectionDetails> implements RestfulConnection {

    private String connectionName;

    @Override
    protected void doInit() throws ResourceException {
        connectionName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.CONNECTION_NAME);
        setName("dbviewer connection resource for " + connectionName);
    }

    @Override
    @Get
    public Response<ConnectionDetails> getConnection() {
        EntityManager em = ((SkysailApplication) getApplication()).getEntityManager();
        TypedQuery<ConnectionDetails> query = em.createQuery("SELECT c FROM ConnectionDetails c WHERE c.name = :name",
                ConnectionDetails.class);
        query.setParameter("name", connectionName);
        return getEntity(query.getSingleResult());
//        if (result.size() > 0)
//            getEntity(result.get(0));
////        Response<ConnectionDetails> response;
////        try {
////            response = new SuccessResponse<ConnectionDetails>(getFilteredData());
////            response.setMessage("Details for connection '" + connectionName + "'");
////        } catch (Exception e) {
////            response = new FailureResponse<ConnectionDetails>(e);
////        }
////        return response;
    }

    @Override
    @Put
    public Response<MapData> updateConnection(ConnectionDetails connection) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Delete
    public Representation delete() {
        SkysailResponse<MapData> response;
        ConnectionDetails connection = ((SkysailApplication) getApplication()).getConnectionByName(connectionName);
        try {
            EntityManager em = ((SkysailApplication) getApplication()).getEntityManager();
            em.remove(connection);
            response = new SkysailSuccessResponse<MapData>();
            response.setMessage("deleted one entry");
        } catch (Exception e) {
            response = new SkysailFailureResponse<MapData>();
            response.setMessage("no entry found to delete");
        }
        return new JacksonRepresentation<SkysailResponse<MapData>>(response);
    }

//    @Override
//    public void buildGrid() {
//        setMessage("all Connections");
//        EntityManager em = ((SkysailApplication) getApplication()).getEntityManager();
//        TypedQuery<ConnectionDetails> query = em.createQuery("SELECT c FROM ConnectionDetails c WHERE c.name = :name",
//                ConnectionDetails.class);
//        query.setParameter("name", connectionName);
//        List<ConnectionDetails> result = query.getResultList();
//        if (result.size() > 0)
//            setSkysailData(result.get(0));
//    }

}
