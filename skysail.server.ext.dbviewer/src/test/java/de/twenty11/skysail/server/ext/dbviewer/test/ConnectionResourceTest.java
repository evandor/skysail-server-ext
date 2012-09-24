package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Method;
import org.restlet.representation.Representation;

import de.twenty11.skysail.common.maps.MapData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerComponent;

public class ConnectionResourceTest extends ResourceTest {

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
    public void shouldListExistingConnectionDetails() throws Exception {
        Request request = new Request(Method.GET, "/dbviewer/default");
        Response response = handleRequest(request);
        Representation entity = response.getEntity();
        Object readValue = mapper.readValue(entity.getText(), new TypeReference<SkysailResponse<MapData>>() {
        });
        assertThat(response.getStatus().getCode(), is(200));
    }

    @Test
    @Ignore
    public void shouldGetFailureWhenTryingToDeleteNonExistingConnection() throws Exception {
        Request request = new Request(Method.DELETE, "/dbviewer/nonexistent");
        Response response = handleRequest(request);
        assertThat(response.getStatus().getCode(), is(200));
    }

    // @Test
    // public void shouldSucceedWhenTryingToDeleteExistingConnection() throws Exception {
    // ConnectionDetails connection = new ConnectionDetails("idoexist", "username", "password", "url",
    // "driverClassName");
    // handleRequest(preparePostRequest(connection));
    // Request request = new Request(Method.DELETE, "/dbviewer/idoexist");
    // Response response = handleRequest(request);
    // assertThat(response.getStatus().getCode(), is(200));
    // }

}
