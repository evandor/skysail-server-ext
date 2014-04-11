package de.twenty11.skysail.server.ext.osgi.monitor.agent.descriptors;

import org.osgi.framework.Bundle;

public class BundleDescriptor {

    private String bsn;
    private int state;

    public BundleDescriptor(Bundle bundle) {
        this.bsn = bundle.getSymbolicName();
        this.state = bundle.getState();
    }

    public String getBsn() {
        return bsn;
    }

    public int getState() {
        return state;
    }

    public void setState(int result) {
        this.state = result;

    }

}
