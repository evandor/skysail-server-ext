package de.twenty11.skysail.server.ext.inlinebrowser.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import edu.umd.cs.findbugs.annotations.NoWarning;


/**
 * Needed to get a hold on the bundle context when we do not use the ServiceProvider approach (for example,
 * when starting via web.xml + jetty).
 * 
 * @author carsten
 * 
 */
public class Activator implements BundleActivator {

    /** OSGi bundle context . */
    private static BundleContext context;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
     * )
     */
    @Override
    @NoWarning("findbug warning not relevant here")
    public final void start(final BundleContext bundleContext) throws Exception {
        Activator.context = bundleContext;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    @NoWarning("findbug warning not relevant here")
    public final void stop(final BundleContext bundleContext) throws Exception {
        Activator.context = null;
    }
    
    /**
     * @return the bundleContext
     */
    public static BundleContext getContext() {
        return context;
    }

}
