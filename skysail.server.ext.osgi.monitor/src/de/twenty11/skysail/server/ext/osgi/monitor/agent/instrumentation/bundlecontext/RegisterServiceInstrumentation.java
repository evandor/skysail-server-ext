package de.twenty11.skysail.server.ext.osgi.monitor.agent.instrumentation.bundlecontext;

import javassist.ClassPool;
import javassist.CtMethod;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.MethodInstrumentation;

public class RegisterServiceInstrumentation extends MethodInstrumentation {

    private static final Logger logger = LoggerFactory.getLogger(MethodInstrumentation.class);

    public RegisterServiceInstrumentation(String callbackClassName, String callbackMethodName) {
        super(callbackClassName, callbackMethodName);
    }

    @Override
    public void instrument(CtMethod m, ClassPool classPool) {

        super.instrument(m, classPool);

        // MethodInfo methodInfo = m.getMethodInfo();
        //
        // LocalVariableAttribute table = (LocalVariableAttribute) methodInfo.getCodeAttribute().getAttribute(
        // javassist.bytecode.LocalVariableAttribute.tag);
        //
        // int index = 2;
        // String first = getVariableName(index++, methodInfo, table);
        // String second = getVariableName(index++, methodInfo, table);
        // String third = getVariableName(index++, methodInfo, table);
        //
        // String code = null;
        // String methodName = m.getName();
        //
        // code = getInsertBefore(callbackClassName, callbackMethodName,
        // Param.fromObject("this"), Param.fromObject(first), Param.fromObject(second), Param.fromObject(third));
        //
        // logger.info(code);
        // try {
        // m.insertBefore(code);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        insertBeforeMethod(defaultBeforeCode(callbackClassName, m));
        insertCatchMethod(defaultCatchCode(m, callbackMethodName, classPool));
    }

    private String getVariableName(int index, MethodInfo methodInfo, LocalVariableAttribute table) {
        int frameWithNameAtConstantPool = table.nameIndex(index);
        String variableName = methodInfo.getConstPool().getUtf8Info(frameWithNameAtConstantPool);
        return variableName;
    }

}
