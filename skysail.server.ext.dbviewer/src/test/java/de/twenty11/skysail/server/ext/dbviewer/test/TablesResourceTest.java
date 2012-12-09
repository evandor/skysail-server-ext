package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;

import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerApplication;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.TableDetails;

public class TablesResourceTest extends BaseTests {


    @Before
    public void setUp() throws Exception {
        DbViewerApplication spy = setUpRestletApplication();
        setUpPersistence(spy);
        ConnectionDetails connection = new ConnectionDetails("testdb", "skysail", "skysail",
                "jdbc:derby:skysailDerbyTestDb;create=true", "org.apache.derby.jdbc.EmbeddedDriver");
        create(connection);
    }
    
    @Test
    public void can_read_tables_from_schema() throws Exception {
        List<String> tables = getTables("testDb", "skysail");
        assertThat(tables.size(), is(greaterThan(0)));
    }

//    @Test
//    @Ignore
//    public void shouldGetValidGridDataForGetRequestToExistingConnection() throws Exception {
//        addDefaultConnection();
//
//        Response response = get(DbViewerUrlMapper.CONNECTION_PREFIX + "default/tables");
//        SkysailResponse<GridData> skysailResponse = getGridDataResponse(response);
//        assertThat(skysailResponse.getMessage(), containsString("listing"));
//        assertThat(skysailResponse.getMessage(), containsString("tables"));
//        GridData gridData = skysailResponse.getData();
//        assertThat(gridData.getColumns().getAsList().size(), is(3));
//        // assertThat(gridData.getRows().size(), is(0));
//    }
//
//    @Test
//    @Ignore
//    public void shouldGetSuccessAnswerWhenAddingValidTableWithPost() throws Exception {
//        addDefaultConnection();
//        Response response = post(DbViewerUrlMapper.CONNECTION_PREFIX + "default/schemas/PUBLIC/tables",
//                new TableDetails("tableA"));
//        Representation entity = response.getEntity();
//        SkysailResponse<GridData> skysailResponse = mapper.readValue(entity.getText(),
//                new TypeReference<SkysailResponse<GridData>>() {
//                });
//        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
//    }
//
//    @Test
//    @Ignore
//    public void shouldGetNewConnectionWithGetAfterAddingValidConnectionWithPost() throws Exception {
//        ConnectionDetails connection = new ConnectionDetails("name", "username", "password", "url", "driverClassName");
//        post(DbViewerUrlMapper.CONNECTION_PREFIX, connection);
//
//        Response response = get(DbViewerUrlMapper.CONNECTION_PREFIX);
//        List<ConnectionDetails> data = getListOfConnectionDetailsResponse(response).getData();
//        assertThat(data.size(), is(1));
//    }
//
//    @Test
//    @Ignore
//    public void shouldGetFailureAnswerWhenAddingNonValidConnectionWithPost() throws Exception {
//        ConnectionDetails connection = new ConnectionDetails(null, "username", "password", "url", "driverClassName");
//        Response response = post(DbViewerUrlMapper.CONNECTION_PREFIX, connection);
//
//        SkysailResponse<GridData> skysailResponse = getGridDataResponse(response);
//        assertThat(skysailResponse.getSuccess(), is(false));
//    }

}
