package de.twenty11.skysail.server.ext.dbviewer.spi;

import org.restlet.representation.Variant;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.ConnectionDetails;

public interface RestfulConnections {

    @Get
    public SkysailResponse<GridData> getConnections(Variant variant);

    @Post()
    public SkysailResponse<?> addConnection(ConnectionDetails entity);

}
