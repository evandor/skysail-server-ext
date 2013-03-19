package de.twenty11.skysail.server.ext.dbviewer.experimental;

import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.Post;

import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.ext.dbviewer.RestfulConnections;
import de.twenty11.skysail.common.forms.ConstraintViolations;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.restlet.ListServerResource;

public class BaseListResource<T> extends ListServerResource<T> implements RestfulConnections {

    public BaseListResource() {
        setName("dbviewer base resource");
        setDescription("The resource containing the basic list of connections");
    }

    @Override
    @Get
    public SkysailResponse<List<ConnectionDetails>> getConnections() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Post
    public SkysailResponse<ConstraintViolations<ConnectionDetails>> addConnection(ConnectionDetails entity) {
        // TODO Auto-generated method stub
        return null;
    }
}
