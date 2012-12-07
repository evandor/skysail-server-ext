package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Restlet;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

import de.twenty11.skysail.common.ext.dbviewer.ColumnsDetails;
import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.ext.dbviewer.ConstraintDetails;
import de.twenty11.skysail.common.ext.dbviewer.SchemaDetails;
import de.twenty11.skysail.common.forms.ConstraintViolations;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerComponent;
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

    protected ConstraintViolations<ConnectionDetails> create(ConnectionDetails connection) throws Exception {
        org.restlet.Response response = post("/dbviewer/connections/", connection);
        assertDefaults(response);
        Response<ConstraintViolations<ConnectionDetails>> skysailResponse = mapper.readValue(response.getEntity()
                .getText(), new TypeReference<Response<ConstraintViolations<ConnectionDetails>>>() {
        });
        // assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        return skysailResponse.getValidationViolations();
    }

    protected List<ConnectionDetails> getConnections() throws Exception {
        org.restlet.Response response = get("/dbviewer/connections/");
        assertDefaults(response);
        Representation entity = response.getEntity();
        Response<List<ConnectionDetails>> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<Response<List<ConnectionDetails>>>() {
                });
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        return skysailResponse.getData();
        // assertThat(skysailResponse.getMessage(), skysailResponse.getData().size(), is(1));
    }

    protected ConnectionDetails getConnection(String connectionName) throws Exception {
        org.restlet.Response response = get("/dbviewer/connections/" + connectionName);
        assertDefaults(response);
        Representation entity = response.getEntity();
        Response<ConnectionDetails> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<Response<ConnectionDetails>>() {
                });
        ConnectionDetails data = skysailResponse.getData();
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        return data;
    }

    protected List<SchemaDetails> getSchemas(String connectionName) throws Exception {
        org.restlet.Response response = get("/dbviewer/connections/" + connectionName + "/schemas");
        assertDefaults(response);
        Representation entity = response.getEntity();
        Response<List<SchemaDetails>> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<Response<List<SchemaDetails>>>() {
                });
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        return skysailResponse.getData();
    }

    protected List<String> getTables(String connectionName, String schemaName) throws Exception {
        org.restlet.Response response = get("/dbviewer/connections/" + connectionName + "/schemas/" + schemaName
                + "/tables");
        assertDefaults(response);
        Representation entity = response.getEntity();
        Response<List<String>> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<Response<List<String>>>() {
                });
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        return skysailResponse.getData();
    }

    protected List<ColumnsDetails> getColumns(String connectionName, String schemaName, String tableName)
            throws Exception {
        org.restlet.Response response = get("/dbviewer/connections/" + connectionName + "/schemas/" + schemaName
                + "/tables/" + tableName + "/columns");
        assertDefaults(response);
        Representation entity = response.getEntity();
        Response<List<ColumnsDetails>> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<Response<List<ColumnsDetails>>>() {
                });
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        return skysailResponse.getData();
    }

    protected List<ConstraintDetails> getConstraints(String connectionName, String schemaName, String tableName)
            throws Exception {
        org.restlet.Response response = get("/dbviewer/connections/" + connectionName + "/schemas/" + schemaName
                + "/tables/" + tableName + "/constraints");
        assertDefaults(response);
        Representation entity = response.getEntity();
        Response<List<ConstraintDetails>> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<Response<List<ConstraintDetails>>>() {
                });
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        return skysailResponse.getData();
    }

    protected void deleteConnection(String connectionName) throws Exception {
        org.restlet.Response response = delete("/dbviewer/connections/" + connectionName);
        assertDefaults(response);
        Representation entity = response.getEntity();
        Response<String> skysailResponse = mapper.readValue(entity.getText(), new TypeReference<Response<String>>() {
        });
        String data = skysailResponse.getData();
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        assertThat(skysailResponse.getMessage(), skysailResponse.getMessage(),
                is("deleted entity 'name, username, url'"));
    }

    private org.restlet.Response delete(String uri) {
        Request request = new Request(Method.DELETE, uri);
        return handleRequest(request);
    }

    protected org.restlet.Response get(String uri) {
        Request request = new Request(Method.GET, uri);
        return handleRequest(request);
    }

    protected org.restlet.Response post(String uri, Object connection) throws JsonGenerationException,
            JsonMappingException, IOException {
        Request request = new Request(Method.POST, uri);
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

    protected void setUpPersistence(SkysailApplication spy) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("testPU");
        Mockito.doAnswer(new Answer<EntityManager>() {
            @Override
            public EntityManager answer(InvocationOnMock invocation) throws Throwable {
                return emf.createEntityManager();
            }

        }).when(spy).getEntityManager();
    }

    protected SkysailApplication setUpRestletApplication() throws ClassNotFoundException {
        DbViewerComponent dbViewerComponent = new DbViewerComponent();
        skysailApplication = dbViewerComponent.getApplication();

        SkysailApplication spy = Mockito.spy(skysailApplication);
        Application.setCurrent(spy);
        inboundRoot = skysailApplication.getInboundRoot();
        addMappings();
        return spy;
    }

}
