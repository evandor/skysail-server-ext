package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.twenty11.skysail.server.ext.dbviewer.internal.Connections;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.ConnectionDetails;

public class ConnectionTests {

    private Connections connections;
    private ConnectionDetails connectionDetails;

    @Before
    public void setup() {
        connections = new Connections();
        connectionDetails = new ConnectionDetails("user", "pass", "url", "driverName");
    }

    @Test
    public void testSuccessfullAdd() {
        connections.add("test", connectionDetails);
        DataSource ds = connections.getDataSource("test");
        assertTrue(ds != null);
    }

    @Test
    public void testUndefinedIdentifier() {
        connections.add("test", connectionDetails);
        DataSource ds = connections.getDataSource("nottest");
        assertTrue(ds == null);
    }

    @Test
    @Ignore
    public void testNullIdentifier() {
        connections.add(null, connectionDetails);
        DataSource ds = connections.getDataSource(null);
        assertTrue(ds == null);
    }

    @Test
    public void testList() throws Exception {
        connections.add("test", connectionDetails);
        Set<String> list = connections.list();
        assertTrue(list.size() == 1);
    }

    @Test
    public void testGet() throws Exception {
        connections.add("test", connectionDetails);
        ConnectionDetails details = connections.get("test");
        assertTrue(details.equals(connectionDetails));
    }

    @Test
    public void testDelete() throws Exception {
        connections.add("test", connectionDetails);
        connections.delete("test");
    }

    @Test
    public void updateConnectionDetails() throws Exception {
        connections.add("test", connectionDetails);
        ConnectionDetails newConnectioDetails = new ConnectionDetails("u", "p", "u", "d");
        connections.update("test", newConnectioDetails);
        assertTrue(connections.get("test").getUsername().equals("u"));
    }

    @Test
    public void updateRename() throws Exception {
        connections.add("test", connectionDetails);
        connections.rename("test", "newtest");
        assertTrue(connections.get("newtest") != null);
    }

}
