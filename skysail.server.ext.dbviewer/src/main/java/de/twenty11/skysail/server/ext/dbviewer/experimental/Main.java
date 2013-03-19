package de.twenty11.skysail.server.ext.dbviewer.experimental;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.sf.cglib.asm.Type;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class Main {

    public class Employee {

    }

    public static <T> T createProxy(T obj) {
        // this is the main cglib api entry-point
        // this object will 'enhance' (in terms of CGLIB) with new capabilities
        // one can treat this class as a 'Builder' for the dynamic proxy
        Enhancer e = new Enhancer();

        // the class will extend from the real class
        e.setSuperclass(obj.getClass());

        // we have to declare the interceptor - the class whose 'intercept'
        // will be called when any method of the proxified object is called.
        e.setCallback(new MyInterceptor(obj));
        // now the enhancer is configured and we'll create the proxified object
        T proxifiedObj = (T) e.create();
        // the object is ready to be used - return it
        return proxifiedObj;
    }

    public static void main(String[] args) throws Exception {
        // BaseListResource<?> baseCls = new BaseListResource<String>();
        // BaseListResourceEnhancer enhancedListResource = new BaseListResourceEnhancer(baseCls);
        // enhancedListResource.
        Algorithm alg = new Algorithm();
        BaseListResource blr = new BaseListResource();
        // 2. create the proxy
        Algorithm proxifiedAlgorithm = createProxy(alg);
        BaseListResource proxyListResource = createProxy(blr);

        // 3. execute the proxy - as we see it has the same API as the real object
        proxifiedAlgorithm.runAlgorithm();
        proxyListResource.addConnection(null);

        // Create a dynamice interface
        InterfaceMaker im = new InterfaceMaker();

        // Define a setter method for firstName, i.e., setFirstName.
        Type[] parameters = new Type[] { Type.getType(String.class) };
        // Signature signature2 = new Signature("name", "desc");
        Signature signature = new Signature("setFirstName", Type.VOID_TYPE, parameters);
        im.add(signature, new Type[0]);

        // Finish creating the interface
        Class myInterface = im.create();

        // Create a dynamic class that subclasses Employee
        // and add a method interceptor to handle setFirstName
        Enhancer e = new Enhancer();
        e.setSuperclass(Employee.class);
        e.setInterfaces(new Class[] { myInterface });
        e.setCallback(new MethodInterceptor() {
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                if (method.getName().equals("setFirstName")) {
                    Employee employee = (Employee) obj;
                    Field[] fields = Employee.class.getDeclaredFields();
                    Field field = null;
                    for (int index = 0; index < fields.length; index++) {
                        Field currentField = fields[index];
                        System.out.println("Field Name " + currentField.getName());
                        if (currentField.getName().equals("firstName")) {
                            currentField.setAccessible(true);
                            field = currentField;
                            break;
                        }
                    }
                    field.set(employee, args[0]);
                    return null;
                } else {
                    return proxy.invokeSuper(obj, args);
                }
            }
        });

        // Use our new dynamic class
        Employee employee = (Employee) e.create();

        // Make sure we can invoke the setFirstName method.
        Method method = employee.getClass().getMethod("setFirstName", new Class[] { String.class });
        method.invoke(employee, new Object[] { "Rick" });

        // Test that we actually invoked a setFirstName method.
        assertEquals("Employee = Rick", employee.toString());

    }

}
