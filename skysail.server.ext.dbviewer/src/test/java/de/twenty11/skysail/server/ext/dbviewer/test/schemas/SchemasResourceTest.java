package de.twenty11.skysail.server.ext.dbviewer.test.schemas;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;

import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.TableDetails;
import de.twenty11.skysail.server.ext.dbviewer.test.ApplicationTests;

public class SchemasResourceTest extends ApplicationTests {

    @Test
    public void shouldGetValidResponseForGetRequest() throws Exception {
        Response response = get(DbViewerUrlMapper.CONNECTION_PREFIX + "default/schemes");
        assertEquals(200, response.getStatus().getCode());
        assertThat(response.isEntityAvailable(), is(true));
        assertThat(response.getEntity().getMediaType(), is(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldGetSuccessAnswerWhenAddingValidTableWithPost() throws Exception {
        addDefaultConnection();
        Response response = post(DbViewerUrlMapper.CONNECTION_PREFIX + "default/tables", new TableDetails("tableA"));
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

        Response response = get(DbViewerUrlMapper.CONNECTION_PREFIX);
        List<ConnectionDetails> data = getListOfConnectionDetailsResponse(response).getData();
        assertThat(data.size(), is(1));
    }

    @Test
    public void shouldGetFailureAnswerWhenAddingNonValidConnectionWithPost() throws Exception {
        ConnectionDetails connection = new ConnectionDetails(null, "username", "password", "url", "driverClassName");
        Response response = post(DbViewerUrlMapper.CONNECTION_PREFIX, connection);

        SkysailResponse<GridData> skysailResponse = getGridDataResponse(response);
        assertThat(skysailResponse.getSuccess(), is(false));
    }

}
