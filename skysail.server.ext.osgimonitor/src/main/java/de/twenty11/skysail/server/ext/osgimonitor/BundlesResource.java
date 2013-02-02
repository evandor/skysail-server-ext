package de.twenty11.skysail.server.ext.osgimonitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.ext.osgimonitor.BundleDescriptor;
import de.twenty11.skysail.common.ext.osgimonitor.BundleDetails;
import de.twenty11.skysail.common.ext.osgimonitor.RestfulBundles;
import de.twenty11.skysail.common.ext.osgimonitor.ServiceReferenceDetails;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.server.ext.osgimonitor.internal.OsgiMonitorViewerApplication;
import de.twenty11.skysail.server.restlet.ListServerResource;

/**
 * Restlet Resource class for handling Connections.
 * 
 * Provides a method to retrieve the existing connections and to add a new one.
 * 
 * The managed entity is of type {@link BundleDetails}, providing details (like
 * jdbc url, username and password about what is needed to actually connect to a
 * datasource.
 * 
 */
public class BundlesResource extends ListServerResource<BundleDescriptor> implements RestfulBundles {

	private List<Bundle> bundles;

	public BundlesResource() {
		setName("osgimonitor bundles resource");
		setDescription("The resource containing the list of bundles");
	}

	@Override
	protected void doInit() throws ResourceException {
		OsgiMonitorViewerApplication app = (OsgiMonitorViewerApplication) getApplication();
		BundleContext bundleContext = app.getBundleContext();
		if (bundleContext == null) {
			bundles = Collections.emptyList();
		} else {
			bundles = Arrays.asList(bundleContext.getBundles());
		}
	}

	@Override
	@Get("html|json")
    public Response<List<BundleDescriptor>> getBundles() {
		return getEntities(allBundles(), "all Bundles");
	}



    @Post
	public Representation install(String location) {
		String prefix = "prefix";
		if (!location.startsWith(prefix)) {
			return new StringRepresentation("location didn't start with '" + prefix + "'");
		}
		return new StringRepresentation("success");
	}

    private List<BundleDescriptor> allBundles() {
        List<BundleDescriptor> result = new ArrayList<BundleDescriptor>();
		for (Bundle bundle : bundles) {
            BundleDescriptor bundleDetail = new BundleDescriptor(bundle);
			result.add(bundleDetail);
		}
		return result;
	}

	private List<ServiceReferenceDetails> getDetails(ServiceReference[] registeredServices) {
		List<ServiceReferenceDetails> details = new ArrayList<ServiceReferenceDetails>();
		if (registeredServices == null) {
			return details;
		}
		for (ServiceReference serviceReference : registeredServices) {
			ServiceReferenceDetails srd = new ServiceReferenceDetails();
			srd.setBundleId(serviceReference.getBundle().getBundleId());
			srd.setName(serviceReference.toString());
            // srd.setProperties(serviceReference.getPropertyKeys());
			srd.setUsingBundles(getDetails(serviceReference.getUsingBundles()));
			details.add(srd);
		}
		return details;
	}

	private List<BundleDetails> getDetails(Bundle[] usingBundles) {
		List<BundleDetails> details = new ArrayList<BundleDetails>();
		if (usingBundles == null) {
			return details;
		}
		for (Bundle bundle : usingBundles) {
			BundleDetails bundleDetails = new BundleDetails();
			bundleDetails.setBundleId(bundle.getBundleId());
			bundleDetails.setSymbolicName(bundle.getSymbolicName());
			bundleDetails.setVersion(bundle.getVersion());
			details.add(bundleDetails);
		}
		return details;
	}

}
