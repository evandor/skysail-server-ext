package de.twenty11.skysail.server.ext.osgi.monitor.agent.test;

public class FromMemoryClassLoader extends ClassLoader {

    private byte[] code;

    public FromMemoryClassLoader(byte[] code) {
        this.code = code;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return defineClass(name, code, 0, code.length);
    }

}
