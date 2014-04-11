package de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.bundle;

import org.osgi.framework.Bundle;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.JsonMessage;

public class AdaptBundleMessage extends JsonMessage {

    private long bundleId;
    private String symbolicName;

    public AdaptBundleMessage(Bundle bundle, Class<?> cls) {
        super("bl", "adapt");
        bundleId = bundle.getBundleId();
        // symbolicName = bundle.getSymbolicName();
    }

    public long getBundleId() {
        return bundleId;
    }

    public String getSymbolicName() {
        return symbolicName;
    }

}
