package de.twenty11.skysail.server.ext.osgimonitor.resources;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleRevision;
import org.osgi.framework.wiring.BundleWire;
import org.osgi.framework.wiring.BundleWiring;
import org.restlet.data.Form;
import org.restlet.resource.Get;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twenty11.skysail.server.ext.osgimonitor.OsgiMonitorViewerApplication;
import de.twenty11.skysail.server.ext.osgimonitor.domain.Capability;

@Presentation(preferred = PresentationStyle.LIST2)
public class CapabilitiesResource extends ListServerResource2<Capability> {

    @Override
    @Get("html|json|csv")
    public SkysailResponse<List<Capability>> getEntities() {
        return getEntities("All Capabilities");
    }

    @Override
    protected List<Capability> getData() {
        List<Capability> result = new ArrayList<Capability>();
        OsgiMonitorViewerApplication app = (OsgiMonitorViewerApplication) getApplication();

        BundleContext bundleContext = app.getBundleContext();
        Bundle[] bundles = bundleContext.getBundles();
        for (Bundle bundle : bundles) {
            addCapabilities(result, bundle);
        }
        return result;
    }

    private void addCapabilities(List<Capability> result, Bundle bundle) {
        BundleWiring bw = bundle.adapt(BundleWiring.class);
        if (bw == null) {
            return;
        }
        List<BundleWire> providedWires = bw.getProvidedWires(BundleRevision.PACKAGE_NAMESPACE);

        for (BundleCapability cap : bw.getCapabilities(BundleRevision.PACKAGE_NAMESPACE)) {
            result.add(new Capability(cap, bundle));
        }
    }

    @Override
    public Capability getData(Form form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SkysailResponse<?> addEntity(Capability entity) {
        // TODO Auto-generated method stub
        return null;
    }

}
