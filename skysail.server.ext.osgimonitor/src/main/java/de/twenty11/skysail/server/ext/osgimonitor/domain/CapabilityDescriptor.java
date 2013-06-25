package de.twenty11.skysail.server.ext.osgimonitor.domain;

import java.util.Map;

import org.osgi.framework.wiring.BundleCapability;

public class CapabilityDescriptor implements Comparable<CapabilityDescriptor> {

    private Map<String, Object> attributes;

    /**
     * Default constructor, needed for // TODO
     */
    public CapabilityDescriptor() {
    }

    public CapabilityDescriptor(BundleCapability cap) {
        attributes = cap.getAttributes();
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }
    
    @Override
    public int compareTo(CapabilityDescriptor other) {
        return 0;//serviceId.compareTo(other.getServiceId());
    }


}
