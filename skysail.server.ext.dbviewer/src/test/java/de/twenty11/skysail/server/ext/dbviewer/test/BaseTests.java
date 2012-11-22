package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.resource.ServerResource;

import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;

public class BaseTests {

    protected SkysailApplication skysailApplication;
    protected Restlet inboundRoot;
    protected ObjectMapper mapper = new ObjectMapper();

    protected void addMappings() throws ClassNotFoundException {
        Map<String, String> urlMapping = new DbViewerUrlMapper().provideUrlMapping();
        for (Map.Entry<String, String> mapping : urlMapping.entrySet()) {
            @SuppressWarnings("unchecked")
            Class<? extends ServerResource> resourceClass = (Class<? extends ServerResource>) Class.forName(mapping
                    .getValue());
            skysailApplication.attachToRouter("" + mapping.getKey(), resourceClass);
        }
    }
    
    protected Response get(String uri) {
        Request request = new Request(Method.GET, uri);
        return handleRequest(request);
    }

    protected Response post(String uri, Object connection) throws JsonGenerationException, JsonMappingException,
            IOException {
        Request request = new Request(Method.POST, uri);
        String writeValueAsString = mapper.writeValueAsString(connection);
        request.setEntity(writeValueAsString, MediaType.APPLICATION_JSON);
        return handleRequest(request);
    }

    protected Response delete(String uri) {
        Request request = new Request(Method.DELETE, uri);
        return handleRequest(request);
    }

    protected Response handleRequest(Request request) {
        ChallengeResponse authentication = new ChallengeResponse(ChallengeScheme.HTTP_BASIC, "scott", "tiger");
        request.setChallengeResponse(authentication);
        Response response = new Response(request);
        inboundRoot.handle(request, response);
        return response;
    }

    protected void assertDefaults(org.restlet.Response response) {
        assertEquals(200, response.getStatus().getCode());
        assertThat(response.isEntityAvailable(), is(true));
        assertThat(response.getEntity().getMediaType(), is(MediaType.APPLICATION_JSON));
    }



}
