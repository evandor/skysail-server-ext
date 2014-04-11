package de.twenty11.skysail.server.ext.osgi.monitor.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.List;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.CallbackDefinition;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.Callbacks;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.serviceregistration.ServiceRegistrationCallback;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.config.JsonConfig;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.descriptors.CallbackDescriptor;

/**
 * A class file transformer which finds the Classes implementing the underlying OSGi framework and transforms them for
 * monitoring the framework internally.
 * 
 */
public class OsgiFrameworkTransformer implements ClassFileTransformer {

    private static final Logger logger = LoggerFactory.getLogger(OsgiFrameworkTransformer.class);

    private ClassPool classPool;
    private Callbacks callbacks;

    private List<CallbackDescriptor> config;

    public OsgiFrameworkTransformer(ClassPool classPool, Callbacks callbacks, Logger agentlogger) {
        this.classPool = classPool;
        this.callbacks = callbacks;
        for (String packageName : callbacks.packages()) {
            logger.debug("adding package {} to the classPool", packageName);
            this.classPool.importPackage(packageName);
        }
        config = new JsonConfig().readConfig("/config/osgi.json");
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        // "return early" if it makes sense
        if (ignore(className)) {
            return classfileBuffer;
        }

        try {

            CtClass ctClass = classPool.get(className.replace("/", "."));

            checkForInstrumentation(ctClass);

            if (classImplements(ctClass, Const.OSGI_BUNDLE_CLASS_NAME)) {
                instrumentBundleImplementor(ctClass);

            } else if (classImplements(ctClass, Const.OSGI_BUNDLE_CONTEXT_CLASS_NAME)) {
                instrumentBundleContextImplementor(ctClass, Const.OSGI_BUNDLE_CONTEXT_CLASS_NAME);

            } else if (classImplements(ctClass, "org.apache.felix.framework.ext.FelixBundleContext")) {
                instrumentBundleContextImplementor(ctClass, "org.apache.felix.framework.ext.FelixBundleContext");

            } else if (classImplements(ctClass, Const.OSGI_SERVICE_REG_CLASS_NAME)) {
                instrumentServiceRegistrationImplementor(ctClass);

            } else {
                return classfileBuffer;
            }
            classfileBuffer = ctClass.toBytecode();
            ctClass.detach();

        } catch (NotFoundException nfe) {
            logger.trace("Javassist not found exception: {}", nfe.getMessage());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        return classfileBuffer;
    }

    private void checkForInstrumentation(CtClass ctClass) {
        for (CallbackDescriptor cd : config) {
            List<MethodIdentifier> methods = cd.getMethods();
            for (MethodIdentifier methodIdentifier : methods) {
                if (classImplements(ctClass, methodIdentifier.getClassname())) {
                    instrument(ctClass, cd.getCallbackClassName(), methodIdentifier);
                    break;
                }
            }
        }

    }

    private void instrument(CtClass ctClass, String callbackClassName, MethodIdentifier methodIdentifier) {
        instrumentMethod(callbackClassName, ctClass, methodIdentifier);
    }

    private void instrumentBundleImplementor(CtClass ctClass) {
        // instrumentMethod(Const.BUNDLE_CALLBACK, ctClass, new MethodIdentifier(Const.OSGI_BUNDLE_CLASS_NAME, "start",
        // "(I)V"));
        // instrumentMethod(Const.BUNDLE_CALLBACK, Const.OSGI_BUNDLE_CLASS_NAME, ctClass, "start", "()V");

        // instrumentMethod(Const.BUNDLE_CALLBACK, Const.OSGI_BUNDLE_CLASS_NAME, ctClass, "stop", "(I)V");
        instrumentMethod(Const.BUNDLE_CALLBACK, Const.OSGI_BUNDLE_CLASS_NAME, ctClass, "stop", "()V");

        // missing: update(InputStream)

        instrumentMethod(Const.BUNDLE_CALLBACK, Const.OSGI_BUNDLE_CLASS_NAME, ctClass, "update", "()V");

        instrumentMethod(Const.BUNDLE_CALLBACK, Const.OSGI_BUNDLE_CLASS_NAME, ctClass, "uninstall", "()V");

        instrumentMethod(Const.BUNDLE_CALLBACK, Const.OSGI_BUNDLE_CLASS_NAME, ctClass, "getState", "()I");
    }

    private void instrumentBundleContextImplementor(CtClass ctClass, String className) {

        instrumentMethod(Const.BUNDLE_CONTEXT_CALLBACK, className, ctClass, "registerService",
                Const.REGISTER_SERVICE_SIGNATURE_1);
        instrumentMethod(Const.BUNDLE_CONTEXT_CALLBACK, className, ctClass, "registerService",
                Const.REGISTER_SERVICE_SIGNATURE_2);
        instrumentMethod(Const.BUNDLE_CONTEXT_CALLBACK, className, ctClass, "registerService",
                Const.REGISTER_SERVICE_SIGNATURE_3);
        instrumentMethod(Const.BUNDLE_CONTEXT_CALLBACK, className, ctClass, "ungetService",
                "(Lorg/osgi/framework/ServiceReference;)Z");
    }

    private void instrumentServiceRegistrationImplementor(CtClass ctClass) {
        instrumentMethod(ServiceRegistrationCallback.class.getName(), Const.OSGI_SERVICE_REG_CLASS_NAME, ctClass,
                "unregister", "()V");
    }

    private void instrumentMethod(String callbackClassName, String classIdentifier, CtClass ctClass, String methodName,
            String desc) {
        try {
            CtMethod m = ctClass.getMethod(methodName, desc);
            CallbackDefinition callbackDefinition = callbacks.get(callbackClassName);
            callbackDefinition.instrument(classIdentifier, m, classPool);
        } catch (Exception e) {
            handleException(ctClass, e);
        }
    }

    private void instrumentMethod(String callbackClassName, CtClass ctClass, MethodIdentifier methodIdentifier) {
        try {
            CtMethod m = ctClass.getMethod(methodIdentifier.getMethodName(), methodIdentifier.getSignature());
            CallbackDefinition callbackDefinition = callbacks.get(callbackClassName);
            callbackDefinition.instrument(methodIdentifier, m, classPool);
        } catch (Exception e) {
            handleException(ctClass, e);
        }

    }

    /**
     * http://stackoverflow.com/questions/20316965/get-a-name-of-a-method-parameter-using-javassist
     * 
     */

    private boolean ignore(String className) {
        if (className.startsWith("java/") || className.startsWith("javax/") || className.startsWith("sun/")) {
            return true;
        }
        if (className.startsWith("org/junit") || className.startsWith("junit/framework")) {
            return true;
        }
        if (className.startsWith("org/apache/maven/surefire")) {
            return true;
        }
        if (className.startsWith("org/springframework")) {
            return true;
        }
        return false;
    }

    private boolean classImplements(CtClass ctClass, String interfaceName) {
        List<CtClass> interfaces;
        try {
            interfaces = Arrays.asList(ctClass.getInterfaces());
            if (interfaces == null || interfaces.size() == 0) {
                return false;
            }
            for (CtClass interfaceIdent : interfaces) {
                if (interfaceIdent.getName().equals(interfaceName)) {
                    if (ctClass.isInterface()) {
                        System.out.println("ignoring '" + interfaceName + "' interface: " + ctClass.getName());
                        System.out
                                .println("maybe this class should be added to deal with the current framework correctly.");
                        return false;
                    }
                    System.out.println("found for " + interfaceName + " class: " + ctClass.getName());
                    return true;
                }
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void handleException(CtClass ctClass, Exception e) {
        e.printStackTrace();
        List<CtMethod> methods = Arrays.asList(ctClass.getMethods());
        for (CtMethod ctMethod : methods) {
            System.out.println(ctMethod.getName() + ": " + ctMethod.getMethodInfo2().getDescriptor());
        }
    }

}
