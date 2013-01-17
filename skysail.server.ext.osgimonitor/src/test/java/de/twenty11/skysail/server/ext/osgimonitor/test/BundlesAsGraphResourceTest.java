package de.twenty11.skysail.server.ext.osgimonitor.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.junit.Before;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.restlet.Request;

import de.twenty11.skysail.server.ext.osgimonitor.BundlesAsGraphResource;
import de.twenty11.skysail.server.ext.osgimonitor.internal.OsgiMonitorViewerApplication;

public class BundlesAsGraphResourceTest extends BaseTests {
    
    private BundlesAsGraphResource resource;

	@Before
    public void setUp() throws Exception {
        OsgiMonitorViewerApplication spy = setUpRestletApplication();
        BundleContext context = mock(BundleContext.class);
        Bundle bundle = mock(Bundle.class);
        when(bundle.getBundleId()).thenReturn(99l);
        when(bundle.getSymbolicName()).thenReturn("symbolic");
        when(bundle.getLastModified()).thenReturn(111l);
        Bundle[] bundles = new Bundle[1];
        bundles[0] = bundle;
        when(context.getBundles()).thenReturn(bundles);
        when(spy.getBundleContext()).thenReturn(context);

        resource = new BundlesAsGraphResource();
        Request request = mock(Request.class);
        ConcurrentMap<String, Object> attributes = new ConcurrentHashMap<String, Object>();
        attributes.putIfAbsent("bundleId", "99");
        when(request.getAttributes()).thenReturn(attributes);
        resource.init(spy.getContext(), request, null);
    }

    // @Test
    // public void a() throws Exception {
    // Representation jsGraph = resource.getJSGraph();
    // String text = jsGraph.getText();
    // System.out.println(text);
    // }
	


}
