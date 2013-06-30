package de.twenty11.skysail.server.ext.osgimonitor.test;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.mockito.Mockito;
import org.osgi.service.component.ComponentContext;
import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Restlet;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.representation.Representation;
import org.restlet.security.MapVerifier;

import de.twenty11.skysail.common.ext.osgimonitor.domain.BundleDescriptor;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.osgimonitor.OsgiMonitorViewerApplication;
import de.twenty11.skysail.server.ext.osgimonitor.domain.ServiceDescriptor;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;

public class BaseTests {

    protected OsgiMonitorViewerApplication osgiMonitorViewerApplication;
    protected Restlet inboundRoot;
    protected ObjectMapper mapper = new ObjectMapper();

    private ComponentContext componentContextMock = mock(ComponentContext.class);

    protected OsgiMonitorViewerApplication setUpRestletApplication() throws ClassNotFoundException {

        MapVerifier secretVerifier = new MapVerifier();
        secretVerifier.getLocalSecrets().put("testadmin", "testpassword".toCharArray());
        // OsgiMonitorComponent osgiMonitorComponent = new OsgiMonitorComponent(componentContextMock, secretVerifier);
        osgiMonitorViewerApplication = new OsgiMonitorViewerApplication();
        osgiMonitorViewerApplication.setVerifier(secretVerifier);

        OsgiMonitorViewerApplication spy = Mockito.spy(osgiMonitorViewerApplication);
        Application.setCurrent(spy);
        inboundRoot = osgiMonitorViewerApplication.getInboundRoot();
        // addMappings();
        return spy;
    }

    // private void addMappings() throws ClassNotFoundException {
    // Map<String, String> urlMapping = new OsgiMonitorUrlMapper().provideUrlMapping();
    // for (Map.Entry<String, String> mapping : urlMapping.entrySet()) {
    // @SuppressWarnings("unchecked")
    // Class<? extends ServerResource> resourceClass = (Class<? extends ServerResource>) Class.forName(mapping
    // .getValue());
    // osgiMonitorViewerApplication.attachToRouter("" + mapping.getKey(), resourceClass);
    // }
    // }

    protected List<BundleDescriptor> getBundles() throws Exception {
        org.restlet.Response response = get("bundles");
        assertDefaults(response);
        Representation entity = response.getEntity();
        SkysailResponse<List<BundleDescriptor>> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<SkysailResponse<List<BundleDescriptor>>>() {
                });
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        return skysailResponse.getData();
    }

    protected List<ServiceDescriptor> getServices() throws Exception {
        org.restlet.Response response = get("services");
        assertDefaults(response);
        Representation entity = response.getEntity();
        SkysailResponse<List<ServiceDescriptor>> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<SkysailResponse<List<ServiceDescriptor>>>() {
                });
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        return skysailResponse.getData();
    }

    private org.restlet.Response delete(String uri) {
        Request request = new Request(Method.DELETE, "/" + uri);
        return handleRequest(request);
    }

    protected org.restlet.Response get(String uri) {
        Request request = new Request(Method.GET, "/" + uri);
        return handleRequest(request);
    }

    protected org.restlet.Response put(String uri) {
        Request request = new Request(Method.PUT, "/" + uri);
        return handleRequest(request);
    }

    protected org.restlet.Response post(String uri, Object connection) throws JsonGenerationException,
            JsonMappingException, IOException {
        Request request = new Request(Method.POST, "/" + uri);
        String writeValueAsString = mapper.writeValueAsString(connection);
        request.setEntity(writeValueAsString, MediaType.APPLICATION_JSON);
        return handleRequest(request);
    }

    protected org.restlet.Response handleRequest(Request request) {
        ChallengeResponse authentication = new ChallengeResponse(ChallengeScheme.HTTP_BASIC, "testadmin",
                "testpassword");
        request.setChallengeResponse(authentication);
        org.restlet.Response response = new org.restlet.Response(request);
        inboundRoot.handle(request, response);
        return response;
    }

    protected void assertDefaults(org.restlet.Response response) {
        assertEquals(200, response.getStatus().getCode());
        assertThat(response.isEntityAvailable(), is(true));
        // assertThat(response.getEntity().getMediaType(), is(MediaType.APPLICATION_JSON));
    }

}
