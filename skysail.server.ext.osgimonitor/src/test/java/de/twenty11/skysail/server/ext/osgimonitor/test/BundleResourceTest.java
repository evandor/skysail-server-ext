package de.twenty11.skysail.server.ext.osgimonitor.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.restlet.Request;
import org.restlet.representation.Representation;

import de.twenty11.skysail.common.ext.osgimonitor.BundleDetails;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.server.ext.osgimonitor.BundleResource;
import de.twenty11.skysail.server.ext.osgimonitor.internal.OsgiMonitorViewerApplication;

public class BundleResourceTest extends BaseTests {
    
    private BundleResource bundleResource;

	@Before
    public void setUp() throws Exception {
        
        OsgiMonitorViewerApplication spy = setUpRestletApplication();
        BundleContext context = mock(BundleContext.class);
        Bundle bundle = mock(Bundle.class);
        when(bundle.getBundleId()).thenReturn(99l);
        when(bundle.getSymbolicName()).thenReturn("symbolic");
        when(bundle.getLastModified()).thenReturn(111l);
		when(context.getBundle(99l)).thenReturn(bundle);
		when(spy.getBundleContext()).thenReturn(context);
		
        bundleResource = new BundleResource();
        Request request = mock(Request.class);
        ConcurrentMap<String, Object> attributes = new ConcurrentHashMap<String, Object>();
        attributes.putIfAbsent("bundleId", "99");
		when(request.getAttributes()).thenReturn(attributes);
		bundleResource.init(spy.getContext(), request, null);
    }
    
    @Test
    public void getBundle_returns_bundleDetails_for_bundleId_from_request() throws Exception {
    	Response<BundleDetails> bundle = bundleResource.getBundle();
    	BundleDetails details = bundle.getData();
    	assertThat(details.getBundleId(), is(equalTo(99l)));
    }
    
    @Test 
    public void canIssue_GET_request() throws Exception {
    	org.restlet.Response response = get("bundle");
        assertDefaults(response);
        Representation entity = response.getEntity();
        System.out.println(entity.getText());
        Response<BundleDetails> skysailResponse = mapper.readValue(entity.getText(),
                new TypeReference<Response<BundleDetails>>() {
                });
        //assertThat(skysailResponse.getMessage(), skysailResponse.getSuccess(), is(true));
       
    }

    
   
}
