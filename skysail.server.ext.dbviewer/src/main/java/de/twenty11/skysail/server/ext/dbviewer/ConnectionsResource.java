package de.twenty11.skysail.server.ext.dbviewer;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.grids.ColumnsBuilder;
import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.grids.RowData;
import de.twenty11.skysail.server.GridDataServerResource;

public class ConnectionsResource extends GridDataServerResource {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public static Map<String, BasicDataSource> datasources = new HashMap<String, BasicDataSource>();

    static {
        BasicDataSource defaultDS = new BasicDataSource();
        defaultDS.setDriverClassName("com.mysql.jdbc.Driver");
        defaultDS.setUsername("root");
        defaultDS.setPassword("");
        defaultDS.setUrl("jdbc:mysql://localhost/skysailosgi");
        datasources.put("default", defaultDS);
    }

    public ConnectionsResource() {
        setTemplate("skysail.server.ext.dbviewer:connections.ftl");
    }

    @Override
    public void configureColumns(ColumnsBuilder builder) {
        builder.addColumn("connectionName");
        builder.addColumn("url");
        builder.addColumn("user");
        builder.addColumn("driver");
    }

    @Override
    public void buildGrid() {
        GridData grid = getSkysailData();
        for (String dsName : datasources.keySet()) {
            BasicDataSource ds = datasources.get(dsName);
            RowData row = new RowData(getSkysailData().getColumns());
            row.add(dsName);
            row.add(ds.getUrl());
            row.add(ds.getUsername());
            row.add(ds.getDriverClassName());
            grid.addRowData(getSkysailData().getFilter(), row);
        }
    }
}
