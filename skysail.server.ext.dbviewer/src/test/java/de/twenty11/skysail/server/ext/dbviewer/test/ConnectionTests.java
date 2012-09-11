package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.junit.Assert.assertTrue;

import javax.sql.DataSource;

import org.junit.Before;
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
    public void testNullIdentifier() {
        connections.add(null, connectionDetails);
        DataSource ds = connections.getDataSource("nottest");
        assertTrue(ds == null);
    }
}
