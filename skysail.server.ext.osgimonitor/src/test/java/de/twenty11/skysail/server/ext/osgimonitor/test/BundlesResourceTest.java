package de.twenty11.skysail.server.ext.osgimonitor.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.twenty11.skysail.common.ext.osgimonitor.BundleDetails;
import de.twenty11.skysail.common.forms.ConstraintViolations;
import de.twenty11.skysail.server.ext.osgimonitor.internal.OsgiMonitorViewerApplication;

public class BundlesResourceTest extends BaseTests {
    
    @Before
    public void setUp() throws Exception {
        OsgiMonitorViewerApplication spy = setUpRestletApplication();
        //setUpPersistence(spy);
    }
    
    @Test
    public void gives_validation_error_for_missing_name() throws Exception {
        List<BundleDetails> bundles = getBundles();
        assertThat(bundles.size(), is(equalTo(0)));
        //assertThat(createViolations.getViolations().get(0).getMessage(), is(equalTo("Name is mandatory")));
       
    }

    

}
