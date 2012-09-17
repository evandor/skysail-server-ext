package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Method;
import org.restlet.resource.ServerResource;

import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerComponent;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;

public class ConnectionResourceTest {

    private SkysailApplication skysailApplication;
    private Restlet inboundRoot;
    private DbViewerComponent dbViewerComponent;

    @Before
    public void setUp() throws Exception {
        dbViewerComponent = new DbViewerComponent();
        // skysailApplication = new SkysailApplication("/static");
        skysailApplication = dbViewerComponent.getApplication();
        Application.setCurrent(skysailApplication);
        inboundRoot = skysailApplication.getInboundRoot();
        addMappings();
    }

    @Test
    public void testAdd() {

        String resourceUri = "/dbviewer/";
        Request request = new Request(Method.GET, resourceUri);
        ChallengeResponse authentication = new ChallengeResponse(ChallengeScheme.HTTP_BASIC, "scott", "tiger");
        request.setChallengeResponse(authentication);
        Response response = new Response(request);
        inboundRoot.handle(request, response);
        assertEquals(200, response.getStatus().getCode());

        // Client client = new Client(Protocol.HTTP);
        // Request request = new Request(Method.GET, connectionsResource.getReference());
        // Response response = client.handle(request);
        // assert response.getStatus().getCode() == 200;
        // assert response.isEntityAvailable();
        // assert response.getEntity().getMediaType().equals(MediaType.TEXT_HTML);

        // Representation.getText() empties the InputStream, so we need to store the text in a variable
        String text;
        try {
            text = response.getEntity().getText();
            // assert text.contains("search string");
            // assert text.contains("another search string");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void addMappings() {
        Map<String, String> urlMapping = new DbViewerUrlMapper().provideUrlMapping();
        for (Map.Entry<String, String> mapping : urlMapping.entrySet()) {
            try {
                // logger.debug("adding new mapping from '{}' to '{}'", mapping.getKey(), mapping.getValue());
                @SuppressWarnings("unchecked")
                Class<? extends ServerResource> resourceClass = (Class<? extends ServerResource>) Class.forName(mapping
                        .getValue());
                skysailApplication.attachToRouter("" + mapping.getKey(), resourceClass);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("was not able to find class " + mapping.getValue(), e);
            }
        }
    }

}
