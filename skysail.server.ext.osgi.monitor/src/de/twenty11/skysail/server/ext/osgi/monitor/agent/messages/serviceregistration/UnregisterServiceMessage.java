package de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.serviceregistration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceRegistration;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.JsonMessage;

public class UnregisterServiceMessage extends JsonMessage {

    private String symbolicName;
    private List<String> usingBundles = new ArrayList<String>();
    private Map<String, String> properties = new HashMap<String, String>();

    public UnregisterServiceMessage(ServiceRegistration sr) {
        super("sr", "unregister");
        symbolicName = sr.getReference().getBundle().getSymbolicName();
        Bundle[] bundles = sr.getReference().getUsingBundles();
        if (bundles != null) {
            for (Bundle bundle : bundles) {
                usingBundles.add(bundle.getSymbolicName());
            }
        }
        String[] propertyKeys = sr.getReference().getPropertyKeys();
        if (propertyKeys != null) {
            for (String key : propertyKeys) {
                if ("objectClass".equals(key)) {
                    Object property = sr.getReference().getProperty(key);
                    if (property instanceof String[]) {
                        String[] prop = (String[]) property;
                        properties.put(key, Arrays.asList(prop).toString());
                    } else {
                        properties.put(key, property.toString());
                    }
                } else {
                    properties.put(key, sr.getReference().getProperty(key).toString());
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
