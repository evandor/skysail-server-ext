package de.twenty11.skysail.server.ext.dbviewer.internal;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import de.twenty11.skysail.server.ext.dbviewer.ColumnsResource;
import de.twenty11.skysail.server.ext.dbviewer.ConnectionResource;
import de.twenty11.skysail.server.ext.dbviewer.ConnectionsResource;
import de.twenty11.skysail.server.ext.dbviewer.ConstraintsResource;
import de.twenty11.skysail.server.ext.dbviewer.DataResource;
import de.twenty11.skysail.server.ext.dbviewer.RootResource;
import de.twenty11.skysail.server.ext.dbviewer.SchemasResource;
import de.twenty11.skysail.server.ext.dbviewer.TablesResource;
import de.twenty11.skysail.server.services.UrlMapper;

public class DbViewerUrlMapper implements UrlMapper {

    //public static final String CONTEXT_ID = "dbviewer";

    public static final String CONNECTION_PREFIX = "/" + DbViewerApplicationDescriptor.APPLICATION_NAME + "/connections/";

    public static final String CONNECTION_NAME = "connectionName";

    public static final String SCHEMA_NAME = "schema";

    public static final String TABLE_NAME = "tableName";

    @Override
    public Map<String, String> provideUrlMapping() {
        Map<String, String> queue = Collections.synchronizedMap(new LinkedHashMap<String, String>());
        // @formatter:off
        queue.put("/", RootResource.class.getName());
        queue.put("/dbviewer", RootResource.class.getName());
        queue.put("/dbviewer/", RootResource.class.getName());

        queue.put(CONNECTION_PREFIX, ConnectionsResource.class.getName());

        String connection = CONNECTION_PREFIX + "{" + CONNECTION_NAME + "}";
        queue.put(connection, ConnectionResource.class.getName());

        String schemas = connection + "/schemas";
        queue.put(schemas, SchemasResource.class.getName());

        String schema = schemas + "/{schema}";

        queue.put(schema + "/tables", TablesResource.class.getName());
        queue.put(schema + "/tables/{" + TABLE_NAME + "}/columns",ColumnsResource.class.getName());
        queue.put(schema + "/tables/{" + TABLE_NAME + "}/constraints", ConstraintsResource.class.getName());
        queue.put(schema + "/tables/{" + TABLE_NAME + "}/data", DataResource.class.getName());
        
        // @formatter:on
        return queue;
    }

}
