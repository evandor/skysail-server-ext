package de.twenty11.skysail.server.ext.dbviewer.test.connection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.restlet.Response;
import org.restlet.data.MediaType;

import de.twenty11.skysail.common.MapData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.test.ApplicationTests;

public class ConnectionViaApplicationTest extends ApplicationTests {

    /**
     * Testing HTTP GET request
     */
    @Test
    public void canIssueGetRequest() throws Exception {
        Response response = get(DbViewerUrlMapper.CONNECTION_PREFIX + "default");
        assertEquals(200, response.getStatus().getCode());
        assertThat(response.isEntityAvailable(), is(true));
        assertThat(response.getEntity().getMediaType(), is(MediaType.APPLICATION_JSON));
    }

    /**
     * Testing HTTP GET request
     */
    @Test
    @Ignore
    public void shouldRetrieveExistingConnectionDetails() throws Exception {
        addDefaultConnection();

        Response response = get(DbViewerUrlMapper.CONNECTION_PREFIX + "default");
        SkysailResponse<MapData> skysailResponse = getMapDataResponse(response);
        assertThat(response.getStatus().getCode(), is(200));
        assertThat(skysailResponse.getSuccess(), is(true));
        assertThat(skysailResponse.getMessage(), is("Details for connection 'default'"));
        MapData data = skysailResponse.getData();
        assertThat(data.getValue("username"), is("SA"));
    }

    /**
     * Testing HTTP delete request
     */
    @Test
    public void shouldGetInfoMessageWhenTryingToDeleteNonExistingConnection() throws Exception {
        Response response = delete(DbViewerUrlMapper.CONNECTION_PREFIX + "nonexistent");
        SkysailResponse<MapData> skysailResponse = getMapDataResponse(response);
        assertThat(response.getStatus().getCode(), is(200));
        assertThat(skysailResponse.getMessage(), is("no entry found to delete"));
    }

    /**
     * Testing HTTP delete request
     */
    @Test
    public void shouldSucceedWhenTryingToDeleteExistingConnection() throws Exception {
        addDefaultConnection();

        Response response = delete(DbViewerUrlMapper.CONNECTION_PREFIX + "default");
        SkysailResponse<MapData> skysailResponse = getMapDataResponse(response);
        assertThat(response.getStatus().getCode(), is(200));
        assertThat(skysailResponse.getMessage(), is("deleted one entry"));
    }

}
