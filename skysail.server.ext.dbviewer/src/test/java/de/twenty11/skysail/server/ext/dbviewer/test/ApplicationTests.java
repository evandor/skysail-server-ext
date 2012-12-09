package de.twenty11.skysail.server.ext.dbviewer.test;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
import org.restlet.Application;
import org.restlet.Response;
import org.restlet.representation.Representation;

import de.twenty11.skysail.common.MapData;
import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerComponent;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.TableDetails;

public class ApplicationTests extends BaseTests {

    protected DbViewerComponent dbViewerComponent;

    @Before
    public void setUp() throws Exception {
        dbViewerComponent = new DbViewerComponent();
        dbViewerApplication = dbViewerComponent.getApplication();
        Application.setCurrent(dbViewerApplication);
        inboundRoot = dbViewerApplication.getInboundRoot();
        addMappings();
    }

    protected SkysailResponse<GridData> getGridDataResponse(Response response) throws Exception {
        Representation entity = response.getEntity();
        return mapper.readValue(entity.getText(), new TypeReference<SkysailResponse<GridData>>() {
        });
    }

    protected de.twenty11.skysail.common.responses.Response<List<ConnectionDetails>> getListOfConnectionDetailsResponse(Response response) throws Exception {
        Representation entity = response.getEntity();
        return mapper.readValue(entity.getText(), new TypeReference<de.twenty11.skysail.common.responses.Response<List<ConnectionDetails>>>() {
        });
    }

    protected SkysailResponse<MapData> getMapDataResponse(Response response) throws Exception {
        Representation entity = response.getEntity();
        return mapper.readValue(entity.getText(), new TypeReference<SkysailResponse<MapData>>() {
        });
    }

    /**
     * adds connections details with id "default" to an in-memory hsqldb database
     */
    protected ConnectionDetails addDefaultConnection() throws JsonGenerationException, JsonMappingException,
            IOException {
        ConnectionDetails connection = new ConnectionDetails("default", "SA", "", "jdbc:hsqldb:mem:.",
                "org.hsqldb.jdbc.JDBCDriver");
        post(DbViewerUrlMapper.CONNECTION_PREFIX, connection);
        return connection;
    }

    protected void addTable(String connection, TableDetails tableDetails) throws JsonGenerationException,
            JsonMappingException, IOException {
        post(DbViewerUrlMapper.CONNECTION_PREFIX + connection + "/tables", tableDetails);
    }
}
