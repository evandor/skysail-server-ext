package de.twenty11.skysail.server.ext.dbviewer.spi;

import org.restlet.resource.Get;

import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.responses.SkysailResponse;

public interface RestfulColumns {

    @Get
    public SkysailResponse<GridData> getColumns();

    // @Post()
    // public Representation add(JsonRepresentation entity);

}
