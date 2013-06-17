package de.twenty11.skysail.server.ext.osgimonitor.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;
import org.restlet.data.Reference;

public class ServiceDescriptor implements Serializable, Comparable<ServiceDescriptor> {

    private static final long serialVersionUID = -557374476304687619L;

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

    // @JsonIgnore
    // @Override
    // public PresentableHeader getHeader() {
    // return new PresentableHeader.Builder("#" + serviceId + ": " + this.stringRep).build();
    // }
    //
    // @JsonIgnore
    // @Override
    // public Map<String, Object> getContent() {
    // return new BeanMap(this);
    // // Map<String, Object> result = new HashMap<String, Object>();
    // // result.put("provided by", providingBundle);
    // // result.put("used by", usingBundles);
    // // for (String property : this.properties.keySet()) {
    // // result.put(property, properties.get(property));
    // // }
    // // return result;
    // }

    // @JsonIgnore
    // public String getHtml() {
    // ST html = new ST("#map.keys:{k| <tr><td style='width:300px;'><b>#k#</b></td><td>#map.(k)#</td></tr>}#\n", '#',
    // '#');
    // properties.put("provided by", providingBundle);
    // properties.put("used by", usingBundles);
    // html.add("map", properties);
    //
    // return "<table class='table table-hover table-bordered'>\n<tr><th colspan=2 style='background-color:#F5F5F5;'>"
    // + serviceId + " " + this.stringRep + "</th></tr>\n" + html.render() + "</table>\n";
    // }

    @Override
    public int compareTo(ServiceDescriptor other) {
        return serviceId.compareTo(other.getServiceId());
    }

    public Long getServiceId() {
        return serviceId;
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
