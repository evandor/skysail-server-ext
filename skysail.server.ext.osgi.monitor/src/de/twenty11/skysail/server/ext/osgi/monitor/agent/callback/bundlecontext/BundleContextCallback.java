package de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.bundlecontext;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.Const;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.MethodIdentifier;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.OsgiMonitorCallback;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.messages.RuntimeExceptionMessage;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.instrumentation.bundlecontext.RegisterServiceInstrumentation;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.bundlecontext.RegisterServiceMessage;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.bundlecontext.UngetServiceMessage;

/**
 * the class called from the instrumented methods.
 * 
 */
public class BundleContextCallback extends OsgiMonitorCallback {

    public static Logger agentLogger;

    static {

        addMethodCallback(new MethodIdentifier(Const.OSGI_BUNDLE_CONTEXT_CLASS_NAME, "registerService",
                Const.REGISTER_SERVICE_SIGNATURE_1),
                new RegisterServiceInstrumentation(BundleContextCallback.class.getSimpleName(), "registerService"));
        addMethodCallback(new MethodIdentifier("org.apache.felix.framework.ext.FelixBundleContext", "registerService",
                Const.REGISTER_SERVICE_SIGNATURE_1),
                new RegisterServiceInstrumentation(BundleContextCallback.class.getSimpleName(), "registerService"));

        addMethodCallback(new MethodIdentifier(Const.OSGI_BUNDLE_CONTEXT_CLASS_NAME, "registerService",
                Const.REGISTER_SERVICE_SIGNATURE_2),
                new RegisterServiceInstrumentation(BundleContextCallback.class.getSimpleName(), "registerService2"));
        addMethodCallback(new MethodIdentifier("org.apache.felix.framework.ext.FelixBundleContext", "registerService",
                Const.REGISTER_SERVICE_SIGNATURE_2),
                new RegisterServiceInstrumentation(BundleContextCallback.class.getSimpleName(), "registerService2"));

        addMethodCallback(new MethodIdentifier(Const.OSGI_BUNDLE_CONTEXT_CLASS_NAME, "registerService",
                Const.REGISTER_SERVICE_SIGNATURE_3),
                new RegisterServiceInstrumentation(BundleContextCallback.class.getSimpleName(), "registerService3"));
        addMethodCallback(new MethodIdentifier("org.apache.felix.framework.ext.FelixBundleContext", "registerService",
                Const.REGISTER_SERVICE_SIGNATURE_3),
                new RegisterServiceInstrumentation(BundleContextCallback.class.getSimpleName(), "registerService3"));

        addMethodCallback(new MethodIdentifier("org.apache.felix.framework.ext.FelixBundleContext", "ungetService",
                "(Lorg/osgi/framework/ServiceReference;)Z"), new RegisterServiceInstrumentation(
                BundleContextCallback.class.getSimpleName(), "ungetService"));

    }

    public static void registerService(BundleContext bundleContext, String[] strArr, Object obj, Dictionary<?, ?> dict) {
        logJson(new RegisterServiceMessage(bundleContext, strArr, obj, dict));
    }

    public static void registerService(BundleContext bundleContext, String str, Object obj, Dictionary<?, ?> dict) {
        String[] strArr = new String[1];
        strArr[0] = str;
        logJson(new RegisterServiceMessage(bundleContext, strArr, obj, dict));
    }

    public static void registerService(BundleContext bundleContext, Class<?> cls, Object obj, Dictionary<?, ?> dict) {
        String[] strArr = new String[1];
        strArr[0] = cls.toString();
        logJson(new RegisterServiceMessage(bundleContext, strArr, obj, dict));
    }

    public static void ungetService(BundleContext bundleContext, ServiceReference sr) {
        logJson(new UngetServiceMessage(sr));
    }

    public static void catchMe(String methodName, RuntimeException e) {
        logJson(new RuntimeExceptionMessage("bc", methodName, e));
    }

}
