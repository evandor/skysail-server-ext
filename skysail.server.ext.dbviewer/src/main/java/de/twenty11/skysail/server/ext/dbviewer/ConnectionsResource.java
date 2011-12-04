package de.twenty11.skysail.server.ext.dbviewer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.RowData;
import de.twenty11.skysail.common.messages.GridData;
import de.twenty11.skysail.common.messages.GridInfo;
import de.twenty11.skysail.server.osgi.SkysailUtils;
import de.twenty11.skysail.server.restletosgi.SkysailServerResource;

public class ConnectionsResource extends SkysailServerResource<GridData> {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String[] fields = { "name", "URL","user", "class" };

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
        super("Datasources");
        setTemplate("skysail.server.ext.dbviewer:connections.ftl");
    }

    @Override
    public GridData getData() {
        //return DbViewer.getInstance().getBundles();
        GridInfo fieldsList = SkysailUtils.createFieldList(fields);
        GridData grid = new GridData(fieldsList.getColumns());

        for (String dsName : datasources.keySet()) {
            BasicDataSource ds = datasources.get(dsName);
            RowData rowData = new RowData();
            List<Object> cols = new ArrayList<Object>();
            cols.add(dsName);
            cols.add(ds.getUrl());
            cols.add(ds.getUsername());
            cols.add(ds.getDriverClassName());
            rowData.setColumnData(cols);
            grid.addRowData(rowData );
        }
        return grid;
    }

}
