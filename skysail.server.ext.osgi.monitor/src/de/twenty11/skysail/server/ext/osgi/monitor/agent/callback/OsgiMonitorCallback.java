package de.twenty11.skysail.server.ext.osgi.monitor.agent.callback;

import java.util.HashMap;
import java.util.Map;

import javassist.ClassPool;
import javassist.CtMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.MethodIdentifier;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.MethodInstrumentation;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.messages.RuntimeExceptionMessage;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.descriptors.Param;

public abstract class OsgiMonitorCallback {

    private static final Logger logger = LoggerFactory.getLogger(OsgiMonitorCallback.class);

    private static Map<MethodIdentifier, MethodInstrumentation> methodCallbacks = new HashMap<MethodIdentifier, MethodInstrumentation>();

    private CtMethod method;

    protected static Logger agentLogger;

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();

    private static boolean isOff = false;

//    protected static StringBuilder handleParams(StringBuilder sb, Param[] params) {
//        for (int i = 0; i < params.length; i++) {
//            sb.append(params[i].toString());
//            if (i < params.length - 1) {
//                sb.append(",");
//            }
//        }
//        return sb;
//
//    }

//    protected static String getLogMsgPrefix(String string, String methodName) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("\"type\": \"").append(string).append("\", ").append("\"tstamp\": ").append(System.nanoTime())
//                .append(", ").append("\"method\": \"").append(methodName).append("\",");
//
//        return sb.toString();
//    }

    protected void refine(CtMethod method) {
        this.method = method;
    }

    public CtMethod getMethod() {
        return method;
    }

    public void instrument(String classIdentifier, CtMethod m, ClassPool classPool) throws Exception {
        MethodIdentifier methodIdentifier = new MethodIdentifier(classIdentifier, m.getMethodInfo().getName(),
                m.getSignature());
        MethodInstrumentation methodCallback = methodCallbacks.get(methodIdentifier);
        if (methodCallback == null) {
            logger.error("no callback found for method {}", methodIdentifier);
        }
        methodCallback.instrument(m, classPool);
    }

    public void instrument(MethodIdentifier methodIdentifier, CtMethod m, ClassPool classPool) {
        MethodInstrumentation methodCallback = methodCallbacks.get(methodIdentifier);
        if (methodCallback == null) {
            logger.error("no callback found for method {}", methodIdentifier);
        }
        methodCallback.instrument(m, classPool);

    }

    public void setAgentLogger(Logger agentlogger) {
        OsgiMonitorCallback.agentLogger = agentlogger;
    }

    protected static void logJson(Object obj) {
        if (isOff) {
            return;
        }
        try {
            String msg = writer.writeValueAsString(obj);
            agentLogger.info(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected static void logErrorJson(Object obj) {
            if (isOff) {
                return;
            }
            try {
                String msg = writer.writeValueAsString(obj);
                agentLogger.error(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
		
	}


    protected static void addMethodCallback(MethodIdentifier methodIdentifier, MethodInstrumentation methodCallback) {
        methodCallbacks.put(methodIdentifier, methodCallback);
    }

    public static void setOff() {
        OsgiMonitorCallback.isOff = true;
    }

    public static void setOn() {
        OsgiMonitorCallback.isOff = false;
    }

}
