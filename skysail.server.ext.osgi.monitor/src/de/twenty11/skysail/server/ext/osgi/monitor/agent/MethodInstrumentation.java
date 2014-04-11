package de.twenty11.skysail.server.ext.osgi.monitor.agent;

import java.util.ArrayList;
import java.util.List;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.descriptors.Param;

public abstract class MethodInstrumentation {

    private static final Logger logger = LoggerFactory.getLogger(MethodInstrumentation.class);

    private CtMethod m;
    private ClassPool classPool;

    protected String callbackMethodName;
    protected String callbackClassName;

    public MethodInstrumentation(String callbackClassName, String callbackMethodName) {
        this.callbackClassName = callbackClassName;
        this.callbackMethodName = callbackMethodName;
    }

    public void instrument(CtMethod m, ClassPool classPool) {
        this.m = m;
        this.classPool = classPool;
    }

    protected String defaultBeforeCode(String callbackClassName, CtMethod m) {
        List<String> params = new ArrayList<String>();
        try {
            CtClass[] parameterTypes = m.getParameterTypes();
            // CtClass returnType = m.getReturnType();
            boolean isStatic = Modifier.isStatic(m.getModifiers());

            int i = 0;
            if (isStatic) {
                i = 1;
            } else {
                params.add("$0");
            }
            for (CtClass param : parameterTypes) {
                params.add("$" + (++i));
            }
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return getInsertBefore(callbackClassName, m.getName(), params);
    }

    protected String defaultCatchCode(CtMethod m, String callbackMethodName, ClassPool classPool) {
        // return getCatch(
        // ServiceRegistrationCallback.class.getSimpleName(),
        // Param.fromObject("this"),
        // Param.fromString(m.getDeclaringClass().getName()),
        // Param.fromString(m.getName())
        // );
        // StringBuilder sb = new StringBuilder("{").append(className).append(".catchMe(");
        // handleParams(sb, params);
        // sb.append(", $e); throw new RuntimeException($e);}");
        // return sb.toString();

        List<String> params = new ArrayList<String>();
        try {
            CtClass[] parameterTypes = m.getParameterTypes();
            // CtClass returnType = m.getReturnType();
            boolean isStatic = Modifier.isStatic(m.getModifiers());

            int i = 0;
            if (isStatic) {
                i = 1;
            } else {
                params.add("$0");
            }
            for (CtClass param : parameterTypes) {
                params.add("$" + (++i));
            }
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // return get(callbackClassName, m.getName(), params);
        return "{" + callbackClassName + ".catchMe(\"" + m.getName() + "\", $e); throw new RuntimeException($e);}";

    }

    protected void insertBeforeMethod(String code) {
        logger.info(code);
        try {
            m.insertBefore(code);
        } catch (CannotCompileException e) {
            logger.error("Problem with code {} in method {}", code, m, e);
        }
    }

    protected void insertCatchMethod(String code) {
        logger.info(code);
        try {
            m.addCatch(code, classPool.get("java.lang.RuntimeException"));
        } catch (Exception e) {
            logger.error("Problem with code {} in method {}", code, m, e);
        }
    }

    protected void insertAfterMethod(String code) {
        logger.info(code);
        try {
            m.insertAfter(code);
        } catch (Exception e) {
            logger.error("Problem with code {} in method {}", code, m, e);
        }
    }

    // public static String getInsertBefore(String className, String callbackMethodName, Param... params) {
    // StringBuilder sb = new StringBuilder("{").append(className).append(".").append(callbackMethodName).append("(");
    // handleParams(sb, params);
    // sb.append(");}");
    // return sb.toString();
    // }

    protected String getInsertBefore(String className, String callbackMethodName, List<String> params) {
        StringBuilder sb = new StringBuilder("{").append(className).append(".").append(callbackMethodName).append("(");
        handleParams(sb, params);
        sb.append(");}");
        return sb.toString();
    }

    private static StringBuilder handleParams(StringBuilder sb, List<String> params) {
        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).toString());
            if (i < params.size() - 1) {
                sb.append(",");
            }
        }
        return sb;

    }

    protected static StringBuilder handleParams(StringBuilder sb, Param[] params) {
        for (int i = 0; i < params.length; i++) {
            sb.append(params[i].toString());
            if (i < params.length - 1) {
                sb.append(",");
            }
        }
        return sb;

    }

    public static String getCatch(String className, Param... params) {
        StringBuilder sb = new StringBuilder("{").append(className).append(".catchMe(");
        handleParams(sb, params);
        sb.append(", $e); throw new RuntimeException($e);}");
        return sb.toString();
    }

}
