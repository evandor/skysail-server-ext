package de.twenty11.skysail.server.ext.osgi.monitor.agent.descriptors;

public class Param {

    private String value;
    private boolean escape;

    private Param(String value, boolean escape) {
        this.value = value;
        this.escape = escape;
    }

    public static Param fromString(String value) {
        return new Param(value, true);
    }

    public static Param fromObject(String name) {
        return new Param(name, false);
    }

    public String toString() {
        if (!escape) {
            return value;
        }
        return "\"" + value + "\"";
    }
}
