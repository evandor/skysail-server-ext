package de.twenty11.skysail.server.ext.osgi.monitor.agent.messages;

import java.util.Arrays;
import java.util.Map;

import org.osgi.framework.ServiceReference;

public class JsonMessage {

    private String type = "?";
    private String method = "?";

    public JsonMessage(String type, String method) {
        this.type = type;
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public String getMethod() {
        return method;
    }

    protected void mapProperty(Map<String, String> properties, ServiceReference sr, String key) {
        Object value = sr.getProperty(key);
        if (value instanceof String[]) {
            properties.put(key, Arrays.asList((String[]) value).toString());
        } else {
            properties.put(key, "unhandled type: " + value.getClass().getName());
        }
    }
}
