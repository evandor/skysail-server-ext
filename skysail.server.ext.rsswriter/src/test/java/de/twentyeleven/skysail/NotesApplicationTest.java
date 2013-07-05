package de.twentyeleven.skysail;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.ComponentContext;
import org.restlet.Component;

import de.twentyeleven.skysail.MyApplication;
import de.twenty11.skysail.server.services.ComponentProvider;

public class NotesApplicationTest {

    private TestFacebookApplication configuration;
    private ComponentContext componentContext;
    private ComponentProvider componentProvider = Mockito.mock(ComponentProvider.class);

    private class TestFacebookApplication extends MyApplication {
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
        configuration = new TestFacebookApplication();
        componentContext = Mockito.mock(ComponentContext.class);
        Component component = Mockito.mock(Component.class);
        Mockito.when(componentProvider.getComponent()).thenReturn(component);
    }

    @Test
    public void activating_creates_restlet_application() throws Exception {
        configuration.activate(componentContext);
        Assert.assertThat(configuration.getApplication(), is(notNullValue()));
        // assertThat(configuration.getApplication(), is(nullValue()));
        // assertThat(configuration.getApplication(),
    }
}
