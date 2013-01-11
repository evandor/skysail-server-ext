package de.twenty11.skysail.server.ext.osgimonitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.ext.osgimonitor.BundleDetails;
import de.twenty11.skysail.common.ext.osgimonitor.RestfulBundles;
import de.twenty11.skysail.common.ext.osgimonitor.ServiceReferenceDetails;
import de.twenty11.skysail.common.graphs.Graph;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.server.ext.osgimonitor.internal.OsgiMonitorViewerApplication;
import de.twenty11.skysail.server.restlet.ListServerResource;

/**
 * Restlet Resource class for handling Connections.
 * 
 * Provides a method to retrieve the existing connections and to add a new one.
 * 
 * The managed entity is of type {@link BundleDetails}, providing details (like jdbc url, username and password about
 * what is needed to actually connect to a datasource.
 * 
 */
@Graph(nodesPath = "/osgimonitor/bundles", edgesPath = "/osgimonitor/services")
public class BundlesResource extends ListServerResource<BundleDetails> implements RestfulBundles {

    private List<Bundle> bundles;

    /** slf4j based logger implementation */
    // private static Logger logger = LoggerFactory.getLogger(BundlesResource.class);

    public BundlesResource() {
        setName("osgimonitor bundles resource");
        setDescription("The resource containing the list of bundles");
    }

    @Override
    @Get("html|json")
    public Response<List<BundleDetails>> getBundles() {
        return getEntities(allBundles(), "all Bundles");
    }

    @Override
    protected void doInit() throws ResourceException {
        // BundleContext bundleContext = (BundleContext) getContext().getAttributes().get(
        // OsgiMonitorViewerApplication.class.getName() + ".bundleContext");
        // getVariants().add(new Variant(MediaType.TEXT_HTML));
        OsgiMonitorViewerApplication app = (OsgiMonitorViewerApplication) getApplication();
        BundleContext bundleContext = app.getBundleContext();
        if (bundleContext == null) {
            bundles = Collections.emptyList();
        } else {
            bundles = Arrays.asList(bundleContext.getBundles());
        }
    }

    @Post
    public Representation install(String location) {
        String prefix = "prefix";
        if (!location.startsWith(prefix)) {
            return new StringRepresentation("location didn't start with '" + prefix + "'");
        }
        OsgiMonitorViewerApplication application = (OsgiMonitorViewerApplication) getApplication();
        // application
        return new StringRepresentation("success");
    }

    private List<BundleDetails> allBundles() {
        List<BundleDetails> result = new ArrayList<BundleDetails>();
        // List<Bundle> bundles = Activator.getBundles();
        for (Bundle bundle : bundles) {
            BundleDetails bundleDetail = new BundleDetails();
            bundleDetail.setSymbolicName(bundle.getLocation());
            bundleDetail.setBundleId(bundle.getBundleId());
            // bundleDetail.setHeaders(getDetails(bundle.getHeaders()));
            bundleDetail.setLastModified(bundle.getLastModified());
            bundleDetail.setRegisteredServices(getDetails(bundle.getRegisteredServices()));
            bundleDetail.setServicesInUse(getDetails(bundle.getServicesInUse()));
            bundleDetail.setState(bundle.getState());
            bundleDetail.setVersion(bundle.getVersion());
            bundleDetail.setSymbolicName(bundle.getSymbolicName());
            result.add(bundleDetail);
        }
        return result;
    }

    private Map<String, String> getDetails(Dictionary headers) {
        Map<String, String> result = new HashMap<String, String>();
        Enumeration keys = headers.keys();
        while (keys.hasMoreElements()) {
            Object nextElement = keys.nextElement();
            result.put(nextElement.toString(), headers.get(nextElement).toString());
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
            srd.setPropertyKeys(serviceReference.getPropertyKeys());
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
