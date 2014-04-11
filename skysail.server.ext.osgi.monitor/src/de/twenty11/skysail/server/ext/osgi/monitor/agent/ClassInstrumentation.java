package de.twenty11.skysail.server.ext.osgi.monitor.agent;

import javassist.ClassPool;
import javassist.CtMethod;

public interface ClassInstrumentation {

    void instrument(CtMethod m, ClassPool classPool) throws Exception;

}
