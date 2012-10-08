package de.twenty11.skysail.server.ext.dbviewer.test;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Protocol;
import org.restlet.resource.ServerResource;

import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerComponent;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;

public class ComponentTests {

    protected static Restlet inboundRoot;
    protected SkysailApplication skysailApplication;
    protected ObjectMapper mapper = new ObjectMapper();
    protected static DbViewerComponent dbViewerComponent;
    private static Server server;
    protected ChallengeResponse authentication;

    @BeforeClass
    public static void startTests() throws Exception {
        dbViewerComponent = new DbViewerComponent();
        server = new Server(Protocol.HTTP, 8111, dbViewerComponent);
        server.start();
    }

    @Before
    public void setUp() throws Exception {
        authentication = new ChallengeResponse(ChallengeScheme.HTTP_BASIC, "scott", "tiger");
        skysailApplication = dbViewerComponent.getApplication();
        Application.setCurrent(skysailApplication);
        inboundRoot = skysailApplication.getInboundRoot();
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
            skysailApplication.attachToRouter("" + mapping.getKey(), resourceClass);
        }
    }

}
