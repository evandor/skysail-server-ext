package de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.serviceregistration;

import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.MethodIdentifier;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.OsgiMonitorCallback;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.messages.RuntimeExceptionMessage;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.instrumentation.serviceregistration.UnregisterServiceInstrumentation;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.serviceregistration.UnregisterServiceMessage;

/**
 * the class called from the instrumented methods.
 * 
 */
public class ServiceRegistrationCallback extends OsgiMonitorCallback {

    private static Logger agentLogger;

    static {
        addMethodCallback(new MethodIdentifier(ServiceRegistration.class.getName(), "unregister", "()V"),
                new UnregisterServiceInstrumentation(ServiceRegistrationCallback.class.getSimpleName(), "unregister"));
    }

    public static void unregister(ServiceRegistration sr) {
        logJson(new UnregisterServiceMessage(sr));
        // String logMsgPrefix = getLogMsgPrefix("sr", "unregister");
        // String ref = sr.toString();
        // String bsn = "";
        // String properties = "";
        // String usedBy = "";
        // ServiceReference reference = sr.getReference();
        // if (reference != null) {
        // bsn = reference.getBundle().getSymbolicName();
        // String[] propertyKeys = reference.getPropertyKeys();
        // StringBuilder sb = new StringBuilder();
        // for (String key : propertyKeys) {
        // Object property = reference.getProperty(key);
        // sb.append(key).append(": ").append(property.toString()).append(", ");
        // }
        // properties = sb.toString();
        // Bundle[] usingBundles = reference.getUsingBundles();
        // if (usingBundles != null) {
        // sb = new StringBuilder();
        // for (Bundle bundle : usingBundles) {
        // sb.append(bundle.getSymbolicName()).append(", ");
        // }
        // usedBy = sb.toString();
        // }
        // }
        // agentLogger.info("{" + logMsgPrefix + "\"bsn\": \"{}\", \n\"props\": \"{}\", \n\"usedBy\": \"{}\"},",
        // new Object[] { bsn, properties, usedBy });
    }

    public static void catchMe(ServiceRegistration sr, String objectName, String methodName, RuntimeException e) {
        agentLogger.info("{\"type\": \"sr\", \"method\": \"{}\", \"bsn\": \"{}\"},", methodName, sr.toString());
    }

    @Override
    public void setAgentLogger(Logger agentlogger) {
        ServiceRegistrationCallback.agentLogger = agentlogger;
    }

    public static void catchMe(String methodName, RuntimeException e) {
        logJson(new RuntimeExceptionMessage("sr", methodName, e));
    }

}
