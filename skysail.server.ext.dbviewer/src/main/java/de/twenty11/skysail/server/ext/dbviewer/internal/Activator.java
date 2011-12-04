//package de.twenty11.skysail.server.ext.dbviewer.internal;
//
//import org.osgi.framework.BundleActivator;
//import org.osgi.framework.BundleContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
///**
// * 
// * @author carsten
// * 
// */
//public class Activator implements BundleActivator {
//
//    /** slf4j based logger. */
//    private static Logger       logger = LoggerFactory.getLogger(Activator.class);
//
//    /** OSGi bundle context . */
//    private BundleContext context;
//
//    /*
//     * (non-Javadoc)
//     * 
//     * @see
//     * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
//     * )
//     */
//    @Override
//    public final void start(final BundleContext context) throws Exception {
//        this.context = context;
//        DbViewer.getInstance().setContext(context);
//    }
//
//    /*
//     * (non-Javadoc)
//     * 
//     * @see
//     * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
//     */
//    @Override
//    public final void stop(final BundleContext context) throws Exception {
//        this.context = null;
//    }
//
//}
