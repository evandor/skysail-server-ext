package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.mockito.Mockito.mock;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.osgi.service.component.ComponentContext;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Protocol;
import org.restlet.resource.ServerResource;

import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerApplication;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerComponent;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;

public class ComponentTests {

    protected static Restlet inboundRoot;
    protected DbViewerApplication dbViewerApplication;
    protected ObjectMapper mapper = new ObjectMapper();
    protected static DbViewerComponent dbViewerComponent;
    private static Server server;
    protected ChallengeResponse authentication;

    @BeforeClass
    public static void startTests() throws Exception {
        ComponentContext mock = mock(ComponentContext.class);
        dbViewerComponent = new DbViewerComponent(mock, null, null);
        server = new Server(Protocol.HTTP, 8111, dbViewerComponent);
        server.start();
    }

    @Before
    public void setUp() throws Exception {
        authentication = new ChallengeResponse(ChallengeScheme.HTTP_BASIC, "admin", "skysail");
        dbViewerApplication = dbViewerComponent.getApplication();
        Application.setCurrent(dbViewerApplication);
        inboundRoot = dbViewerApplication.getInboundRoot();
        addMappings();
    }

    @AfterClass
    public static void stopTests() throws Exception {
        server.stop();
    }

    protected void addMappings() throws ClassNotFoundException {
        Map<String, String> urlMapping = new DbViewerUrlMapper().provideUrlMapping();
        for (Map.Entry<String, String> mapping : urlMapping.entrySet()) {
            @SuppressWarnings("unchecked")
            Class<? extends ServerResource> resourceClass = (Class<? extends ServerResource>) Class.forName(mapping
                    .getValue());
            dbViewerApplication.attachToRouter("" + mapping.getKey(), resourceClass);
        }
    }

}
