package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.forms.ConstraintViolations;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerApplication;

public class ConnectionsResourceTest extends BaseTests {
    
    @Before
    public void setUp() throws Exception {
        DbViewerApplication spy = setUpRestletApplication();
        setUpPersistence(spy);
    }
    
    @Test
    public void gives_validation_error_for_missing_name() throws Exception {
        ConnectionDetails connection = new ConnectionDetails(null, "username", "password", "url", "driverClassName");
        ConstraintViolations<ConnectionDetails> createViolations = create(connection);
        assertThat(createViolations.size(), is(equalTo(1)));
        assertThat(createViolations.getViolations().get(0).getMessage(), is(equalTo("Name is mandatory")));
       
    }

    @Test
    public void can_create_and_read_connections() throws Exception {
        ConnectionDetails connection = new ConnectionDetails("name", "username", "password", "url", "driverClassName");
        create(connection);
        List<ConnectionDetails> connections = getConnections();
        assertThat(connections.size(), is(greaterThan(0)));
    }
    
    

}
