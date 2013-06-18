package de.twenty11.skysail.server.ext.mail.internal.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.restlet.Component;

import de.twenty11.skysail.server.ext.mail.internal.Configuration;
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
    public void can_activate_component() throws Exception {
    }

    @Test
    public void can_deactivate_component() throws Exception {
    }
}
