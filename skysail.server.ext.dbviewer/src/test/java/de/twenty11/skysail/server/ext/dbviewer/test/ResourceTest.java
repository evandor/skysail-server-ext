package de.twenty11.skysail.server.ext.dbviewer.test;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;

public class ResourceTest {

    protected Restlet inboundRoot;
    protected SkysailApplication skysailApplication;
    protected ObjectMapper mapper = new ObjectMapper();

    protected Response handleRequest(Request request) {
        ChallengeResponse authentication = new ChallengeResponse(ChallengeScheme.HTTP_BASIC, "scott", "tiger");
        request.setChallengeResponse(authentication);
        Response response = new Response(request);
        inboundRoot.handle(request, response);
        return response;
    }

    protected void addMappings() throws ClassNotFoundException {
        Map<String, String> urlMapping = new DbViewerUrlMapper().provideUrlMapping();
        for (Map.Entry<String, String> mapping : urlMapping.entrySet()) {
            @SuppressWarnings("unchecked")
            Class<? extends ServerResource> resourceClass = (Class<? extends ServerResource>) Class.forName(mapping
                    .getValue());
            skysailApplication.attachToRouter("" + mapping.getKey(), resourceClass);
        }
    }

    protected SkysailResponse<GridData> getSkysailResponse(Response response) throws IOException, JsonParseException, JsonMappingException {
        Representation entity = response.getEntity();
        return mapper.readValue(entity.getText(), new TypeReference<SkysailResponse<GridData>>() {
        });
    }

}
