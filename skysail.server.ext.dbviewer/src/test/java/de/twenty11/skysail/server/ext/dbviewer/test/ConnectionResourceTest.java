package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;
import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Method;

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
        SkysailResponse<MapData> skysailResponse = new SkysailResponse<MapData>().fromJson(response.getEntity()
                .getText(), MapData.class);
        assertThat(response.getStatus().getCode(), is(200));
        assertThat(skysailResponse.getSuccess(), is(true));
        assertThat(skysailResponse.getMessage(), is("Details for connection 'default'"));
    }

    @Test
    public void shouldGetInfoMessageWhenTryingToDeleteNonExistingConnection() throws Exception {
        Request request = new Request(Method.DELETE, "/dbviewer/nonexistent");
        Response response = handleRequest(request);
        SkysailResponse<MapData> skysailResponse = new SkysailResponse<MapData>().fromJson(response.getEntity()
                .getText(), MapData.class);
        assertThat(response.getStatus().getCode(), is(200));
        assertThat(skysailResponse.getMessage(), is("no entry found to delete"));
    }

    @Test
    public void shouldSucceedWhenTryingToDeleteExistingConnection() throws Exception {
        Request request = new Request(Method.DELETE, "/dbviewer/default");
        Response response = handleRequest(request);
        SkysailResponse<MapData> skysailResponse = new SkysailResponse<MapData>().fromJson(response.getEntity()
                .getText(), MapData.class);
        assertThat(response.getStatus().getCode(), is(200));
        assertThat(skysailResponse.getMessage(), is("deleted one entry"));

    }

}
