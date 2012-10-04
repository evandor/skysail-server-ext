package de.twenty11.skysail.server.ext.dbviewer.test;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
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
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.ConnectionDetails;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.TableDetails;

public class ResourceTest {

    protected Restlet inboundRoot;
    protected SkysailApplication skysailApplication;
    protected ObjectMapper mapper = new ObjectMapper();
    private DbViewerComponent dbViewerComponent;

    @Before
    public void setUp() throws Exception {
        dbViewerComponent = new DbViewerComponent();
        skysailApplication = dbViewerComponent.getApplication();
        Application.setCurrent(skysailApplication);
        inboundRoot = skysailApplication.getInboundRoot();
        addMappings();
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

    protected void addMappings() throws ClassNotFoundException {
        Map<String, String> urlMapping = new DbViewerUrlMapper().provideUrlMapping();
        for (Map.Entry<String, String> mapping : urlMapping.entrySet()) {
            @SuppressWarnings("unchecked")
            Class<? extends ServerResource> resourceClass = (Class<? extends ServerResource>) Class.forName(mapping
                    .getValue());
            skysailApplication.attachToRouter("" + mapping.getKey(), resourceClass);
        }
    }

    protected SkysailResponse<GridData> getSkysailResponse(Response response) throws IOException, JsonParseException,
            JsonMappingException {
        Representation entity = response.getEntity();
        return mapper.readValue(entity.getText(), new TypeReference<SkysailResponse<GridData>>() {
        });
    }

    protected Request preparePostRequest(ConnectionDetails connection) throws IOException, JsonGenerationException,
            JsonMappingException {
        Request request = new Request(Method.POST, "/dbviewer/connections/");
        String writeValueAsString = mapper.writeValueAsString(connection);
        request.setEntity(writeValueAsString, MediaType.APPLICATION_JSON);
        return request;
    }

    protected ConnectionDetails addDefaultConnection() throws JsonGenerationException, JsonMappingException,
            IOException {
        ConnectionDetails connection = new ConnectionDetails("default", "SA", "", "jdbc:hsqldb:mem:.",
                "org.hsqldb.jdbc.JDBCDriver");
        post("/dbviewer/connections/", connection);
        return connection;
    }

    protected void addTable(String connection, TableDetails tableDetails) throws JsonGenerationException,
            JsonMappingException, IOException {
        post("/dbviewer/connections/" + connection + "/tables", tableDetails);
    }
}
