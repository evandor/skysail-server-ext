package de.twenty11.skysail.server.ext.dbviewer;

import java.util.Map;
import java.util.Map.Entry;

import org.restlet.data.MediaType;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.maps.MapData;
import de.twenty11.skysail.common.responses.SkysailFailureResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.responses.SkysailSuccessResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.Connections;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.ConnectionDetails;
import de.twenty11.skysail.server.restlet.SkysailServerResource;

public class ConnectionResource extends SkysailServerResource<MapData> {

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
    public void setResponseDetails(SkysailResponse<MapData> response, MediaType mediaType) {
        response.setMessage("Details for connection '" + connectionName + "'");
    }

    @Override
    public MapData getFilteredData() {
        MapData data = new MapData();
        String connectionName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.CONNECTION_NAME);
        ConnectionDetails connectionDetails = connections.get(connectionName);
        if (connectionDetails != null) {
            Map<String, String> members = connectionDetails.toMap();
            for (Entry<String, String> entry : members.entrySet()) {
                data.put(entry.getKey(), entry.getValue());
            }
        }
        return data;
    }

    @Get("json")
    public Representation getJson() {
        SkysailResponse<MapData> response = createResponse();
        setResponseDetails(response, MediaType.APPLICATION_JSON);
        return new JacksonRepresentation<SkysailResponse<MapData>>(response);
    }

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

}
