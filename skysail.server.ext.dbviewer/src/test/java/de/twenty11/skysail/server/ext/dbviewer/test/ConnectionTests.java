package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.sql.DataSource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.twenty11.skysail.server.ext.dbviewer.internal.Connections;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.ConnectionDetails;

public class ConnectionTests {

    private Connections connections;
    private ConnectionDetails connectionDetails;
    private Validator validator;

    @Before
    public void setup() {
        connections = new Connections();
        connectionDetails = new ConnectionDetails("id", "user", "pass", "url", "driverName");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testSuccessfullAdd() {
        connections.add(connectionDetails);
        DataSource ds = connections.getDataSource("id");
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
        ConnectionDetails details = connections.get("id");
        assertTrue(details.equals(connectionDetails));
    }

    @Test
    public void testDelete() throws Exception {
        connections.add(connectionDetails);
        connections.delete("id");
    }

    @Test
    @Ignore
    public void updateConnectionDetails() throws Exception {
        connections.add(connectionDetails);
        ConnectionDetails newConnectioDetails = new ConnectionDetails("i", "u", "p", "u", "d");
        connections.update("test", newConnectioDetails);
        assertTrue(connections.get("id").getUsername().equals("u"));
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
        assertEquals("Id is mandatory", constraintViolations.iterator().next().getMessage());

    }

}
