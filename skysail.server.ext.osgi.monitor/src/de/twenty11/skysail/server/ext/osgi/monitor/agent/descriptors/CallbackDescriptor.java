package de.twenty11.skysail.server.ext.osgi.monitor.agent.descriptors;

import java.util.ArrayList;
import java.util.List;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.MethodIdentifier;

public class CallbackDescriptor {

    private String callbackClassName;
    private List<MethodIdentifier> methods = new ArrayList<MethodIdentifier>();

    public CallbackDescriptor() {
        // needed for jackson
    }

    public CallbackDescriptor(String callbackClassName) {
        this.callbackClassName = callbackClassName;
    }

    public String getCallbackClassName() {
        return callbackClassName;
    }

    public void addMethodIdentifier(MethodIdentifier methodIdentifier) {
        this.methods.add(methodIdentifier);
    }

    public List<MethodIdentifier> getMethods() {
        return methods;
    }

    @Override
    public String toString() {
        return "CallbackDescriptor for " + methods;
    }

}
