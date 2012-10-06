package de.twenty11.skysail.server.ext.dbviewer;

import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.SkysailData;
import de.twenty11.skysail.common.grids.ColumnsBuilder;
import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.grids.RowData;
import de.twenty11.skysail.common.responses.SkysailFailureResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.responses.SkysailSuccessResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.Connections;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.ConnectionDetails;
import de.twenty11.skysail.server.ext.dbviewer.spi.RestfulConnections;
import de.twenty11.skysail.server.restlet.GridDataServerResource;

public class ConnectionsResource extends GridDataServerResource implements RestfulConnections {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());

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
    protected void doInit() throws ResourceException {
        // getVariants().add(new Variant(MediaType.APPLICATION_XML));
        // getVariants().add(new Variant(MediaType.APPLICATION_JSON));
    }

    @Override
    public void buildGrid() {
        setMessage("all Connections");

        Connections connections = ((SkysailApplication) getApplication()).getConnections();
        GridData grid = getSkysailData();
        for (String connectionName : connections.list()) {
            ConnectionDetails details = connections.get(connectionName);
            RowData row = new RowData(getSkysailData().getColumns());
            row.add(connectionName);
            row.add(details.getUrl());
            row.add(details.getUsername());
            row.add(details.getDriverName());
            row.add(getParent() + "dbviewer/" + connectionName + "/?media=json");
            grid.addRowData(row);

        }
    }

    public SkysailResponse<GridData> getConnections() {
        return createResponse();
    }

    public SkysailResponse<?> addConnection(ConnectionDetails entity) {
        SkysailResponse skysailResponse;
        // JSONObject jsonObject = entity.getJsonObject();
        // String name = determineValue(jsonObject, "id");
        // String user = determineValue(jsonObject, "username");
        // String pass = determineValue(jsonObject, "password");
        // String url = determineValue(jsonObject, "url");
        // String driver = determineValue(jsonObject, "driverName");
        // ConnectionDetails connectionDetails = new ConnectionDetails(name, user, pass, url, driver);
        Set<ConstraintViolation<ConnectionDetails>> constraintViolations = validator.validate(entity);
        int size = constraintViolations.size();
        if (size > 0) {
            skysailResponse = new SkysailFailureResponse(constraintViolations.toString());
        } else {
            ((SkysailApplication) getApplication()).getConnections().add(entity);
            skysailResponse = new SkysailSuccessResponse<SkysailData>();
        }
        return skysailResponse;// new JacksonRepresentation<SkysailResponse<GridData>>(skysailResponse);
    }

}
