package de.twenty11.skysail.server.ext.osgimonitor.internal.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.framework.BundleContext;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.ComponentContext;
import org.restlet.Component;

import de.twenty11.skysail.server.ext.osgimonitor.internal.Configuration;
import de.twenty11.skysail.server.ext.osgimonitor.internal.OsgiMonitorViewerApplication;
import de.twenty11.skysail.server.services.ApplicationProvider;
import de.twenty11.skysail.server.services.ComponentProvider;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationTest {

    @Mock
    private ApplicationProvider applicationProvider;
    @Mock
    private ComponentProvider componentProvider;
    @Mock
    private ComponentContext componentContext;

    private TestConfiguration testConfiguration;

    private class TestConfiguration extends Configuration {
        @Override
        protected void activate(ComponentContext componentContext) throws ConfigurationException {
            super.activate(componentContext);
        }
        @Override
        protected void deactivate(ComponentContext componentContext) {
        	super.deactivate(componentContext);
        }
    }

    @Before
    public void createConfiguration() throws Exception {
        BundleContext bundleContext = mock(BundleContext.class);
        //Mockito.when(bundleContext.)
        Mockito.when(componentContext.getBundleContext()).thenReturn(bundleContext);
        OsgiMonitorViewerApplication application = new OsgiMonitorViewerApplication(componentContext.getBundleContext());

        Mockito.when(componentProvider.getComponent()).thenReturn(new Component());

        Mockito.when(applicationProvider.getApplication()).thenReturn(application);
        testConfiguration = new TestConfiguration();
        // testConfiguration.setApplicationProvider(applicationProvider);
        testConfiguration.setComponentProvider(componentProvider);
    }

    @Test
    public void can_activate_component() throws Exception {
        testConfiguration.activate(componentContext);
        assertThat(testConfiguration.getApplication().getName(), is(equalTo("osgimonitor")));
    }

    @Test
    public void can_deactivate_component() throws Exception {
        testConfiguration.activate(componentContext);
        testConfiguration.deactivate(componentContext);
        assertThat(testConfiguration.getApplication().getName(), is(equalTo("osgimonitor")));
    }

}
