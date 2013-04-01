package de.twenty11.skysail.server.ext.jenkins.internal.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.framework.BundleContext;
import org.restlet.Context;

import de.twenty11.skysail.server.ext.jenkins.internal.MyApplication;

import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

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
    public void calling_createInboundRoute_attaches_routes_from_MyApplication() throws Exception {
        myApplication.createInboundRoot();
        assertThat(myApplication.getRoutes().size(), is(greaterThan(1)));
    }

}
