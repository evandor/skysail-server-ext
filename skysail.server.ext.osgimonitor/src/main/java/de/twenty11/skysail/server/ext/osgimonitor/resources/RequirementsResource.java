package de.twenty11.skysail.server.ext.osgimonitor.resources;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.wiring.BundleRequirement;
import org.osgi.framework.wiring.BundleRevision;
import org.osgi.framework.wiring.BundleWire;
import org.osgi.framework.wiring.BundleWiring;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twenty11.skysail.server.ext.osgimonitor.OsgiMonitorViewerApplication;
import de.twenty11.skysail.server.ext.osgimonitor.domain.Requirement;

@Presentation(preferred = PresentationStyle.LIST2)
public class RequirementsResource extends ListServerResource2<Requirement> {

    @Override
    protected List<Requirement> getData() {
        List<Requirement> result = new ArrayList<Requirement>();
        OsgiMonitorViewerApplication app = (OsgiMonitorViewerApplication) getApplication();
        
        BundleContext bundleContext = app.getBundleContext();
        Bundle[] bundles = bundleContext.getBundles();
        for (Bundle bundle : bundles) {
            addRequirements(result, bundle);
        }
        return result;
    }

    private void addRequirements(List<Requirement> result, Bundle bundle) {
        BundleWiring bw = bundle.adapt(BundleWiring.class);
        if (bw == null) {
            return;
        }
        List<BundleWire> providedWires = bw.getProvidedWires(BundleRevision.PACKAGE_NAMESPACE);
        
        
        for (BundleRequirement cap : bw.getRequirements(BundleRevision.PACKAGE_NAMESPACE)) {
            result.add(new Requirement(cap));
        }
    }

}
