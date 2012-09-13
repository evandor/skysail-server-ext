package de.twenty11.skysail.server.ext.dbviewer;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.grids.ColumnsBuilder;
import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.grids.RowData;
import de.twenty11.skysail.server.ext.dbviewer.internal.Connections;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailDataSource;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.ConnectionDetails;
import de.twenty11.skysail.server.restlet.GridDataServerResource;

public class ConnectionsResource extends GridDataServerResource {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public static Map<String, DataSource> datasources = new HashMap<String, DataSource>();

    public ConnectionsResource() {
        super(new ColumnsBuilder() {

            @Override
            public void configure() {
                addColumn("connectionName");
                addColumn("url");
                addColumn("user");
                addColumn("driver");
                addColumn("drillDown10");
            }
        });
        setTemplate("skysail.server.ext.dbviewer:connections.ftl");

        if (!datasources.containsKey("default")) {
            DataSource defaultDS = SkysailDataSource.get();
            datasources.put("default", defaultDS);
        }
    }

    @Override
    public void buildGrid() {
        setMessage("all Connections");

        SkysailApplication application = (SkysailApplication) getApplication();
        Connections connections = application.getConnections();
        GridData grid = getSkysailData();
        for (String connectionName : connections.list()) {
            ConnectionDetails details = connections.get(connectionName);
            RowData row = new RowData(getSkysailData().getColumns());
            row.add(connectionName);
            row.add(details.getUrl());
            row.add(details.getUsername());
            row.add(details.getDriverName());
            row.add(getParent() + "dbviewer/" + connectionName + "/?media=json");
            grid.addRowData(row);

        }
    }

    @Post()
    public void add(JsonRepresentation entity) {
        try {
            JSONObject jsonObject = entity.getJsonObject();
            String name = jsonObject.getString("connectionName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
