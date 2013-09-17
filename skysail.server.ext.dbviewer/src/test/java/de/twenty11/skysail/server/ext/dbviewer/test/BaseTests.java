package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerApplication;

public class BaseTests {

    protected DbViewerApplication dbViewerApplication;
    protected Restlet inboundRoot;
    protected ObjectMapper mapper = new ObjectMapper();

    protected void addMappings() throws ClassNotFoundException {
        Map<String, String> urlMapping = Collections.emptyMap();// new Constants().provideUrlMapping();
        for (Map.Entry<String, String> mapping : urlMapping.entrySet()) {
            @SuppressWarnings("unchecked")
            Class<? extends ServerResource> resourceClass = (Class<? extends ServerResource>) Class.forName(mapping
                    .getValue());
            dbViewerApplication.attachToRouter("" + mapping.getKey(), resourceClass);
        }
    }

    // protected SkysailResponse<ConstraintViolations<ConnectionDetails>> create(ConnectionDetails connection) throws
    // Exception {
    // org.restlet.Response response = post("connections/", connection);
    // assertDefaults(response);
    // SkysailResponse<ConstraintViolations<ConnectionDetails>> skysailResponse = mapper.readValue(response.getEntity()
    // .getText(), new TypeReference<SkysailResponse<ConstraintViolations<ConnectionDetails>>>() {
    // });
    // // assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
    // return skysailResponse;//.getValidationViolations();
    // }

    protected List<ConnectionDetails> getConnections() throws Exception {
        org.restlet.Response response = get("connections/");
        assertDefaults(response);
        Representation entity = response.getEntity();
        SkysailResponse<List<ConnectionDetails>> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<SkysailResponse<List<ConnectionDetails>>>() {
                });
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        return skysailResponse.getData();
        // assertThat(skysailResponse.getMessage(), skysailResponse.getData().size(), is(1));
    }

    protected ConnectionDetails getConnection(String connectionName) throws Exception {
        org.restlet.Response response = get("connections/" + connectionName);
        assertDefaults(response);
        Representation entity = response.getEntity();
        SkysailResponse<ConnectionDetails> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<SkysailResponse<ConnectionDetails>>() {
                });
        ConnectionDetails data = skysailResponse.getData();
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        return data;
    }

    protected List<SchemaDetails> getSchemas(String connectionName) throws Exception {
        org.restlet.Response response = get("connections/" + connectionName + "/schemas");
        assertDefaults(response);
        Representation entity = response.getEntity();
        SkysailResponse<List<SchemaDetails>> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<SkysailResponse<List<SchemaDetails>>>() {
                });
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        return skysailResponse.getData();
    }

    protected List<String> getTables(String connectionName, String schemaName) throws Exception {
        org.restlet.Response response = get("connections/" + connectionName + "/schemas/" + schemaName + "/tables");
        assertDefaults(response);
        Representation entity = response.getEntity();
        SkysailResponse<List<String>> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<SkysailResponse<List<String>>>() {
                });
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        return skysailResponse.getData();
    }

    protected List<ColumnsDetails> getColumns(String connectionName, String schemaName, String tableName)
            throws Exception {
        org.restlet.Response response = get("connections/" + connectionName + "/schemas/" + schemaName + "/tables/"
                + tableName + "/columns");
        assertDefaults(response);
        Representation entity = response.getEntity();
        SkysailResponse<List<ColumnsDetails>> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<SkysailResponse<List<ColumnsDetails>>>() {
                });
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        return skysailResponse.getData();
    }

    protected List<ConstraintDetails> getConstraints(String connectionName, String schemaName, String tableName)
            throws Exception {
        org.restlet.Response response = get("connections/" + connectionName + "/schemas/" + schemaName + "/tables/"
                + tableName + "/constraints");
        assertDefaults(response);
        Representation entity = response.getEntity();
        SkysailResponse<List<ConstraintDetails>> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<SkysailResponse<List<ConstraintDetails>>>() {
                });
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        return skysailResponse.getData();
    }

    // protected GridData getData(String connectionName, String schemaName, String tableName) throws Exception {
    // org.restlet.Response response = get("connections/" + connectionName + "/schemas/" + schemaName + "/tables/"
    // + tableName + "/data");
    // assertDefaults(response);
    // Representation entity = response.getEntity();
    // SkysailResponse<GridData> skysailResponse = mapper.readValue(entity.getText(),
    // new TypeReference<SkysailResponse<GridData>>() {
    // });
    // assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
    // return skysailResponse.getData();
    // }

    protected void deleteConnection(String connectionName) throws Exception {
        org.restlet.Response response = delete("connections/" + connectionName);
        assertDefaults(response);
        Representation entity = response.getEntity();
        SkysailResponse<String> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<SkysailResponse<String>>() {
                });
        String data = skysailResponse.getData();
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        assertThat(skysailResponse.getMessage(), skysailResponse.getMessage(),
                is("deleted entity 'name, username, url'"));
    }

    private org.restlet.Response delete(String uri) {
        Request request = new Request(Method.DELETE, "/" + uri);
        return handleRequest(request);
    }

    protected org.restlet.Response get(String uri) {
        Request request = new Request(Method.GET, "/" + uri);
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
        ChallengeResponse authentication = new ChallengeResponse(ChallengeScheme.HTTP_BASIC, "admin", "skysail");
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

    protected void setUpPersistence(DbViewerApplication spy) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("testPU");
        Mockito.doAnswer(new Answer<EntityManager>() {
            @Override
            public EntityManager answer(InvocationOnMock invocation) throws Throwable {
                return emf.createEntityManager();
            }

        }).when(spy).getEntityManager();
    }

    protected DbViewerApplication setUpRestletApplication() throws ClassNotFoundException {
        // DbViewerComponent dbViewerComponent = new DbViewerComponent(null, null, null);
        dbViewerApplication = new DbViewerApplication(null, null, null);

        DbViewerApplication spy = Mockito.spy(dbViewerApplication);
        Application.setCurrent(spy);
        inboundRoot = dbViewerApplication.getInboundRoot();
        addMappings();
        return spy;
    }

}
