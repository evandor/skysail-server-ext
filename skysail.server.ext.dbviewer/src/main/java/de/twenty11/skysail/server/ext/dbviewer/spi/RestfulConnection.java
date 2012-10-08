package de.twenty11.skysail.server.ext.dbviewer.spi;

import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

import de.twenty11.skysail.common.MapData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.ConnectionDetails;

/**
 * Restful Http Service for a single Connection.
 * 
 */
public interface RestfulConnection {

    @Get
    public SkysailResponse<MapData> getConnection();

    @Put
    public SkysailResponse<MapData> updateConnection(ConnectionDetails connection);

    @Delete
    public Representation delete();

}
