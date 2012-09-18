package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
import org.junit.Test;
import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerComponent;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;

public class ConnectionResourceTest {

    private SkysailApplication skysailApplication;
    private Restlet inboundRoot;
    private DbViewerComponent dbViewerComponent;

    /** deals with json objects */
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        dbViewerComponent = new DbViewerComponent();
        skysailApplication = dbViewerComponent.getApplication();
        Application.setCurrent(skysailApplication);
        inboundRoot = skysailApplication.getInboundRoot();
        addMappings();
    }

    @Test
    public void testAdd() throws Exception {

        String resourceUri = "/dbviewer/";
        Request request = new Request(Method.GET, resourceUri);
        Response response = handleRequest(request);
        assertEquals(200, response.getStatus().getCode());
        assertTrue(response.isEntityAvailable());
        assertTrue(response.getEntity().getMediaType().equals(MediaType.APPLICATION_JSON));

        Representation entity = response.getEntity();
        // ((org.restlet.ext.jackson.JacksonRepresentation) entity).
        SkysailResponse<GridData> response2 = mapper.readValue(entity.getText(),
                new TypeReference<SkysailResponse<GridData>>() {
                });
        GridData payload = response2.getData();

        System.out.println(payload);

    }

    private Response handleRequest(Request request) {
        ChallengeResponse authentication = new ChallengeResponse(ChallengeScheme.HTTP_BASIC, "scott", "tiger");
        request.setChallengeResponse(authentication);
        Response response = new Response(request);
        inboundRoot.handle(request, response);
        return response;
    }

    private void addMappings() throws ClassNotFoundException {
        Map<String, String> urlMapping = new DbViewerUrlMapper().provideUrlMapping();
        for (Map.Entry<String, String> mapping : urlMapping.entrySet()) {
            @SuppressWarnings("unchecked")
            Class<? extends ServerResource> resourceClass = (Class<? extends ServerResource>) Class.forName(mapping
                    .getValue());
            skysailApplication.attachToRouter("" + mapping.getKey(), resourceClass);
        }
    }

}
