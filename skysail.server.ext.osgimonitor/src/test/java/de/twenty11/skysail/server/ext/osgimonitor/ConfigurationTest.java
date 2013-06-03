package de.twenty11.skysail.server.ext.osgimonitor;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isNull;

import java.util.Dictionary;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.ComponentContext;
import org.restlet.Component;
import org.restlet.Context;

import de.twenty11.skysail.server.ext.osgimonitor.Configuration;
import de.twenty11.skysail.server.ext.osgimonitor.OsgiMonitorViewerApplication;
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
    @Mock
    private BundleContext bundleContext;
    @Mock
    private ServiceRegistration serviceRegistration;
    @Mock
    private Context context;

    private TestConfiguration testConfiguration = new TestConfiguration();
    private Component component = new Component();

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
		OsgiMonitorViewerApplication application = new OsgiMonitorViewerApplication(bundleContext, context);

        Mockito.when(bundleContext.registerService(anyString(), anyObject(), (Dictionary<?, ?>) isNull()))
                .thenReturn(serviceRegistration);
        Mockito.when(componentContext.getBundleContext()).thenReturn(bundleContext);
        Mockito.when(componentProvider.getComponent()).thenReturn(component);
        Mockito.when(applicationProvider.getApplication()).thenReturn(application);
    }

    @Test
    public void can_activate_component() throws Exception {
        testConfiguration.setComponentProvider(componentProvider);
        testConfiguration.activate(componentContext);
        // assertThat(testConfiguration.getApplication().getName(), is(equalTo("osgimonitor")));
    }

    @Test
    @Ignore
    public void can_deactivate_component() throws Exception {
        testConfiguration.setComponentProvider(componentProvider);
        testConfiguration.activate(componentContext);
        testConfiguration.deactivate(componentContext);
        // assertThat(testConfiguration.getApplication(), is(nullValue()));
    }

    @Test
    @Ignore
    public void can_deal_with_new_application() throws Exception {
        testConfiguration.setComponentProvider(componentProvider);
        testConfiguration.activate(componentContext);
        // testConfiguration.setApplicationProvider(applicationProvider);
        assertThat(component.getDefaultHost().getRoutes().size(), is(equalTo(1)));
    }

    @Test
    @Ignore
    public void can_remove_added_application() throws Exception {
        testConfiguration.setComponentProvider(componentProvider);
        testConfiguration.activate(componentContext);
        // testConfiguration.setApplicationProvider(applicationProvider);
        // testConfiguration.unsetApplicationProvider(applicationProvider);
        assertThat(component.getDefaultHost().getRoutes().size(), is(equalTo(0)));
    }

}
