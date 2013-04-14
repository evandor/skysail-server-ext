package de.twenty11.skysail.server.ext.bookmarks.internal.test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.restlet.Component;
import org.restlet.Context;

import de.twenty11.skysail.server.ext.bookmarks.internal.Configuration;
import de.twenty11.skysail.server.ext.bookmarks.internal.MyApplication;
import de.twenty11.skysail.server.services.ApplicationProvider;
import de.twenty11.skysail.server.services.ComponentProvider;

import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

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

    private Configuration testConfiguration = new Configuration();
    private Component component = new Component();

    @Before
    public void createConfiguration() throws Exception {
        MyApplication application = new MyApplication(context);

        // Mockito.when(bundleContext.registerService(anyString(), anyObject(), (Dictionary<?, ?>)
        // isNull())).thenReturn(
        // serviceRegistration);
        // Mockito.when(componentContext.getBundleContext()).thenReturn(bundleContext);
        Mockito.when(componentProvider.getComponent()).thenReturn(component);
        // Mockito.when(applicationProvider.getApplication()).thenReturn(application);
    }

    @Test
    public void can_activate_component() throws Exception {
        testConfiguration.setComponentProvider(componentProvider);
        testConfiguration.activate();
        assertThat(testConfiguration.getApplication().getName(), is(equalTo("bookmarks")));
    }

    @Test
    public void can_deactivate_component() throws Exception {
        testConfiguration.setComponentProvider(componentProvider);
        testConfiguration.activate();
        testConfiguration.deactivate();
        // assertThat(testConfiguration.getApplication(), is(nullValue()));
    }

    @Test
    @Ignore
    public void can_deal_with_new_application() throws Exception {
        testConfiguration.setComponentProvider(componentProvider);
        testConfiguration.activate();
        // testConfiguration.setApplicationProvider(applicationProvider);
        assertThat(component.getDefaultHost().getRoutes().size(), is(equalTo(1)));
    }

    @Test
    @Ignore
    public void can_remove_added_application() throws Exception {
        testConfiguration.setComponentProvider(componentProvider);
        testConfiguration.activate();
        // testConfiguration.setApplicationProvider(applicationProvider);
        // testConfiguration.unsetApplicationProvider(applicationProvider);
        assertThat(component.getDefaultHost().getRoutes().size(), is(equalTo(0)));
    }

}
