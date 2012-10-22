package de.twenty11.skysail.server.ext.dbviewer;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.MapData;
import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.ext.dbviewer.RestfulConnection;
import de.twenty11.skysail.common.responses.FailureResponse;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.common.responses.SkysailFailureResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.responses.SkysailSuccessResponse;
import de.twenty11.skysail.common.responses.SuccessResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.Connections;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;
import de.twenty11.skysail.server.restlet.SkysailServerResource;

public class ConnectionResource extends SkysailServerResource<MapData> implements RestfulConnection {

    private String connectionName;
    private Connections connections;

    public ConnectionResource() {
        super(null);
    }

    public ConnectionResource(MapData data) {
        super(data);
    }

    @Override
    protected void doInit() throws ResourceException {
        connectionName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.CONNECTION_NAME);
        connections = ((SkysailApplication) getApplication()).getConnections();
    }

    @Override
    @Get
    public Response<MapData> getConnection() {
        Response<MapData> response;
        try {
            response = new SuccessResponse<MapData>(getFilteredData());
            response.setMessage("Details for connection '" + connectionName + "'");
        } catch (Exception e) {
            //logger.error(e.getMessage(), e);
            response = new FailureResponse<MapData>(e);
        }
        return response;
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
        ConnectionDetails deletedConnection = connections.delete(connectionName);
        if (deletedConnection != null) {
            response = new SkysailSuccessResponse<MapData>();
            response.setMessage("deleted one entry");
        } else {
            response = new SkysailFailureResponse<MapData>();
            response.setMessage("no entry found to delete");
        }
        return new JacksonRepresentation<SkysailResponse<MapData>>(response);
    }

    @Override
    public MapData getFilteredData() {
        MapData data = new MapData();
        // String connectionName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.CONNECTION_NAME);
        ConnectionDetails connectionDetails = connections.get(connectionName);
        if (connectionDetails != null) {
            Map<String, String> members = connectionDetails.toMap();
            for (Entry<String, String> entry : members.entrySet()) {
                data.put(entry.getKey(), entry.getValue());
            }
        }
        return data;
    }

}
