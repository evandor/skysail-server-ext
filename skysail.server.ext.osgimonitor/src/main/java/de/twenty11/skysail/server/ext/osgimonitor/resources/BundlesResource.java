package de.twenty11.skysail.server.ext.osgimonitor.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.common.ext.osgimonitor.domain.BundleDescriptor;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twenty11.skysail.server.ext.osgimonitor.OsgiMonitorViewerApplication;

/**
 * Restlet Resource class for OSGi Bundles.
 * 
 */
@Presentation(preferred = PresentationStyle.LIST2)
public class BundlesResource extends ListServerResource2<BundleDescriptor> {

    private List<Bundle> bundles;

    public BundlesResource() {
        setName("osgimonitor bundles resource");
        setDescription("The resource containing the list of bundles");
    }

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        OsgiMonitorViewerApplication app = (OsgiMonitorViewerApplication) getApplication();
        BundleContext bundleContext = app.getBundleContext();
        if (bundleContext == null) {
            bundles = Collections.emptyList();
        } else {
            bundles = Arrays.asList(bundleContext.getBundles());
        }
    }

    @Override
    @Get("html|json|csv")
    public SkysailResponse<List<BundleDescriptor>> getEntities() {
        return super.getEntities("listing all bundles");
    }

    @Override
    protected List<BundleDescriptor> getData() {
        List<BundleDescriptor> result = new ArrayList<BundleDescriptor>();
        for (Bundle bundle : bundles) {
            BundleDescriptor bundleDescriptor = new BundleDescriptor(bundle);
            if (filterMatches(bundleDescriptor)) {
                result.add(bundleDescriptor);
            }
        }
        return result;
    }

    @Post
    public Representation install(String location) {
        String prefix = "prefix";
        if (!location.startsWith(prefix)) {
            return new StringRepresentation("location didn't start with '" + prefix + "'");
        }
        return new StringRepresentation("success");
    }

    @Override
    protected boolean match(BundleDescriptor object, String pattern) {
        return object.getSymbolicName().contains(pattern);
    }

}
