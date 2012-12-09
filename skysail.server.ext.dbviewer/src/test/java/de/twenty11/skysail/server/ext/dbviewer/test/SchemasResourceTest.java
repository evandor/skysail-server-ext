package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.ext.dbviewer.SchemaDetails;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerApplication;

public class SchemasResourceTest extends BaseTests {

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
        List<SchemaDetails> schemas = getSchemas("testdb");
        assertThat(schemas.size(), is(greaterThan(0)));
    }

    // @Test
    // @Ignore
    // public void shouldGetValidResponseForGetRequest() throws Exception {
    // addDefaultConnection();
    //
    // Response response = get(DbViewerUrlMapper.CONNECTION_PREFIX + "default/schemas");
    //
    // assertEquals(200, response.getStatus().getCode());
    // assertThat(response.isEntityAvailable(), is(true));
    // assertThat(response.getEntity().getMediaType(), is(MediaType.APPLICATION_JSON));
    // }
    //
    // @Test
    // @Ignore
    // public void shouldGetSuccessAnswerWhenAddingValidTableWithPost() throws Exception {
    // addDefaultConnection();
    // Response response = post(DbViewerUrlMapper.CONNECTION_PREFIX + "default/tables", new TableDetails("tableA"));
    // Representation entity = response.getEntity();
    // SkysailResponse<GridData> skysailResponse = mapper.readValue(entity.getText(),
    // new TypeReference<SkysailResponse<GridData>>() {
    // });
    // assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
    // }
    //
    // @Test
    // @Ignore
    // public void shouldGetNewConnectionWithGetAfterAddingValidConnectionWithPost() throws Exception {
    // ConnectionDetails connection = new ConnectionDetails("name", "username", "password", "url", "driverClassName");
    // post(DbViewerUrlMapper.CONNECTION_PREFIX, connection);
    //
    // Response response = get(DbViewerUrlMapper.CONNECTION_PREFIX);
    // List<ConnectionDetails> data = getListOfConnectionDetailsResponse(response).getData();
    // assertThat(data.size(), is(1));
    // }
    //
    // @Test
    // @Ignore
    // public void shouldGetFailureAnswerWhenAddingNonValidConnectionWithPost() throws Exception {
    // ConnectionDetails connection = new ConnectionDetails(null, "username", "password", "url", "driverClassName");
    // Response response = post(DbViewerUrlMapper.CONNECTION_PREFIX, connection);
    //
    // SkysailResponse<GridData> skysailResponse = getGridDataResponse(response);
    // assertThat(skysailResponse.getSuccess(), is(false));
    // }

}
