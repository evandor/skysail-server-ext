package de.twentyeleven.skysail.skysail.server.ext.osgideps;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    private JGraphOsgiServicesVisualizer visualizer;

    @Override
    public void start(BundleContext context) throws Exception {
        visualizer = new JGraphOsgiServicesVisualizer();
        visualizer.buildGraphFromOsgiContext(context);
        visualizer.run();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        visualizer = null;
    }

}
