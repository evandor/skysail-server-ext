package de.twenty11.skysail.server.ext.dbviewer;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.ext.dbviewer.RestfulSchemas;
import de.twenty11.skysail.common.ext.dbviewer.SchemaDetails;
import de.twenty11.skysail.common.responses.FailureResponse;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.common.responses.SuccessResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;
import de.twenty11.skysail.server.restlet.GenericServerResource;
import de.twenty11.skysail.server.restlet.ListServerResource;

public class SchemasResource extends ListServerResource<List<SchemaDetails>> implements RestfulSchemas {

    /** slf4j based logger implementation */
    private static Logger logger = LoggerFactory.getLogger(SchemasResource.class);

    private String connectionName;

    public SchemasResource() {
    }

    @Override
    protected void doInit() {
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
        if (ds == null)
            throw new IllegalStateException("datasource could not be found");
        Connection connection;
        List<SchemaDetails> result = new ArrayList<SchemaDetails>();
        int count = 0;
        try {
            connection = ds.getConnection();
            DatabaseMetaData meta = connection.getMetaData();

            //http://stackoverflow.com/questions/5679259/how-to-get-list-of-databases-schema-names-of-mysql-using-java-jdbc
            ResultSet schemas = meta.getCatalogs();
            while (schemas.next()) {
                count++;
                result.add(new SchemaDetails(schemas.getString("TABLE_CAT")));
            }
            if (count == 0) {
                schemas = meta.getSchemas();
                while (schemas.next()) {
                    count++;
                    result.add(new SchemaDetails(schemas.getString("TABLE_SCHEM")));
                }
            }
            setMessage("listing " + count + " schemas");
            setSkysailData(result);
        } catch (SQLException e) {
            throw new RuntimeException("Database Problem: " + e.getMessage(), e);
        }
    }

    private DataSource getDataSourceForConnection() {
       return ((SkysailApplication) getApplication()).getConnections(connectionName);
    }

}
