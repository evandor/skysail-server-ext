package de.twenty11.skysail.server.ext.jenkins.internal.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.framework.BundleContext;
import org.restlet.Context;

import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import de.twenty11.skysail.server.ext.jenkins.MyRootResource;
import de.twenty11.skysail.server.ext.jenkins.internal.MyApplication;

@RunWith(MockitoJUnitRunner.class)
public class MyApplicationTest {

    @Mock
    private BundleContext bundleContext;

    @Mock
    private Context componentContext;

    private MyApplication myApplication;

    @Before
    public void setUp() throws Exception {
        myApplication = new MyApplication();
    }

    @Test
    public void attach() throws Exception {
        myApplication.createInboundRoot();
        //myApplication.attachToRouter("key", MyRootResource.class);
        assertThat(myApplication.getRoutes().size(), is(equalTo(1)));
    }

}
