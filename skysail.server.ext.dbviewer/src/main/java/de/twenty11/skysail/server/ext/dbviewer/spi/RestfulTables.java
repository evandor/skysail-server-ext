package de.twenty11.skysail.server.ext.dbviewer.spi;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.responses.SkysailResponse;

public interface RestfulTables {

    @Get
    public SkysailResponse<GridData> getTables();

    @Post()
    public Representation add(JsonRepresentation entity);

}
