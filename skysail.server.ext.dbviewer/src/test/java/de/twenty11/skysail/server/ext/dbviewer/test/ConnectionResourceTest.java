package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.restlet.Response;

import de.twenty11.skysail.common.maps.MapData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.ConnectionDetails;

public class ConnectionResourceTest extends ResourceTest {

    @Test
    public void shouldListExistingConnectionDetails() throws Exception {
        ConnectionDetails connection = new ConnectionDetails("default", "username", "password", "url",
                "driverClassName");
        post(DbViewerUrlMapper.CONNECTION_PREFIX, connection);

        Response response = get(DbViewerUrlMapper.CONNECTION_PREFIX + "default");
        SkysailResponse<MapData> skysailResponse = new SkysailResponse<MapData>().fromJson(response.getEntity()
                .getText(), MapData.class);
        assertThat(response.getStatus().getCode(), is(200));
        assertThat(skysailResponse.getSuccess(), is(true));
        assertThat(skysailResponse.getMessage(), is("Details for connection 'default'"));
    }

    @Test
    public void shouldGetInfoMessageWhenTryingToDeleteNonExistingConnection() throws Exception {
        Response response = delete(DbViewerUrlMapper.CONNECTION_PREFIX + "nonexistent");
        SkysailResponse<MapData> skysailResponse = new SkysailResponse<MapData>().fromJson(response.getEntity()
                .getText(), MapData.class);
        assertThat(response.getStatus().getCode(), is(200));
        assertThat(skysailResponse.getMessage(), is("no entry found to delete"));
    }

    @Test
    public void shouldSucceedWhenTryingToDeleteExistingConnection() throws Exception {
        ConnectionDetails connection = new ConnectionDetails("default", "username", "password", "url",
                "driverClassName");
        post(DbViewerUrlMapper.CONNECTION_PREFIX, connection);

        Response response = delete(DbViewerUrlMapper.CONNECTION_PREFIX + "default");
        SkysailResponse<MapData> skysailResponse = new SkysailResponse<MapData>().fromJson(response.getEntity()
                .getText(), MapData.class);
        assertThat(response.getStatus().getCode(), is(200));
        assertThat(skysailResponse.getMessage(), is("deleted one entry"));
    }

}
