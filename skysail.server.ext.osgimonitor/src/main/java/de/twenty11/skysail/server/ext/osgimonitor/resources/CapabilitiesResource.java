package de.twenty11.skysail.server.ext.osgimonitor.resources;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleRevision;
import org.osgi.framework.wiring.BundleWiring;
import org.restlet.resource.Get;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twenty11.skysail.server.ext.osgimonitor.OsgiMonitorViewerApplication;
import de.twenty11.skysail.server.ext.osgimonitor.domain.CapabilityDescriptor;

@Presentation(preferred = PresentationStyle.LIST2)
public class CapabilitiesResource extends ListServerResource2<CapabilityDescriptor> {

    @Override
    @Get("html|json|csv")
    public SkysailResponse<List<CapabilityDescriptor>> getEntities() {
        return getEntities("All Capabilities");
    }
    
    @Override
    protected List<CapabilityDescriptor> getData() {
        List<CapabilityDescriptor> result = new ArrayList<CapabilityDescriptor>();
        OsgiMonitorViewerApplication app = (OsgiMonitorViewerApplication) getApplication();
        Bundle bundle = app.getBundle();
        BundleWiring bw = bundle.adapt(BundleWiring.class);
        for (BundleCapability cap : bw.getCapabilities(BundleRevision.PACKAGE_NAMESPACE)) {
            result.add(new CapabilityDescriptor(cap));
        }
        return result;
    }

}
