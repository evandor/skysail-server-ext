package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.ext.dbviewer.ConstraintDetails;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerApplication;

public class ConstraintsResourceTest extends BaseTests {
    
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
    public void can_read_columns_from_table() throws Exception {
        List<ConstraintDetails> columns = getConstraints("testdb", "skysail", "SKYSAILUSERS");
        assertThat(columns.size(), is(equalTo(1)));
    }
}
