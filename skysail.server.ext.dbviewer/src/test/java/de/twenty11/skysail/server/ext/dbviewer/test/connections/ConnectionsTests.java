package de.twenty11.skysail.server.ext.dbviewer.test.connections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.sql.DataSource;
import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.server.ext.dbviewer.OSGiServiceDiscoverer;
import de.twenty11.skysail.server.ext.dbviewer.internal.Connections;

public class ConnectionsTests {

    private Connections connections;
    private ConnectionDetails connectionDetails;
    private Validator validator;

    @Before
    public void setup() {
        connections = new Connections();
        connectionDetails = new ConnectionDetails("name", "user", "pass", "url", "driverName");
        Configuration<?> config = Validation.byDefaultProvider().providerResolver(new OSGiServiceDiscoverer())
                .configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testSuccessfullAdd() {
        connections.add(connectionDetails);
        DataSource ds = connections.getDataSource("name");
        assertTrue(ds != null);
    }

    @Test
    public void testUndefinedIdentifier() {
        connections.add(connectionDetails);
        DataSource ds = connections.getDataSource("notid");
        assertTrue(ds == null);
    }

    @Test
    @Ignore
    public void testNullIdentifier() {
        connections.add(connectionDetails);
        DataSource ds = connections.getDataSource(null);
        assertTrue(ds == null);
    }

    @Test
    public void testList() throws Exception {
        connections.add(connectionDetails);
        Set<String> list = connections.list();
        assertTrue(list.size() == 1);
    }

    @Test
    public void testGet() throws Exception {
        connections.add(connectionDetails);
        ConnectionDetails details = connections.get("name");
        assertTrue(details.equals(connectionDetails));
    }

    @Test
    public void testDelete() throws Exception {
        connections.add(connectionDetails);
        connections.delete("name");
    }

    @Test
    @Ignore
    public void updateConnectionDetails() throws Exception {
        connections.add(connectionDetails);
        ConnectionDetails newConnectioDetails = new ConnectionDetails("i", "u", "p", "u", "d");
        connections.update("test", newConnectioDetails);
        assertTrue(connections.get("name").getUsername().equals("u"));
    }

    @Test
    @Ignore
    public void updateRename() throws Exception {
        // connections.add("test", connectionDetails);
        // connections.rename("test", "newtest");
        // assertTrue(connections.get("newtest") != null);
    }

    @Test
    public void testManufacturerIsNull() {
        connectionDetails = new ConnectionDetails(null, "user", "pass", "url", "driverName");
        Set<ConstraintViolation<ConnectionDetails>> constraintViolations = validator.validate(connectionDetails);

        assertEquals(1, constraintViolations.size());
        assertEquals("Name is mandatory", constraintViolations.iterator().next().getMessage());

    }

}
