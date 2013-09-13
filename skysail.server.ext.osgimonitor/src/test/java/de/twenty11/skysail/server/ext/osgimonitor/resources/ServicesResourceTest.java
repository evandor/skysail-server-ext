package de.twenty11.skysail.server.ext.osgimonitor.resources;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;
import org.restlet.representation.Representation;

import de.twenty11.skysail.server.ext.osgimonitor.OsgiMonitorViewerApplication;
import de.twenty11.skysail.server.ext.osgimonitor.domain.ServiceDescriptor;
import de.twenty11.skysail.server.ext.osgimonitor.test.BaseTests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicesResourceTest extends BaseTests {

    private ServicesResource resource;

    @Before
    public void setUp() throws Exception {
        OsgiMonitorViewerApplication spy = setUpRestletApplication();
        ServiceReference service = mock(ServiceReference.class);
        Bundle bundle = mock(Bundle.class);
        when(bundle.getVersion()).thenReturn(new Version("1.0.0"));
        when(bundle.getSymbolicName()).thenReturn("dummyBundle");
        when(service.getBundle()).thenReturn(bundle);
        when(service.getUsingBundles()).thenReturn(new Bundle[] { bundle });
        when(service.getPropertyKeys()).thenReturn(new String[] { "foo" });
        when(service.getProperty("foo")).thenReturn("bar");
        BundleContext context = mock(BundleContext.class);

        when(context.getAllServiceReferences(null, null)).thenReturn(new ServiceReference[] { service });
        when(spy.getBundleContext()).thenReturn(context);
        //
        resource = new ServicesResource();
        // Request request = mock(Request.class);
        // ConcurrentMap<String, Object> attributes = new
        // ConcurrentHashMap<String, Object>();
        // attributes.putIfAbsent("bundleId", "99");
        // when(request.getAttributes()).thenReturn(attributes);
        // resource.init(spy.getContext(), request, null);
    }

    @Test
    @Ignore
    public void returns_services_with_proper_values() throws Exception {
        List<ServiceDescriptor> services = getServices();
        assertThat(services.size(), is(equalTo(1)));
        // assertThat(bundles.get(0).getVersion(),
        // is(equalTo("1.2.3.qualifier")));
        // assertThat(bundles.get(0).getState(), is(equalTo("Active")));

    }

    @Test
    @Ignore
    public void gives_error_message_for_post_when_location_doesnt_start_with_prefix() throws Exception {
        Representation answer = resource.install("wrongLocation");
        assertThat(answer.getText(), is(equalTo("location didn't start with 'prefix'")));
    }

}
