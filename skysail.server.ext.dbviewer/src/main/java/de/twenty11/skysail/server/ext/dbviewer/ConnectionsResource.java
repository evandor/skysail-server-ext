package de.twenty11.skysail.server.ext.dbviewer;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.grids.ColumnsBuilder;
import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.grids.RowData;
import de.twenty11.skysail.server.ext.dbviewer.internal.Configuration;
import de.twenty11.skysail.server.restlet.GridDataServerResource;

public class ConnectionsResource extends GridDataServerResource {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public static Map<String, BasicDataSource> datasources = new HashMap<String, BasicDataSource>();

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

        BasicDataSource defaultDS = new BasicDataSource();
        defaultDS.setDriverClassName(Configuration.getDriverClassName());
        defaultDS.setUsername(Configuration.getUsername());
        defaultDS.setPassword(Configuration.getPasswort());
        defaultDS.setUrl(Configuration.getUrl());

        logger.info("setting up skysail db with connection '{}', user '{}' and class '{}'", new String[] {
                Configuration.getUrl(), Configuration.getUsername(), Configuration.getDriverClassName() });
        datasources.put("default", defaultDS);
    }

    @Override
    public void buildGrid() {
        setMessage("all Connections");
        GridData grid = getSkysailData();
        for (String dsName : datasources.keySet()) {
            BasicDataSource ds = datasources.get(dsName);
            RowData row = new RowData(getSkysailData().getColumns());
            row.add(dsName);
            row.add(ds.getUrl());
            row.add(ds.getUsername());
            row.add(ds.getDriverClassName());
            row.add(getParent() + "dbviewer/" + dsName + "/?media=json");
            grid.addRowData(row);
        }
    }
}
