package de.twenty11.skysail.server.ext.osgimonitor.domain;

import java.util.Dictionary;

import org.osgi.service.cm.Configuration;

public class ConfigDescriptor {

    private String bundleLocation;
    private String factoryPid;
    private String pid;
    private Dictionary properties;

    public ConfigDescriptor() {
    }

    public ConfigDescriptor(Configuration conf) {
        bundleLocation = conf.getBundleLocation();
        factoryPid = conf.getFactoryPid();
        pid = conf.getPid();
        properties = conf.getProperties();
    }

    public String getBundleLocation() {
        return bundleLocation;
    }

    public String getFactoryPid() {
        return factoryPid;
    }

    public String getPid() {
        return pid;
    }

    public Dictionary getProperties() {
        return properties;
    }

}
