package de.twenty11.skysail.server.ext.osgi.monitor.agent;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.wiring.BundleWiring;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.bundle.BundleCallback;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.bundlecontext.BundleContextCallback;

public class Const {

    public static final String OSGI_BUNDLE_CLASS_NAME = Bundle.class.getName();

    public static final String OSGI_BUNDLE_CONTEXT_CLASS_NAME = BundleContext.class.getName();

    public static final String OSGI_SERVICE_REG_CLASS_NAME = ServiceRegistration.class.getName();

    public static final String OSGI_WIRING_CLASS_NAME = BundleWiring.class.getName();

    public static final String REGISTER_SERVICE_SIGNATURE_1 = "([Ljava/lang/String;Ljava/lang/Object;Ljava/util/Dictionary;)Lorg/osgi/framework/ServiceRegistration;";

    public static final String REGISTER_SERVICE_SIGNATURE_2 = "(Ljava/lang/String;Ljava/lang/Object;Ljava/util/Dictionary;)Lorg/osgi/framework/ServiceRegistration;";

    public static final String REGISTER_SERVICE_SIGNATURE_3 = "(Ljava/lang/Class;Ljava/lang/Object;Ljava/util/Dictionary;)Lorg/osgi/framework/ServiceRegistration;";

    public static final String BUNDLE_CALLBACK = BundleCallback.class.getName();

    public static final String BUNDLE_CONTEXT_CALLBACK = BundleContextCallback.class.getName();

}
