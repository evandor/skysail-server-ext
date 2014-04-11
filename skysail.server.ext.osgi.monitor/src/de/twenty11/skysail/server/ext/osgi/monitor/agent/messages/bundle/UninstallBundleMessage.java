package de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.bundle;

import org.osgi.framework.Bundle;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.JsonMessage;

public class UninstallBundleMessage extends JsonMessage {

    private long bundleId;
    private String symbolicName;
    private int state;

    public UninstallBundleMessage(Bundle bundle) {
        super("bl", "uninstall");
        bundleId = bundle.getBundleId();
        symbolicName = bundle.getSymbolicName();
        state = bundle.getState();
    }

    public long getBundleId() {
        return bundleId;
    }

    public String getSymbolicName() {
        return symbolicName;
    }

    public String getState() {
        switch (state) {
        case Bundle.ACTIVE:
            return "active";
        case Bundle.INSTALLED:
            return "installed";
        case Bundle.RESOLVED:
            return "resolved";
        case Bundle.STARTING:
            return "starting";
        case Bundle.STOPPING:
            return "stopping";
        case Bundle.UNINSTALLED:
            return "uninstalled";
        default:
            return "unknown";
        }
    }

}
