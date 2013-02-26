package de.twenty11.skysail.server.ext.osgimonitor.internal.test;

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
    }

    @Before
    public void createConfiguration() throws Exception {
        // componentContext = mock(ComponentContext.class);
        BundleContext bundleContext = mock(BundleContext.class);
        Mockito.when(componentContext.getBundleContext()).thenReturn(bundleContext);
        OsgiMonitorViewerApplication application = new OsgiMonitorViewerApplication(componentContext.getBundleContext());

        Mockito.when(componentProvider.getComponent()).thenReturn(new Component());

        Mockito.when(applicationProvider.getApplication()).thenReturn(application);
        testConfiguration = new TestConfiguration();
        // testConfiguration.setApplicationProvider(applicationProvider);
        // testConfiguration.setComponentProvider(componentProvider);
    }

    @Test
    public void aaa() throws Exception {

        // testConfiguration.activate(componentContext);
    }

}
