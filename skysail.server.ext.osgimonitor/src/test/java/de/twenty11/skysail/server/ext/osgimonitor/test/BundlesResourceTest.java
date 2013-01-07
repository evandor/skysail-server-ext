package de.twenty11.skysail.server.ext.osgimonitor.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.osgi.service.component.ComponentContext;
import org.restlet.representation.Representation;

import de.twenty11.skysail.common.ext.osgimonitor.BundleDetails;
import de.twenty11.skysail.server.ext.osgimonitor.BundlesResource;
import de.twenty11.skysail.server.ext.osgimonitor.internal.OsgiMonitorViewerApplication;

public class BundlesResourceTest extends BaseTests {
    
    private BundlesResource resource;

	@Before
    public void setUp() throws Exception {
        ComponentContext componentContextMock = mock(ComponentContext.class);
        OsgiMonitorViewerApplication spy = setUpRestletApplication(componentContextMock);
        resource = new BundlesResource();
    }
    
    @Test
    public void gives_validation_error_for_missing_name() throws Exception {
        List<BundleDetails> bundles = getBundles();
        assertThat(bundles.size(), is(equalTo(0)));
        //assertThat(createViolations.getViolations().get(0).getMessage(), is(equalTo("Name is mandatory")));
       
    }

    @Test
    @Ignore
    public void gives_error_message_for_post_when_location_doesnt_start_with_prefix() throws Exception {
    	Representation answer = resource.install("wrongLocation");
    	assertThat(answer.getText(), is(equalTo("location didn't start with 'prefix'")));
    }

}
