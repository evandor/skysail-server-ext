package de.twenty11.skysail.server.ext.osgimonitor.internal;

import java.util.HashMap;
import java.util.Map;

import de.twenty11.skysail.server.ext.osgimonitor.BundlesAsGraphResource;
import de.twenty11.skysail.server.ext.osgimonitor.BundlesResource;
import de.twenty11.skysail.server.ext.osgimonitor.OsgiMonitorRootResource;
import de.twenty11.skysail.server.services.UrlMapper;

public class OsgiMonitorUrlMapper implements UrlMapper {

    public static final String APP_PREFIX = "/" + OsgiMonitorApplicationDescriptor.APPLICATION_NAME;

    @Override
    public Map<String, String> provideUrlMapping() {
        Map<String, String> routes = new HashMap<String, String>();
        routes.put(APP_PREFIX + "/", OsgiMonitorRootResource.class.getName());
        routes.put(APP_PREFIX + "/bundles?variant=graph", BundlesAsGraphResource.class.getName());
        routes.put(APP_PREFIX + "/bundles", BundlesResource.class.getName());

        return routes;
    }

}
