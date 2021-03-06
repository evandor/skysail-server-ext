package de.twenty11.skysail.server.ext.bookmarks;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.restlet.Component;

import de.twenty11.skysail.server.ext.bookmarks.Configuration;
import de.twenty11.skysail.server.services.ComponentProvider;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationTest {

    @Mock
    private ComponentProvider componentProvider;

    private Component component = new Component();

    private Configuration configuration = new Configuration();

    @Before
    public void createConfiguration() throws Exception {
        Mockito.when(componentProvider.getComponent()).thenReturn(component);
    }

    @Test
    @Ignore
    public void can_activate_component() throws Exception {
        configuration.setComponentProvider(componentProvider);
        configuration.activate();
        assertThat(configuration.getApplication().getName(), is(equalTo("bookmarks")));
    }

    @Test
    @Ignore
    public void can_deactivate_component() throws Exception {
        configuration.setComponentProvider(componentProvider);
        configuration.activate();
        configuration.deactivate();
        assertThat(configuration.getApplication(), is(nullValue()));
    }
}
