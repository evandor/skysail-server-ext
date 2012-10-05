package de.twenty11.skysail.server.ext.dbviewer.spi;

import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;

import de.twenty11.skysail.common.maps.MapData;
import de.twenty11.skysail.common.responses.SkysailResponse;

public interface RestfulConnection {

    @Get
    public SkysailResponse<MapData> getConnection();

    @Delete
    public Representation delete();

}
