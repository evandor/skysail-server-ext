package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.representation.Representation;

import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.ConnectionDetails;

public class ConnectionsResourceTest extends ResourceTest {

    @Test
    public void shouldGetValidResponseForGetRequest() throws Exception {
        Response response = get(DbViewerUrlMapper.CONNECTION_PREFIX);
        assertEquals(200, response.getStatus().getCode());
        assertThat(response.isEntityAvailable(), is(true));
        assertThat(response.getEntity().getMediaType(), is(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldGetValidGridDataForGetRequest() throws Exception {
        Response response = get(DbViewerUrlMapper.CONNECTION_PREFIX);
        GridData gridData = getSkysailResponse(response).getData();
        assertThat(gridData.getColumns().getAsList().size(), is(5));
        assertThat(gridData.getRows().size(), is(0));
    }

    @Test
    public void shouldGetSuccessAnswerWhenAddingValidConnectionWithPost() throws Exception {
        ConnectionDetails connection = new ConnectionDetails("id", "username", "password", "url", "driverClassName");
        Response response = post(DbViewerUrlMapper.CONNECTION_PREFIX, connection);
        Representation entity = response.getEntity();
        SkysailResponse<GridData> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<SkysailResponse<GridData>>() {
                });
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
    }

    @Test
    public void shouldGetNewConnectionWithGetAfterAddingValidConnectionWithPost() throws Exception {
        ConnectionDetails connection = new ConnectionDetails("id", "username", "password", "url", "driverClassName");
        post(DbViewerUrlMapper.CONNECTION_PREFIX, connection);

        Response response = handleRequest(new Request(Method.GET, DbViewerUrlMapper.CONNECTION_PREFIX));
        GridData gridData = getSkysailResponse(response).getData();
        assertThat(gridData.getRows().size(), is(1));
    }

    @Test
    public void shouldGetFailureAnswerWhenAddingNonValidConnectionWithPost() throws Exception {
        ConnectionDetails connection = new ConnectionDetails(null, "username", "password", "url", "driverClassName");
        Response response = post(DbViewerUrlMapper.CONNECTION_PREFIX, connection);

        SkysailResponse<GridData> skysailResponse = getSkysailResponse(response);
        assertThat(skysailResponse.getSuccess(), is(false));
    }
}
