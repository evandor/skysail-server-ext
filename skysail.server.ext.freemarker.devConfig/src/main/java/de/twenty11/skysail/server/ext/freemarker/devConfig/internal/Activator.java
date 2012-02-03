package de.twenty11.skysail.server.ext.freemarker.devConfig.internal;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

/**
 * A bundle checking other bundles for specific contributions ("FtlTemplates")
 * which is providing a service for consumers to access those contributions
 * (which are freemarker template files).
 * 
 * This - "devConfig" - bundle is meant to be used in development context
 * and parses the workspace of the eclipse project to find the ftl files - like
 * this you don't have to reinstall the bundle all the time during development.
 * 
 * This is not mean to be used in productive environments.
 * 
 * 
 * @author carsten
 * 
 */
public class Activator implements BundleActivator {

    /** slf4j based logger. */
    private static Logger       logger = LoggerFactory.getLogger(Activator.class);
    
    /** the service registration holder. */
    private ServiceRegistration serviceRegistration;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
     * )
     */
    @Override
    public final void start(final BundleContext context) throws Exception {
        registerFreemarkerConfigurationService(context);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public final void stop(final BundleContext context) throws Exception {
        // TODO unset service
    }

    /**
     * @param context
     *            bundle context
     */
    private void registerFreemarkerConfigurationService(final BundleContext context) {
        Hashtable<String, String> props = new Hashtable<String, String>(1);
        props.put("dynamicConfiguration", "true");

        // create new freemarker configuration based on WorkspaceUrlTemplateLoader
        Configuration config = new Configuration();
        config.setObjectWrapper(new DefaultObjectWrapper());
        config.setLocalizedLookup(false);
        config.setTemplateUpdateDelay(5);
        config.setTemplateLoader(new WorkspaceUrlTemplateLoader());
        
        // register the configuration
        serviceRegistration = context.registerService(Configuration.class.getName(),
                config, props);
    }

}
