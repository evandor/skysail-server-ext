package de.twenty11.skysail.server.ext.osgi.monitor.agent.instrumentation.bundle;

import javassist.ClassPool;
import javassist.CtMethod;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.MethodInstrumentation;

public class StopBundleInstrumentation extends MethodInstrumentation {

    public StopBundleInstrumentation(String callbackClassName, String callbackMethodName) {
        super(callbackClassName, callbackMethodName);

    }

    @Override
    public void instrument(CtMethod m, ClassPool classPool) {
        super.instrument(m, classPool);
        insertBeforeMethod(defaultBeforeCode(callbackClassName, m));
        // insertCatchMethod(defaultCatchCode(m, callbackMethodName, classPool));
        insertCatchMethod(defaultCatchCode(m, callbackMethodName, classPool));
    }

}
