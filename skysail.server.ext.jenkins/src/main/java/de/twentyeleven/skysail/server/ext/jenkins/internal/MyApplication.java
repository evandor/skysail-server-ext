package de.twentyeleven.skysail.server.ext.jenkins.internal;

import org.osgi.framework.BundleContext;
import org.restlet.Context;

import de.twenty11.skysail.server.restlet.RouteBuilder;
import de.twenty11.skysail.server.restlet.SkysailApplication;
import de.twenty11.skysail.server.services.ApplicationProvider;
import de.twentyeleven.skysail.server.ext.jenkins.MyRootResource;

/**
 * @author carsten
 * 
 */
public class MyApplication extends SkysailApplication implements ApplicationProvider {

    // non-arg constructor needed for scr
    public MyApplication() {
        this(null, null);
    }

    public MyApplication(Context componentContext) {
        this(null, componentContext);
    }

    /**
     * @param staticPathTemplate
     * @param bundleContext
     */
    public MyApplication(BundleContext bundleContext, Context componentContext) {
        super(componentContext == null ? null : componentContext.createChildContext());
        setDescription("RESTful OsgiMonitor bundle");
        setOwner("twentyeleven");
        setName("osgimonitor");
        setBundleContext(bundleContext);
    }

    protected void attach() {
        // @formatter:off
        router.attach(new RouteBuilder("", MyRootResource.class).setVisible(true));
        // @formatter:on
    }

}
