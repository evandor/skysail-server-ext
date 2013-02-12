package de.twenty11.skysail.server.ext.osgimonitor.internal;

import java.util.ArrayList;
import java.util.List;

import org.restlet.Application;

import de.twenty11.skysail.server.services.ApplicationProvider;

public class Applications {

    private List<Application> applications = new ArrayList<Application>();

    public void setApplicationProvider(ApplicationProvider provider) {
        this.applications.add(provider.getApplication());
    }

    public void unsetApplicationProvider(ApplicationProvider provider) {
        this.applications.remove(provider.getApplication());
    }

}
