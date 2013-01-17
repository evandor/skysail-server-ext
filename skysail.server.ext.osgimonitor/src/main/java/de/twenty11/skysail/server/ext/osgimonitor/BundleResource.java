package de.twenty11.skysail.server.ext.osgimonitor;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.restlet.resource.Get;
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

    public BundleResource() {
        setName("osgimonitor bundle resource");
        setDescription("The resource containing bundle detail information");
    }

    @Override
    protected void doInit() throws ResourceException {
    	bundleId = (String)getRequest().getAttributes().get("bundleId");
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

}
