package de.twenty11.skysail.server.ext.dbviewer.internal;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import de.twenty11.skysail.server.ext.dbviewer.internal.entities.ConnectionDetails;

public class Connections {

    private Map<String, ConnectionDetails> connectionDetails = new HashMap<String, ConnectionDetails>();

    public void add(String identifier, ConnectionDetails details) {
        connectionDetails.put(identifier, details);
    }

    public DataSource getDataSource(String identifier) {
        ConnectionDetails details = connectionDetails.get(identifier);
        if (details != null) {
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl(details.getUrl());
            ds.setUsername(details.getUsername());
            ds.setDriverClassName(details.getDriverName());
            ds.setPassword(details.getPassword());
            return ds;
        }
        return null;
    }
}
