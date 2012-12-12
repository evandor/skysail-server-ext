package de.twenty11.skysail.server.ext.osgimonitor.internal;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import de.twenty11.skysail.server.ext.osgimonitor.BundlesResource;
import de.twenty11.skysail.server.ext.osgimonitor.OsgiMonitorRootResource;
import de.twenty11.skysail.server.services.UrlMapper;

public class OsgiMonitorUrlMapper implements UrlMapper {

    //public static final String CONTEXT_ID = "dbviewer";

    public static final String APP_PREFIX = "/" + OsgiMonitorApplicationDescriptor.APPLICATION_NAME;

    @Override
    public Map<String, String> provideUrlMapping() {
        Map<String, String> queue = Collections.synchronizedMap(new LinkedHashMap<String, String>());
        // @formatter:off
        queue.put(APP_PREFIX, OsgiMonitorRootResource.class.getName());
        queue.put(APP_PREFIX + "/", OsgiMonitorRootResource.class.getName());

        queue.put(APP_PREFIX + "/bundles", BundlesResource.class.getName());
//
//        String connection = CONNECTION_PREFIX + "{" + CONNECTION_NAME + "}";
//        queue.put(connection, ConnectionResource.class.getName());
//
//        String schemas = connection + "/schemas";
//        queue.put(schemas, SchemasResource.class.getName());
//
//        String schema = schemas + "/{schema}";
//
//        queue.put(schema + "/tables", TablesResource.class.getName());
//        queue.put(schema + "/tables/{" + TABLE_NAME + "}/columns",ColumnsResource.class.getName());
//        queue.put(schema + "/tables/{" + TABLE_NAME + "}/constraints", ConstraintsResource.class.getName());
//        queue.put(schema + "/tables/{" + TABLE_NAME + "}/data", DataResource.class.getName());
        
        // @formatter:on
        return queue;
    }

}
