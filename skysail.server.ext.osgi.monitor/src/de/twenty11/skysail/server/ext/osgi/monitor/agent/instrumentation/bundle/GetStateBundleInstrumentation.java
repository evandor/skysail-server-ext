package de.twenty11.skysail.server.ext.osgi.monitor.agent.instrumentation.bundle;

import javassist.ClassPool;
import javassist.CtMethod;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.MethodInstrumentation;

public class GetStateBundleInstrumentation extends MethodInstrumentation {

    public GetStateBundleInstrumentation(String callbackClassName, String callbackMethodName) {
        super(callbackClassName, callbackMethodName);
    }

    @Override
    public void instrument(CtMethod m, ClassPool classPool) {
        super.instrument(m, classPool);

        insertAfterMethod("{BundleCallback.getState($0, $_);}");
        // insertBeforeMethod(defaultBeforeCode(m, callbackMethodName));
        // insertCatchMethod(defaultCatchCode(m, callbackMethodName, classPool));
        insertCatchMethod(defaultCatchCode(m, callbackMethodName, classPool));
    }

}
