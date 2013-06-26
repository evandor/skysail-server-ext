package de.twenty11.skysail.server.ext.osgimonitor.domain;

import java.util.Map;

import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleRevision;

public class Capability implements Comparable<Capability> {

    private Map<String, Object> attributes;
    private Map<String, String> directives;
    private String namespace;
    private BundleRevision revision;

    /**
     * Default constructor, needed for // TODO
     */
    public Capability() {
    }

    public Capability(BundleCapability cap) {
        attributes = cap.getAttributes();
        cap.getClass();
        directives = cap.getDirectives();
        namespace = cap.getNamespace();
        revision = cap.getRevision();
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public Map<String, String> getDirectives() {
        return directives;
    }
    
    public String getNamespace() {
        return namespace;
    }
    public BundleRevision getRevision() {
        return revision;
    }
    
    @Override
    public int compareTo(Capability other) {
        return 0;//serviceId.compareTo(other.getServiceId());
    }


}
