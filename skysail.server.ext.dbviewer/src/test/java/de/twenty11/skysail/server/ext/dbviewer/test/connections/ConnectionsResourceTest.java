package de.twenty11.skysail.server.ext.dbviewer.test.connections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.restlet.Application;
import org.restlet.representation.Representation;

import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerComponent;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;
import de.twenty11.skysail.server.ext.dbviewer.test.BaseTests;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionsResourceTest extends BaseTests {
    
    @Before
    public void setUp() throws Exception {
        DbViewerComponent dbViewerComponent = new DbViewerComponent();
        skysailApplication = dbViewerComponent.getApplication();
        
        SkysailApplication spy = Mockito.spy(skysailApplication);
        Application.setCurrent(spy);
        inboundRoot = skysailApplication.getInboundRoot();
        addMappings();
        
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("testPU");
        Mockito.doAnswer(new Answer<EntityManager>() {
            @Override
            public EntityManager answer(InvocationOnMock invocation) throws Throwable {
                return emf.createEntityManager();
             }
            
        }).when(spy).getEntityManager();
    }

    @Test
    public void can_create_and_read_connections() throws Exception {
        ConnectionDetails connection = new ConnectionDetails("name", "username", "password", "url", "driverClassName");
        create(connection);
        read();
    }
    
    private void create(ConnectionDetails connection) throws Exception {
        org.restlet.Response response = post("/dbviewer/connections/", connection);
        assertDefaults(response);
        Response<?> skysailResponse = mapper.readValue(response.getEntity().getText(),
                new TypeReference<Response<?>>() {
                });
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
    }

    private void read() throws Exception {
        org.restlet.Response response = get("/dbviewer/connections/");
        assertDefaults(response);
        Representation entity = response.getEntity();
        Response<List<ConnectionDetails>> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<Response<List<ConnectionDetails>>>() {
                });
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        assertThat(skysailResponse.getMessage(), skysailResponse.getData().size(), is(1));
    }

}
