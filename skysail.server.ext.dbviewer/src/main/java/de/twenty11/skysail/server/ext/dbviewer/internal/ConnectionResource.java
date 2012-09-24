package de.twenty11.skysail.server.ext.dbviewer.internal;

import org.restlet.data.MediaType;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

import de.twenty11.skysail.common.maps.MapData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.restlet.SkysailServerResource;

public class ConnectionResource extends SkysailServerResource<MapData> {

    public ConnectionResource() {
        super(null);
    }

    public ConnectionResource(MapData data) {
        super(data);
    }

    @Override
    public void setResponseDetails(SkysailResponse<MapData> response, MediaType mediaType) {
    }

    @Override
    public MapData getFilteredData() {
        MapData data = new MapData();

        return data;
    }

    @Get("json")
    public Representation getJson() {
        SkysailResponse<MapData> response = createResponse();
        setResponseDetails(response, MediaType.APPLICATION_JSON);
        return new JacksonRepresentation<SkysailResponse<MapData>>(response);
    }

}
