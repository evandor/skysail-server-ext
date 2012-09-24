package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
import org.junit.Test;
import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.representation.Representation;

import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerComponent;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.ConnectionDetails;

public class ConnectionsResourceTest extends ResourceTest {

    private DbViewerComponent dbViewerComponent;

    @Before
    public void setUp() throws Exception {
        dbViewerComponent = new DbViewerComponent();
        skysailApplication = dbViewerComponent.getApplication();
        Application.setCurrent(skysailApplication);
        inboundRoot = skysailApplication.getInboundRoot();
        addMappings();
    }

    @Test
    public void shouldGetValidResponseForGetRequest() throws Exception {
        String resourceUri = "/dbviewer/";
        Request request = new Request(Method.GET, resourceUri);
        Response response = handleRequest(request);
        assertEquals(200, response.getStatus().getCode());
        assertThat(response.isEntityAvailable(), is(true));
        assertThat(response.getEntity().getMediaType(), is(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldGetValidGridDataForGetRequest() throws Exception {
        Response response = handleRequest(new Request(Method.GET, "/dbviewer/"));
        GridData gridData = getSkysailResponse(response).getData();
        assertThat(gridData.getColumns().getAsList().size(), is(5));
        assertThat(gridData.getRows().size(), is(1));
    }

    @Test
    public void shouldGetSuccessAnswerWhenAddingValidConnectionWithPost() throws Exception {
        ConnectionDetails connection = new ConnectionDetails("id", "username", "password", "url", "driverClassName");
        Response response = handleRequest(preparePostRequest(connection));
        Representation entity = response.getEntity();
        SkysailResponse<GridData> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<SkysailResponse<GridData>>() {
                });
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
    }

    @Test
    public void shouldGetNewConnectionWithGetAfterAddingValidConnectionWithPost() throws Exception {
        ConnectionDetails connection = new ConnectionDetails("id", "username", "password", "url", "driverClassName");
        handleRequest(preparePostRequest(connection));
        Response response = handleRequest(new Request(Method.GET, "/dbviewer/"));
        GridData gridData = getSkysailResponse(response).getData();
        assertThat(gridData.getRows().size(), is(2));
    }

    @Test
    public void shouldGetFailureAnswerWhenAddingNonValidConnectionWithPost() throws Exception {
        ConnectionDetails connection = new ConnectionDetails(null, "username", "password", "url", "driverClassName");
        Request request = new Request(Method.POST, "/dbviewer/");
        String writeValueAsString = mapper.writeValueAsString(connection);
        request.setEntity(writeValueAsString, MediaType.APPLICATION_JSON);
        Response response = handleRequest(request);
        SkysailResponse<GridData> skysailResponse = getSkysailResponse(response);
        assertThat(skysailResponse.getSuccess(), is(false));
    }

    // @Test
    // public void shouldGetFailureWhenTryingToDeleteNonExistingConnection() throws Exception {
    // Request request = new Request(Method.DELETE, "/dbviewer/nonexistent");
    // Response response = handleRequest(request);
    // assertThat(response.getStatus().getCode(), is(200));
    // }
    //
    // @Test
    // public void shouldSucceedWhenTryingToDeleteExistingConnection() throws Exception {
    // ConnectionDetails connection = new ConnectionDetails("idoexist", "username", "password", "url",
    // "driverClassName");
    // handleRequest(preparePostRequest(connection));
    // Request request = new Request(Method.DELETE, "/dbviewer/idoexist");
    // Response response = handleRequest(request);
    // assertThat(response.getStatus().getCode(), is(200));
    // }

    private Request preparePostRequest(ConnectionDetails connection) throws IOException, JsonGenerationException,
            JsonMappingException {
        Request request = new Request(Method.POST, "/dbviewer/");
        String writeValueAsString = mapper.writeValueAsString(connection);
        request.setEntity(writeValueAsString, MediaType.APPLICATION_JSON);
        return request;
    }

}
