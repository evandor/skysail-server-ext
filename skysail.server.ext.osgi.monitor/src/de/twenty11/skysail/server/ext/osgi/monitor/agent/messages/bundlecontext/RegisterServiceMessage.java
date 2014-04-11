package de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.bundlecontext;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.JsonMessage;

public class RegisterServiceMessage extends JsonMessage {

    private String[] classes;
    private String obj;
    private String symbolicName;
    private Dictionary<?, ?> dict;

    public RegisterServiceMessage(BundleContext bundleContext, String[] strArr, Object obj, Dictionary<?, ?> dictionary) {
        super("bc", "registerService");
        this.classes = strArr;
        this.obj = obj.getClass().getName();
        this.dict = dictionary;
        this.symbolicName = bundleContext.getBundle().getSymbolicName();
    }

    public String[] getClasses() {
        return classes;
    }

    public String getObj() {
        return obj;
    }

    public String getBSN() {
        return symbolicName;
    }

    public Dictionary<?, ?> getDict() {
        return dict;
    }

}
