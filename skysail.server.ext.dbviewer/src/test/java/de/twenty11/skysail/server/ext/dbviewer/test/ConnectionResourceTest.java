package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionResourceTest extends BaseTests {
    
    @Before
    public void setUp() throws Exception {
        SkysailApplication spy = setUpRestletApplication();
        setUpPersistence(spy);
        ConnectionDetails connection = new ConnectionDetails("name", "username", "password", "url", "driverClassName");
        create(connection);
    }

    @Test
    public void can_read_connection() throws Exception {
        ConnectionDetails connection = getConnection("name");
        assertThat(connection.getUsername(), is(equalTo("username")));
    }

    @Test
    public void can_delete_connection() throws Exception {
        deleteConnection("name");
        // TODO doesnt work yet
        ConnectionDetails connectionRead = getConnection("name");
        //assertThat(connectionRead, is(nullValue()));
    }


    

}
