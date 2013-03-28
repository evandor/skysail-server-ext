package de.twenty11.skysail.server.ext.dbviewer.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerApplication;

public class DataResourceTest extends BaseTests {

	@Before
    public void setUp() throws Exception {
        DbViewerApplication spy = setUpRestletApplication();
        setUpPersistence(spy);
        ConnectionDetails connection = new ConnectionDetails("testdb", "skysail", "skysail",
                "jdbc:derby:skysailDerbyTestDb;create=true", "org.apache.derby.jdbc.EmbeddedDriver");
        // create(connection);
    }

    @Test
    @Ignore
    public void can_read_data_from_table() {
    	try {
			getData("testdb", "skysail", "SKYSAILUSERS");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
    	//assertThat(gridData.getRows().size(), is(equalTo(0)));
    }

}
