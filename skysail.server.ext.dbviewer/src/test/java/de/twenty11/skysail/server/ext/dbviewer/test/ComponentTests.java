package de.twenty11.skysail.server.ext.dbviewer.test;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.resource.ServerResource;

import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerComponent;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;

public class ComponentTests {

    protected Restlet inboundRoot;
    protected SkysailApplication skysailApplication;
    protected ObjectMapper mapper = new ObjectMapper();
    protected DbViewerComponent dbViewerComponent;

    @Before
    public void setUp() throws Exception {
        dbViewerComponent = new DbViewerComponent();
        skysailApplication = dbViewerComponent.getApplication();
        Application.setCurrent(skysailApplication);
        inboundRoot = skysailApplication.getInboundRoot();
        addMappings();
        Server mailServer = new Server(Protocol.HTTP, 8111, dbViewerComponent);
        // mailServer.setNext(skysailApplication);
        mailServer.start();

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
