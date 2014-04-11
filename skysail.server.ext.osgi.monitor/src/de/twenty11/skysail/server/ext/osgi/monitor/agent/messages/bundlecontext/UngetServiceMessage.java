package de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.bundlecontext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.JsonMessage;

public class UngetServiceMessage extends JsonMessage {

    private String symbolicName;
    private List<String> usingBundles = new ArrayList<String>();
    public Map<String, String> properties = new HashMap<String, String>();

    public UngetServiceMessage(ServiceReference sr) {
        super("bc", "ungetService");
        symbolicName = sr.getBundle().getSymbolicName();
        Bundle[] bundles = sr.getUsingBundles();
        if (bundles != null) {
            for (Bundle bundle : bundles) {
                usingBundles.add(bundle.getSymbolicName());
            }
        }
        String[] propertyKeys = sr.getPropertyKeys();
        if (propertyKeys != null) {
            for (String key : propertyKeys) {
                if ("objectClass".equals(key)) {
                    mapProperty(properties, sr, key);
                } else if ("osgi.command.function".equals(key)) {
                    mapProperty(properties, sr, key);
                } else {
                    properties.put(key, sr.getProperty(key).toString());
                }
            }
        }
    }

    public String getSymbolicName() {
        return symbolicName;
    }

    public List<String> getUsingBundles() {
        return usingBundles;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

}
