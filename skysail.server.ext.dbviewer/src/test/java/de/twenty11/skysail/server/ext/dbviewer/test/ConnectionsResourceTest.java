package de.twenty11.skysail.server.ext.dbviewer.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionsResourceTest extends BaseTests {
    
    @Before
    public void setUp() throws Exception {
        SkysailApplication spy = setUpRestletApplication();
        setUpPersistence(spy);
    }
    
    @Test
    public void gives_validation_error_for_missing_name() throws Exception {
        ConnectionDetails connection = new ConnectionDetails(null, "username", "password", "url", "driverClassName");
        create(connection);
       
    }

    @Test
    public void can_create_and_read_connections() throws Exception {
        ConnectionDetails connection = new ConnectionDetails("name", "username", "password", "url", "driverClassName");
        create(connection);
        List<ConnectionDetails> connections = getConnections();
        assertThat(connections.size(), is(greaterThan(0)));
    }
    
    

}
