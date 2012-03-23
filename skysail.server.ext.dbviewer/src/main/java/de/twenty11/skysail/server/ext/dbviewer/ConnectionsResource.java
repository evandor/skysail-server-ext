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
        defaultDS.setUrl("jdbc:mysql://localhost/skysail");
        datasources.put("default", defaultDS);
    }

    public ConnectionsResource() {
        super(new GridData());
        setTemplate("skysail.server.ext.dbviewer:connections.ftl");
    }

    @Override
    public void buildGrid() {
        GridData grid = getSkysailData();
        for (String dsName : datasources.keySet()) {
            BasicDataSource ds = datasources.get(dsName);
            RowData row = new RowData(getSkysailData().getColumns());
            // @formatter:Off
            row
                .add(dsName)
                .add(ds.getUrl())
                .add(ds.getUsername())
                .add(ds.getDriverClassName());
            // @formatter:On
            grid.addRowData(getSkysailData().getFilter(), row);
        }
    }

    @Override
    public void configureColumns(ColumnsBuilder builder) {
        builder.addColumn("connectionName");
        builder.addColumn("url");
        builder.addColumn("user");
        builder.addColumn("driver");
    }

}
