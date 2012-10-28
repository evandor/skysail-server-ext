package de.twenty11.skysail.server.ext.dbviewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.SkysailData;
import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.ext.dbviewer.RestfulConnections;
import de.twenty11.skysail.common.grids.ColumnsBuilder;
import de.twenty11.skysail.common.responses.FailureResponse;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.common.responses.SuccessResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.Connections;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;
import de.twenty11.skysail.server.restlet.GenericServerResource;

public class ConnectionsResource extends GenericServerResource<List<ConnectionDetails>> implements RestfulConnections {

    /** slf4j based logger implementation */
    private static Logger logger = LoggerFactory.getLogger(ConnectionsResource.class);

    private Validator validator;

    public ConnectionsResource() {
        super(new ColumnsBuilder() {

            @Override
            public void configure() {
                addColumn("connectionName");
                addColumn("url");
                addColumn("user");
                addColumn("driver");
                addColumn("drillDown10");
            }
        });
        setTemplate("skysail.server.ext.dbviewer:connections.ftl");

        Configuration<?> config = Validation.byDefaultProvider().providerResolver(new OSGiServiceDiscoverer())
                .configure();

        ValidatorFactory factory = config.buildValidatorFactory();
        validator = factory.getValidator();
    }

    @Override
    @Get
    public Response<List<ConnectionDetails>> getConnections() {
        Response<List<ConnectionDetails>> response;
        try {
            response = new SuccessResponse<List<ConnectionDetails>>(getFilteredData());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response = new FailureResponse<List<ConnectionDetails>>(e);
        }
        return response;
    }

    @Override
    @Post
    public Response<?> addConnection(ConnectionDetails entity) {
        Response<?> skysailResponse;
        Set<ConstraintViolation<ConnectionDetails>> constraintViolations = validator.validate(entity);
        int size = constraintViolations.size();
        if (size > 0) {
            logger.warn("contraint violations found on {}: {}", entity, constraintViolations);
            skysailResponse = new FailureResponse(constraintViolations.toString());
        } else {
            logger.info("about to add connection {}", entity);
            ((SkysailApplication) getApplication()).getConnections().add(entity);
            skysailResponse = new SuccessResponse<SkysailData>();
        }
        return skysailResponse;
    }

    @Override
    public void buildGrid() {
        setMessage("all Connections");

        Connections connections = ((SkysailApplication) getApplication()).getConnections();
        List<ConnectionDetails> result = new ArrayList<ConnectionDetails>();
        for (String connectionName : connections.list()) {
            ConnectionDetails details = connections.get(connectionName);
            result.add(details);
        }
        setSkysailData(result);
    }

}
