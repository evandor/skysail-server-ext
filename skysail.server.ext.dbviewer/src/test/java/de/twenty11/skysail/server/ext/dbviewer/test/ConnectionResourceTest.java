package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

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

@RunWith(MockitoJUnitRunner.class)
public class ConnectionResourceTest extends BaseTests {
    
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
    public void can_read_connection() throws Exception {
        ConnectionDetails connection = new ConnectionDetails("name", "username", "password", "url", "driverClassName");
        create(connection);
        read();
    }

    private void read() throws Exception {
        org.restlet.Response response = get("/dbviewer/connections/name");
        assertDefaults(response);
        Representation entity = response.getEntity();
        Response<ConnectionDetails> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<Response<ConnectionDetails>>() {
                });
        ConnectionDetails data = skysailResponse.getData();
        assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
        assertThat(data.getUsername(), is(equalTo("username")));
    }

}
