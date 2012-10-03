package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
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
import de.twenty11.skysail.server.ext.dbviewer.internal.TableDetails;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.ConnectionDetails;

public class TablesResourceTest extends ResourceTest {

    @Test
    public void shouldGetValidResponseForGetRequest() throws Exception {
        Response response = get("/dbviewer/connections/default/tables");
        assertEquals(200, response.getStatus().getCode());
        assertThat(response.isEntityAvailable(), is(true));
        assertThat(response.getEntity().getMediaType(), is(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldGetValidGridDataForGetRequestToExistingConnection() throws Exception {
        addDefaultConnection();

        Response response = get("/dbviewer/connections/default/tables");
        SkysailResponse<GridData> skysailResponse = getSkysailResponse(response);
        assertThat(skysailResponse.getMessage(), containsString("listing"));
        assertThat(skysailResponse.getMessage(), containsString("tables"));
        GridData gridData = skysailResponse.getData();
        assertThat(gridData.getColumns().getAsList().size(), is(3));
        // assertThat(gridData.getRows().size(), is(0));
    }

    @Test
    public void shouldGetSuccessAnswerWhenAddingValidTableWithPost() throws Exception {
        addDefaultConnection();
        Response response = post("/dbviewer/connections/default/tables", new TableDetails("tableA"));
        Representation entity = response.getEntity();
        SkysailResponse<GridData> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<SkysailResponse<GridData>>() {
                });
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
    }

    @Test
    public void shouldGetNewConnectionWithGetAfterAddingValidConnectionWithPost() throws Exception {
        ConnectionDetails connection = new ConnectionDetails("id", "username", "password", "url", "driverClassName");
        post("/dbviewer/connections/", connection);

        Response response = handleRequest(new Request(Method.GET, "/dbviewer/connections/"));
        GridData gridData = getSkysailResponse(response).getData();
        assertThat(gridData.getRows().size(), is(1));
    }

    @Test
    public void shouldGetFailureAnswerWhenAddingNonValidConnectionWithPost() throws Exception {
        ConnectionDetails connection = new ConnectionDetails(null, "username", "password", "url", "driverClassName");
        Response response = post("/dbviewer/connections/", connection);

        SkysailResponse<GridData> skysailResponse = getSkysailResponse(response);
        assertThat(skysailResponse.getSuccess(), is(false));
    }

}
