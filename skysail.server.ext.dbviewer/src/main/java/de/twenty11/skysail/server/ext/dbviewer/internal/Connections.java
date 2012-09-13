package de.twenty11.skysail.server.ext.dbviewer.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import de.twenty11.skysail.server.ext.dbviewer.internal.entities.ConnectionDetails;

public class Connections {

    private Map<String, ConnectionDetails> connectionDetails = new HashMap<String, ConnectionDetails>();

    public void add(ConnectionDetails details) {
        connectionDetails.put(details.getId(), details);
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

    public Set<String> list() {
        return connectionDetails.keySet();
    }

    public ConnectionDetails get(String connectionName) {
        return connectionDetails.get(connectionName);
    }

    public void delete(String key) {
        connectionDetails.remove(key);

    }

    public void update(String key, ConnectionDetails newConnectioDetails) {
        connectionDetails.put(key, newConnectioDetails);
    }

    public void rename(String oldName, String newName) {
        if (connectionDetails.containsKey(newName)) {
            throw new IllegalArgumentException("name '" + newName + "' already exists");
        }
        connectionDetails.put(newName, connectionDetails.get(oldName));
        connectionDetails.remove(oldName);
    }
}
