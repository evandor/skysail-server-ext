package de.twenty11.skysail.server.ext.osgi.monitor.agent.callback;

import javassist.ClassPool;
import javassist.CtMethod;

import org.slf4j.Logger;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.MethodIdentifier;

/**
 * @author carsten
 * 
 */
public class CallbackDefinition {

    private String packageName;
    private OsgiMonitorCallback callback;

    public CallbackDefinition(OsgiMonitorCallback callback, Logger agentlogger) {
        this.callback = callback;
        this.packageName = callback.getClass().getPackage().getName();
        this.callback.setAgentLogger(agentlogger);
    }

    public String getPackageName() {
        return packageName;
    }

    // public String getInsertBefore(Param... params) {
    // return callback.getInsertBefore(params);
    // }

    public String getName() {
        return callback.getClass().getName();
    }

    public void refine(CtMethod method) {
        this.callback.refine(method);
    }

    public void instrument(String classIdentifier, CtMethod m, ClassPool classPool) throws Exception {
        this.callback.instrument(classIdentifier, m, classPool);
    }

	public void instrument(MethodIdentifier methodIdentifier, CtMethod m,
			ClassPool classPool) {
		this.callback.instrument(methodIdentifier, m, classPool);
	}

}
