package de.twenty11.skysail.server.ext.facebook.test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.ComponentContext;
import org.restlet.Component;

import de.twenty11.skysail.server.ext.facebook.BundleInitializer;
import de.twenty11.skysail.server.services.ComponentProvider;

public class BundleInitializerTest {

    private TestConfiguration configuration;
    private ComponentContext componentContext;
    private ComponentProvider componentProvider = Mockito.mock(ComponentProvider.class);

    private class TestConfiguration extends BundleInitializer {
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
    public void setUp() {
        configuration = new TestConfiguration();
        componentContext = Mockito.mock(ComponentContext.class);
        Component component = Mockito.mock(Component.class);
        Mockito.when(componentProvider.getComponent()).thenReturn(component);
        configuration.setComponentProvider(componentProvider);
    }

    @Test
    public void activating_creates_restlet_application() throws Exception {
        configuration.activate(componentContext);
        Assert.assertThat(configuration.getApplication(), is(notNullValue()));
        // assertThat(configuration.getApplication(), is(nullValue()));
        // assertThat(configuration.getApplication(),
    }
}
