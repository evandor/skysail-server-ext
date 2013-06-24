package de.twenty11.skysail.server.ext.osgimonitor.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;
import org.restlet.data.Reference;

public class ServiceDescriptor implements Comparable<ServiceDescriptor> {

    private static final String SERVICE_ID_IDENTIFIER = "service.id";

    private Long serviceId = -1L;
    private Map<String, Object> properties = new HashMap<String, Object>();
    private BundleDescriptor providingBundle;

    @Transient
    private Map<String, String> links;
    
    @Transient
    private List<BundleDescriptor> usingBundles = new ArrayList<BundleDescriptor>();

    @Transient
    private Reference reference;

    /**
     * Default constructor, needed for // TODO
     */
    public ServiceDescriptor() {
    }

    public ServiceDescriptor(ServiceReference sr, Reference reference) {
        providingBundle = new BundleDescriptor(sr.getBundle(), reference);
        this.reference = reference;
        handleUsingBundles(sr, reference);
        handlePropertyKeys(sr);
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public BundleDescriptor getProvidingBundle() {
        return providingBundle;
    }

    public void setProvidingBundle(BundleDescriptor providingBundle) {
        this.providingBundle = providingBundle;
    }

    @Override
    public int compareTo(ServiceDescriptor other) {
        return serviceId.compareTo(other.getServiceId());
    }

    public Long getServiceId() {
        return serviceId;
    }
    
    @Override
    public String toString() {
        return serviceId + " [" + ((Object[]) properties.get("objectClass"))[0] + "]";
    }

    private void handleUsingBundles(ServiceReference sr, Reference reference) {
        if (sr.getUsingBundles() != null) {
            for (Bundle usingBundle : sr.getUsingBundles()) {
                usingBundles.add(new BundleDescriptor(usingBundle, reference));
            }
        }
    }

    private void handlePropertyKeys(ServiceReference sr) {
        String[] propertyKeys = sr.getPropertyKeys();
        for (String key : propertyKeys) {
            if (SERVICE_ID_IDENTIFIER.equals(key)) {
                serviceId = (Long) sr.getProperty(SERVICE_ID_IDENTIFIER);
            } else {
                properties.put(key, sr.getProperty(key));
            }
        }
    }

}