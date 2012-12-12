package de.twenty11.skysail.server.ext.osgimonitor.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.mockito.Mockito;
import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Restlet;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

import de.twenty11.skysail.common.ext.osgimonitor.BundleDetails;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.server.ext.osgimonitor.internal.OsgiMonitorApplicationDescriptor;
import de.twenty11.skysail.server.ext.osgimonitor.internal.OsgiMonitorComponent;
import de.twenty11.skysail.server.ext.osgimonitor.internal.OsgiMonitorUrlMapper;
import de.twenty11.skysail.server.ext.osgimonitor.internal.OsgiMonitorViewerApplication;

public class BaseTests {

    protected OsgiMonitorViewerApplication osgiMonitorViewerApplication;
    protected Restlet inboundRoot;
    protected ObjectMapper mapper = new ObjectMapper();
    
    protected OsgiMonitorViewerApplication setUpRestletApplication() throws ClassNotFoundException {
        OsgiMonitorComponent osgiMonitorComponent = new OsgiMonitorComponent();
        osgiMonitorViewerApplication = osgiMonitorComponent.getApplication();

        //OsgiMonitorViewerApplication spy = Mockito.spy(osgiMonitorViewerApplication);
        Application.setCurrent(osgiMonitorViewerApplication);
        inboundRoot = osgiMonitorViewerApplication.getInboundRoot();
        addMappings();
        return osgiMonitorViewerApplication;
    }


    protected void addMappings() throws ClassNotFoundException {
        Map<String, String> urlMapping = new OsgiMonitorUrlMapper().provideUrlMapping();
        for (Map.Entry<String, String> mapping : urlMapping.entrySet()) {
            @SuppressWarnings("unchecked")
            Class<? extends ServerResource> resourceClass = (Class<? extends ServerResource>) Class.forName(mapping
                    .getValue());
            osgiMonitorViewerApplication.attachToRouter("" + mapping.getKey(), resourceClass);
        }
    }

    protected List<BundleDetails> getBundles() throws Exception {
        org.restlet.Response response = get("bundles");
        assertDefaults(response);
        Representation entity = response.getEntity();
        Response<List<BundleDetails>> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<Response<List<BundleDetails>>>() {
                });
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        return skysailResponse.getData();
        // assertThat(skysailResponse.getMessage(), skysailResponse.getData().size(), is(1));
    }

    private org.restlet.Response delete(String uri) {
        Request request = new Request(Method.DELETE, "/" + OsgiMonitorApplicationDescriptor.APPLICATION_NAME + "/" + uri);
        return handleRequest(request);
    }

    protected org.restlet.Response get(String uri) {
        Request request = new Request(Method.GET, "/" + OsgiMonitorApplicationDescriptor.APPLICATION_NAME + "/" + uri);
        return handleRequest(request);
    }

    protected org.restlet.Response post(String uri, Object connection) throws JsonGenerationException,
            JsonMappingException, IOException {
        Request request = new Request(Method.POST, "/" + OsgiMonitorApplicationDescriptor.APPLICATION_NAME + "/" + uri);
        String writeValueAsString = mapper.writeValueAsString(connection);
        request.setEntity(writeValueAsString, MediaType.APPLICATION_JSON);
        return handleRequest(request);
    }

    protected org.restlet.Response handleRequest(Request request) {
        ChallengeResponse authentication = new ChallengeResponse(ChallengeScheme.HTTP_BASIC, "scott", "tiger");
        request.setChallengeResponse(authentication);
        org.restlet.Response response = new org.restlet.Response(request);
        inboundRoot.handle(request, response);
        return response;
    }

    protected void assertDefaults(org.restlet.Response response) {
        assertEquals(200, response.getStatus().getCode());
        assertThat(response.isEntityAvailable(), is(true));
        assertThat(response.getEntity().getMediaType(), is(MediaType.APPLICATION_JSON));
    }

}
