package de.twenty11.skysail.server.ext.jenkins.internal.test;

import org.junit.Before;
import org.mockito.Mockito;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.ComponentContext;
import org.restlet.Component;

import de.twenty11.skysail.server.ext.jenkins.internal.Configuration;
import de.twenty11.skysail.server.services.ComponentProvider;

public class ConfigurationTest {

    private TestConfiguration configuration;
    private ComponentContext componentContext;
    private ComponentProvider componentProvider = Mockito.mock(ComponentProvider.class);

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
    public void setUp() {
        configuration = new TestConfiguration();
        componentContext = Mockito.mock(ComponentContext.class);
        Component component = Mockito.mock(Component.class);
        Mockito.when(componentProvider.getComponent()).thenReturn(component);
        configuration.setComponentProvider(componentProvider);
    }

}
