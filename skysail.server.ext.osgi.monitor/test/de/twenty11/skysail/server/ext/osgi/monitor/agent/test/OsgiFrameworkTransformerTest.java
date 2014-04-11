package de.twenty11.skysail.server.ext.osgi.monitor.agent.test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.lang.instrument.IllegalClassFormatException;

import javassist.ClassPool;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.osgi.framework.Bundle;
import org.slf4j.Logger;

import com.google.common.io.ByteStreams;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.OsgiFrameworkTransformer;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.CallbackDefinition;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.Callbacks;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.bundle.BundleCallback;

public class OsgiFrameworkTransformerTest {

    private OsgiFrameworkTransformer transformer;
    private ClassLoader loader;
    private ClassPool pool = ClassPool.getDefault();
    private Logger logger = Mockito.mock(Logger.class);
    private Logger agentLogger;

    @Before
    public void setUp() throws Exception {
        loader = Mockito.mock(ClassLoader.class);
        agentLogger = Mockito.mock(Logger.class);
        transformer = new OsgiFrameworkTransformer(pool, defineCallbacks(), logger);
        pool.appendClassPath(getPathForClass(OsgiFrameworkTransformerTest.class));
        pool.appendClassPath(getPathForClass(BundleCallback.class));
    }

    private Callbacks defineCallbacks() {
        Callbacks callbacks = new Callbacks();
        callbacks.add(new CallbackDefinition(new BundleCallback(), agentLogger));
        return callbacks;
    }

    @Test
    public void keeps_standard_classes_identical() throws IllegalClassFormatException {

        byte[] transformed = transformer.transform(loader, String.class.getCanonicalName(), String.class, null,
                new byte[] { 'a', 'b', 'c' });

        assertThat(new Byte(transformed[0]).intValue(), is(97)); // = 'a'
        assertThat(new Byte(transformed[1]).intValue(), is(98));
        assertThat(new Byte(transformed[2]).intValue(), is(99));
    }

    @Test
    public void test2() throws Exception {
        byte[] byteArray = ByteStreams.toByteArray(new FileInputStream(new File(getPathForClass(MyBundleImpl.class))));

        byte[] transformed = transformer.transform(loader, MyBundleImpl.class.getName(), MyBundleImpl.class, null,
                byteArray);

        Bundle transformedClassInstance = (Bundle) createClass(transformed, MyBundleImpl.class.getName());
        transformedClassInstance.start();
    }

    @Test
    public void calls_callback_code_when_bundle_is_started() throws Exception {

        byte[] byteArray = ByteStreams.toByteArray(new FileInputStream(new File(
                getPathForClass(MyBundleImpl.class))));

        byte[] transformed = transformer.transform(loader, MyBundleImpl.class.getName(),
                MyBundleImpl.class, null, byteArray);

        Bundle transformedClassInstance = (Bundle) createClass(transformed,
                MyBundleImpl.class.getName());
        try {
            transformedClassInstance.start();
        } catch (Exception e) {
            // ok,ok
        }
        Mockito.verify(agentLogger, Mockito.times(1)).info(Mockito.anyString());
    }

    @Test
    public void calls_exception_code_when_exception_is_thrown_in_bundleStartMethod() throws Exception {
        pool.appendClassPath(getPathForClass(OsgiFrameworkTransformerTest.class));
        pool.appendClassPath(getPathForClass(BundleCallback.class));

        byte[] byteArray = ByteStreams.toByteArray(new FileInputStream(new File(
                getPathForClass(MyBundleWithStartExceptionImpl.class))));

        byte[] transformed = transformer.transform(loader, MyBundleWithStartExceptionImpl.class.getName(),
                MyBundleWithStartExceptionImpl.class, null, byteArray);

        Bundle transformedClassInstance = (Bundle) createClass(transformed,
                MyBundleWithStartExceptionImpl.class.getName());
        try {
            transformedClassInstance.start();
        } catch (Exception e) {
            // ok,ok
        }
        Mockito.verify(agentLogger, Mockito.times(1)).error(Mockito.anyString());// Mockito.anyVararg());

    }
    private Object createClass(byte[] transformed, String myBundleClassName) throws Exception {
        FromMemoryClassLoader mcl = new FromMemoryClassLoader(transformed);
        Class<?> transformedClass = mcl.findClass(myBundleClassName);
        return transformedClass.newInstance();
    }

    private String getPathForClass(Class<?> cls) {
        return cls.getResource(cls.getSimpleName() + ".class").getPath();
    }

}
