package de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.bundle;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.Bundle;
import org.slf4j.Logger;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.Const;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.MethodIdentifier;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.OsgiMonitorCallback;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.messages.RuntimeExceptionMessage;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.descriptors.BundleDescriptor;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.instrumentation.bundle.AdaptBundleInstrumentation;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.instrumentation.bundle.GetStateBundleInstrumentation;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.instrumentation.bundle.StartBundleInstrumentation;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.instrumentation.bundle.StopBundleInstrumentation;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.instrumentation.bundle.UninstallBundleInstrumentation;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.instrumentation.bundle.UpdateBundleInstrumentation;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.bundle.AdaptBundleMessage;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.bundle.GetStateMessage;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.bundle.StartBundleMessage;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.bundle.StopBundleMessage;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.bundle.UninstallBundleMessage;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.bundle.UpdateBundleMessage;

/**
 * the class called from the instrumented methods.
 * 
 */
public class BundleCallback extends OsgiMonitorCallback {

    private static Map<Long, BundleDescriptor> bundles = new ConcurrentHashMap<Long, BundleDescriptor>();

    static {
        addMethodCallback(new MethodIdentifier(Const.OSGI_BUNDLE_CLASS_NAME, "start", "(I)V"),
                new StartBundleInstrumentation(BundleCallback.class.getSimpleName(), "start"));

        addMethodCallback(new MethodIdentifier(Const.OSGI_BUNDLE_CLASS_NAME, "start", "()V"),
                new StartBundleInstrumentation(BundleCallback.class.getSimpleName(), "start"));

        addMethodCallback(new MethodIdentifier(Const.OSGI_BUNDLE_CLASS_NAME, "stop", "(I)V"),
                new StopBundleInstrumentation(BundleCallback.class.getSimpleName(), "stop"));

        addMethodCallback(new MethodIdentifier(Const.OSGI_BUNDLE_CLASS_NAME, "stop", "()V"),
                new StopBundleInstrumentation(BundleCallback.class.getSimpleName(), "stop2"));

        // missing: update(InputStream)

        addMethodCallback(new MethodIdentifier(Const.OSGI_BUNDLE_CLASS_NAME, "update", "()V"),
                new UpdateBundleInstrumentation(BundleCallback.class.getSimpleName(), "update"));

        addMethodCallback(new MethodIdentifier(Const.OSGI_BUNDLE_CLASS_NAME, "uninstall", "()V"),
                new UninstallBundleInstrumentation(BundleCallback.class.getSimpleName(), "uninstall"));

        addMethodCallback(new MethodIdentifier(Const.OSGI_BUNDLE_CLASS_NAME, "getState", "()I"),
                new GetStateBundleInstrumentation(BundleCallback.class.getSimpleName(), "getState"));

        addMethodCallback(new MethodIdentifier(Const.OSGI_BUNDLE_CLASS_NAME, "adapt",
                "(Ljava/lang/Class;)Ljava/lang/Object;"),
                new AdaptBundleInstrumentation(BundleCallback.class.getSimpleName(), "adapt"));
    }

    public static void start(Bundle bundle, int option) {
        trackNewBundles(bundle);
        logJson(new StartBundleMessage(bundle));
    }

    public static void start(Bundle bundle) {
        trackNewBundles(bundle);
        logJson(new StartBundleMessage(bundle));
    }

    public static void stop(Bundle bundle, int option) {
        logJson(new StopBundleMessage(bundle));
    }

    public static void stop(Bundle bundle) {
        logJson(new StopBundleMessage(bundle));
    }

    public static void update(Bundle bundle) {
        logJson(new UpdateBundleMessage(bundle));
    }

    public static void uninstall(Bundle bundle) {
        logJson(new UninstallBundleMessage(bundle));
    }

    public static void catchMe(String methodName, RuntimeException e) {
        logErrorJson(new RuntimeExceptionMessage("bl", methodName, e));
    }

    public static void getState(Bundle bundle, int result) {
        BundleDescriptor descriptor = bundles.get(bundle.getBundleId());
        if (descriptor == null || descriptor.getState() == result) {
            return;
        }
        descriptor.setState(result);
        logJson(new GetStateMessage(bundle, result));
    }

    public static void adapt(Bundle bundle, Class<?> cls) {
        logJson(new AdaptBundleMessage(bundle, cls));
    }

    @Override
    public void setAgentLogger(Logger agentlogger) {
        BundleCallback.agentLogger = agentlogger;
    }

    private static void trackNewBundles(Bundle bundle) {
        if (!bundles.containsKey(bundle.getBundleId())) {
            bundles.put(bundle.getBundleId(), new BundleDescriptor(bundle));
        }
    }
}
