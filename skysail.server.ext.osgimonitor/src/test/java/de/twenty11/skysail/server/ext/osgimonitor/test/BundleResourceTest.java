package de.twenty11.skysail.server.ext.osgimonitor.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;

import de.twenty11.skysail.common.ext.osgimonitor.BundleDetails;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.server.ext.osgimonitor.BundleResource;
import de.twenty11.skysail.server.ext.osgimonitor.internal.OsgiMonitorViewerApplication;

public class BundleResourceTest extends BaseTests {
    
    private BundleResource bundleResource;

	@Before
    public void setUp() throws Exception {
        ComponentContext componentContextMock = mock(ComponentContext.class);
        OsgiMonitorViewerApplication spy = setUpRestletApplication(componentContextMock);
        BundleContext context = mock(BundleContext.class);
        Bundle bundle = mock(Bundle.class);
        when(bundle.getBundleId()).thenReturn(99l);
        when(bundle.getSymbolicName()).thenReturn("symbolic");
        when(bundle.getLastModified()).thenReturn(111l);
		when(context.getBundle(99l)).thenReturn(bundle);
		when(spy.getBundleContext()).thenReturn(context);
        bundleResource = new BundleResource();
    }
    
    @Test
    @Ignore
    public void gives_vlidation_error_for_missing_name() throws Exception {
    	Response<BundleDetails> bundle = bundleResource.getBundle();
    	BundleDetails details = bundle.getData();
    	assertThat(details.getBundleId(), is(equalTo(99l)));
       
    }

}
