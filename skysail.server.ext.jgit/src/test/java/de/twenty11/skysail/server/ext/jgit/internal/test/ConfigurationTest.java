package de.twenty11.skysail.server.ext.jgit.internal.test;

import javax.persistence.EntityManagerFactory;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.restlet.Component;

import de.twenty11.skysail.server.ext.jgit.internal.Configuration;
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
        EntityManagerFactory emf = Mockito.mock(EntityManagerFactory.class);
        configuration.setEmf(emf);
    }

}
