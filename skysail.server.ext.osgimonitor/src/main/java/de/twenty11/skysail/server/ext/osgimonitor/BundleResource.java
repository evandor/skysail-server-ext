package de.twenty11.skysail.server.ext.osgimonitor;

import java.util.Arrays;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.restlet.data.MediaType;
import org.restlet.data.Reference;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.ext.osgimonitor.BundleDetails;
import de.twenty11.skysail.common.ext.osgimonitor.RestfulBundle;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.server.ext.osgimonitor.internal.OsgiMonitorViewerApplication;
import de.twenty11.skysail.server.restlet.UniqueResultServerResource;

/**
 * Restlet Resource class for handling a Bundle.
 * 
 */
public class BundleResource extends UniqueResultServerResource<BundleDetails> implements RestfulBundle {

    private String bundleId;
    private String action;

    public BundleResource() {
        setName("osgimonitor bundle resource");
        setDescription("The resource containing bundle detail information");
    }

    @Override
    protected void doInit() throws ResourceException {
        bundleId = (String) getRequest().getAttributes().get("bundleId");
        action = (String) getRequest().getAttributes().get("action");
    }

    @Override
    @Get("html|json")
    public Response<BundleDetails> getBundle() {
        OsgiMonitorViewerApplication app = (OsgiMonitorViewerApplication) getApplication();
        BundleContext bundleContext = app.getBundleContext();
        Bundle bundle = bundleContext.getBundle(Long.parseLong(bundleId));
        BundleDetails details = new BundleDetails(bundle);
        return getEntity(details);
    }

    @Get("putform")
    public Representation formWithPut() {
        if (Arrays.asList("start", "stop", "update").contains(action)) {
            String start = "<html><head><title>form to issue PUT request</title></head>\n<body>\n";
            String stop = "</body></html>";
            StringRepresentation stringRepresentation = new StringRepresentation(start
                    + "<form action='?method=PUT' method='POST'><input type='submit'></form>" + stop);
            stringRepresentation.setMediaType(MediaType.TEXT_HTML);
            return stringRepresentation;
        } else {
            return new StringRepresentation("only 'start','stop' and 'update' are allowed as action");
        }
    }

    @Put
    public Representation startOrStopBundle() {
        OsgiMonitorViewerApplication app = (OsgiMonitorViewerApplication) getApplication();
        BundleContext bundleContext = app.getBundleContext();
        Bundle bundle = bundleContext.getBundle(Long.parseLong(bundleId));

        Reference resourceRef = getRequest().getResourceRef();
        try {
            if (resourceRef.toString().endsWith("/start")) {
                bundle.start();
            } else if (resourceRef.toString().endsWith("/stop")) {
                bundle.stop();
            } else if (resourceRef.toString().endsWith("/update")) {
                bundle.update();
            }
            return new StringRepresentation("Success!");
        } catch (BundleException e) {
            e.printStackTrace();
            return new StringRepresentation("Failure: " + e.getMessage());
        }
    }
}
