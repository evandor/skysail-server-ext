package de.twenty11.skysail.server.ext.dbviewer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;
import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.grids.ColumnsBuilder;
import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.grids.RowData;
import de.twenty11.skysail.common.responses.SkysailFailureResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.Connections;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailDataSource;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.ConnectionDetails;
import de.twenty11.skysail.server.restlet.GridDataServerResource;

public class ConnectionsResource extends GridDataServerResource {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public static Map<String, DataSource> datasources = new HashMap<String, DataSource>();

    // SkysailApplication application = (SkysailApplication) getApplication();

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

        if (!datasources.containsKey("default")) {
            DataSource defaultDS = SkysailDataSource.get();
            datasources.put("default", defaultDS);
        }
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

    @Post()
    public SkysailResponse add(JsonRepresentation entity) {
        try {
            JSONObject jsonObject = entity.getJsonObject();
            String name = jsonObject.getString("connectionName");
            String user = jsonObject.getString("username");
            ConnectionDetails connectionDetails = new ConnectionDetails(null, user, "a", "b", "c");
            Set<ConstraintViolation<ConnectionDetails>> constraintViolations = validator.validate(connectionDetails);
            int size = constraintViolations.size();
            if (size > 0) {
                return new SkysailFailureResponse(constraintViolations.toString());
            }
            ((SkysailApplication) getApplication()).getConnections().add(connectionDetails);
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
