package de.twenty11.skysail.server.ext.osgi.monitor.agent.instrumentation.serviceregistration;

import javassist.ClassPool;
import javassist.CtMethod;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.MethodInstrumentation;

public class UnregisterServiceInstrumentation extends MethodInstrumentation {

    public UnregisterServiceInstrumentation(String callbackClassName, String callbackMethodName) {
        super(callbackClassName, callbackMethodName);
    }

    @Override
    public void instrument(CtMethod m, ClassPool classPool) {

        super.instrument(m, classPool);

        // String insertBeforeCode = getInsertBefore(ServiceRegistrationCallback.class.getSimpleName(),
        // "unregisterService", Param.fromObject("this"), Param.fromString(m.getDeclaringClass().getName()),
        // Param.fromString(m.getName()));
        insertBeforeMethod(defaultBeforeCode(callbackClassName, m));

        // String catchCode = getCatch(ServiceRegistrationCallback.class.getSimpleName(), Param.fromObject("this"),
        // Param.fromString(m.getDeclaringClass().getName()), Param.fromString(m.getName()));
        // insertCatchMethod(catchCode);
        insertCatchMethod(defaultCatchCode(m, callbackMethodName, classPool));

    }

}
