package de.twenty11.skysail.server.ext.dbviewer;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.restlet.resource.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.ext.dbviewer.RestfulSchemas;
import de.twenty11.skysail.common.ext.dbviewer.SchemaDetails;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerApplication;
import de.twenty11.skysail.server.restlet.ListServerResource;

public class SchemasResource extends ListServerResource<SchemaDetails> implements RestfulSchemas {

    /** slf4j based logger implementation */
    private static Logger logger = LoggerFactory.getLogger(SchemasResource.class);

    private String connectionName;

    public SchemasResource() {
        setName("dbviewer schemas resource");
        setDescription("The resource containing the list of schemas for the current connection");
    }

    @Override
    protected void doInit() {
        connectionName = (String) getRequest().getAttributes().get(Constants.CONNECTION_NAME);
        setDescription("The resource containing the list of schemas for '" + connectionName + "'");
    }

    @Override
    @Get("html|json")
    public SkysailResponse<List<SchemaDetails>> getSchemas() {
        return getEntities(allSchemas(), "all Schemas");
    }

    private List<SchemaDetails> allSchemas() {
        DataSource ds = ((DbViewerApplication) getApplication()).getDataSource(connectionName, getChallengeResponse());
//        EntityManager em = ((DbViewerApplication) getApplication()).getEntityManager();
//        em.getTransaction().begin();
//        java.sql.Connection connection = em.unwrap(java.sql.Connection.class);

        List<SchemaDetails> result = new ArrayList<SchemaDetails>();
        int count = 0;
        try {
            Connection connection = ds.getConnection();
            DatabaseMetaData meta = connection.getMetaData();

            // http://stackoverflow.com/questions/5679259/how-to-get-list-of-databases-schema-names-of-mysql-using-java-jdbc
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
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Database Problem: " + e.getMessage(), e);
        } finally {
            //em.getTransaction().commit();
        }
    }

    @Override
    public void buildGrid() {
        setMessage("all Schemas");

    }

}
