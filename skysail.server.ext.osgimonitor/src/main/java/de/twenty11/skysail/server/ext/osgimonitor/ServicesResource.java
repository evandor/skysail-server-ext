package de.twenty11.skysail.server.ext.osgimonitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.ext.osgimonitor.RestfulServices;
import de.twenty11.skysail.common.ext.osgimonitor.ServiceDescriptor;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.osgimonitor.internal.OsgiMonitorViewerApplication;
import de.twenty11.skysail.server.restlet.ListServerResource;

/**
 * Restlet Resource class for handling OSGi Services.
 * 
 * Provides a method to retrieve services.
 * 
 * The managed entity is of type {@link ServiceDescriptor}, providing details.
 * 
 */
public class ServicesResource extends ListServerResource<ServiceDescriptor> implements RestfulServices {

	private List<ServiceReference> services = Collections.emptyList();

	public ServicesResource() {
		setName("osgimonitor bundles resource");
		setDescription("The resource containing the list of bundles");
	}

	@Override
	protected void doInit() throws ResourceException {
		OsgiMonitorViewerApplication app = (OsgiMonitorViewerApplication) getApplication();
		BundleContext bundleContext = app.getBundleContext();
		if (bundleContext != null) {
			try {
				ServiceReference[] allServiceReferences = bundleContext.getAllServiceReferences(null, null);
				services = Arrays.asList(allServiceReferences);
			} catch (InvalidSyntaxException e) {
				throw new ResourceException(e);
			}
		}
	}

	@Override
	@Get("html|json")
    public SkysailResponse<List<ServiceDescriptor>> getServices() {
		return getEntities(allServices(), "all Services");
	}



    @Post
	public Representation install(String location) {
		String prefix = "prefix";
		if (!location.startsWith(prefix)) {
			return new StringRepresentation("location didn't start with '" + prefix + "'");
		}
		return new StringRepresentation("success");
	}

    private List<ServiceDescriptor> allServices() {
        List<ServiceDescriptor> result = new ArrayList<ServiceDescriptor>();
		for (ServiceReference sr : services) {
			ServiceDescriptor descriptor = new ServiceDescriptor(sr);
			result.add(descriptor);
		}
		return result;
	}

}
