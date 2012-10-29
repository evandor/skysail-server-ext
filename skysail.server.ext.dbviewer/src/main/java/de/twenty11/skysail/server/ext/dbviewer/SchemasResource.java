package de.twenty11.skysail.server.ext.dbviewer;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.ext.dbviewer.RestfulSchemas;
import de.twenty11.skysail.common.ext.dbviewer.SchemaDetails;
import de.twenty11.skysail.common.grids.ColumnsBuilder;
import de.twenty11.skysail.common.responses.FailureResponse;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.common.responses.SuccessResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.Connections;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;
import de.twenty11.skysail.server.restlet.GenericServerResource;

public class SchemasResource extends GenericServerResource<List<SchemaDetails>> implements RestfulSchemas {

    /** slf4j based logger implementation */
    private static Logger logger = LoggerFactory.getLogger(SchemasResource.class);

    private Validator validator;

    private String connectionName;

    public SchemasResource() {
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

        Configuration<?> config = Validation.byDefaultProvider().providerResolver(new OSGiServiceDiscoverer())
                .configure();

        ValidatorFactory factory = config.buildValidatorFactory();
        validator = factory.getValidator();
    }

    @Override
    protected void doInit() throws ResourceException {
        connectionName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.CONNECTION_NAME);
    }

    @Override
    @Get
    public Response<List<SchemaDetails>> getSchemas() {
        Response<List<SchemaDetails>> response;
        try {
            response = new SuccessResponse<List<SchemaDetails>>(getFilteredData());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response = new FailureResponse<List<SchemaDetails>>(e);
        }
        return response;
    }


    @Override
    public void buildGrid() {
        setMessage("all Schemas");
        DataSource ds = getDataSourceForConnection();
        Connection connection;
        List<String> result = new ArrayList<String>();
        int count = 0;
        try {
            connection = ds.getConnection();
            DatabaseMetaData meta = connection.getMetaData();

            ResultSet schemas = meta.getSchemas();
            //ResultSet tables = meta.getTables(null, null, null, new String[] { "TABLE" });
            while (schemas.next()) {
                count++;
                result.add(schemas.getString("TABLE_SCHEM"));
            }
            setMessage("listing " + count + " schemas");
        } catch (SQLException e) {
            throw new RuntimeException("Database Problem: " + e.getMessage(), e);
        }
    }

    private DataSource getDataSourceForConnection() {
        Connections connections = ((SkysailApplication) getApplication()).getConnections();
        return connections.getDataSource(connectionName);
    }

}
