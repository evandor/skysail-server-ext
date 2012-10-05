package de.twenty11.skysail.server.ext.dbviewer.spi;

import org.restlet.resource.Get;

import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.responses.SkysailResponse;

public interface RestfulDbData {

    @Get
    public SkysailResponse<GridData> getData();

}
