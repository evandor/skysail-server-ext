package de.twenty11.skysail.server.ext.dbviewer.internal;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import de.twenty11.skysail.server.ext.dbviewer.ColumnsResource;
import de.twenty11.skysail.server.ext.dbviewer.ConnectionsResource;
import de.twenty11.skysail.server.ext.dbviewer.DataResource;
import de.twenty11.skysail.server.ext.dbviewer.TablesResource;
import de.twenty11.skysail.server.services.UrlMapper;

public class DbViewerUrlMapper implements UrlMapper {

    public static final String CONTEXT_ID = "dbviewer";

    public static final String CONNECTION_NAME = "connectionName";

    public static final String TABLE_NAME = "tableName";

    @Override
    public Map<String, String> provideUrlMapping() {
        Map<String, String> queue = Collections.synchronizedMap(new LinkedHashMap<String, String>());
        // @formatter:off
        queue.put("/" + CONTEXT_ID + "/", ConnectionsResource.class.getName());
        queue.put("/" + CONTEXT_ID + "/{" + CONNECTION_NAME + "}", ConnectionResource.class.getName());
        queue.put("/" + CONTEXT_ID + "/{" + CONNECTION_NAME + "}/", TablesResource.class.getName());
        queue.put("/" + CONTEXT_ID + "/{" + CONNECTION_NAME + "}/{" + TABLE_NAME + "}/columns/",ColumnsResource.class.getName());
        queue.put("/" + CONTEXT_ID + "/{" + CONNECTION_NAME + "}/{" + TABLE_NAME + "}/data/", DataResource.class.getName());
        // @formatter:on
        return queue;
    }

}
