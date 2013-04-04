package de.twenty11.skysail.server.ext.jenkins.itest;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.EnumSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.security.Verifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.testing.utils.OsgiTestingUtils;
import de.twenty11.skysail.common.testing.utils.PaxExamOptionSet;
import de.twenty11.skysail.server.services.ApplicationProvider;
import de.twenty11.skysail.server.services.ComponentProvider;

import static org.junit.Assert.assertTrue;

import static org.ops4j.pax.exam.CoreOptions.bundle;

@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class SkysailServerExtJenkinsOsgiIT {

    private static Logger logger = LoggerFactory.getLogger(SkysailServerExtJenkinsOsgiIT.class.getName());

    private List<PaxExamOptionSet> dependencies = new ArrayList<PaxExamOptionSet>();

    @Inject
    private BundleContext context;

    @Configuration
    public Option[] config() {

        dependencies.add(PaxExamOptionSet.BASE);
        dependencies.add(PaxExamOptionSet.DEBUGGING);

        SkysailServerExtJenkinsOsgiSetup setup = new SkysailServerExtJenkinsOsgiSetup();
        List<Option> options = setup.getOptions(EnumSet.copyOf(dependencies));

        // _this_ bundle from target directory
        String currentBundleSource = "file:target/skysail.server.ext.jenkins-" + setup.getProjectVersion() + ".jar";
        logger.info("adding {} to tests...", currentBundleSource);
        options.add(bundle(currentBundleSource));

        for (Option option : options) {
            logger.debug(option.toString());
        }

        return options.toArray(new Option[options.size()]);
    }

    @Test
    public void shouldFindSomeBundlesInActiveState() {
        Bundle bundle = OsgiTestingUtils.getBundleForSymbolicName(context, "skysail.server");
        assertTrue(bundle != null);
        assertTrue(bundle.getState() == 32);

        bundle = OsgiTestingUtils.getBundleForSymbolicName(context, "skysail.server.ext.jenkins");
        assertTrue(bundle != null);
        assertTrue(bundle.getState() == 32);
    }

    @Test
    public void a() {
        ComponentProvider dummyComponentProvider = new ComponentProvider() {
            @Override
            public Component getComponent() {
                return new Component();
            }
            @Override
            public Verifier getVerifier() {
                return Mockito.mock(Verifier.class);
            }
        };
        EntityManagerFactory dummyEmf = new EntityManagerFactory() {

            @Override
            public EntityManager createEntityManager() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public EntityManager createEntityManager(Map map) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public CriteriaBuilder getCriteriaBuilder() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Metamodel getMetamodel() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public boolean isOpen() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void close() {
                // TODO Auto-generated method stub

            }

            @Override
            public Map<String, Object> getProperties() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Cache getCache() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public PersistenceUnitUtil getPersistenceUnitUtil() {
                // TODO Auto-generated method stub
                return null;
            }

        };// Mockito.mock(EntityManagerFactory.class);

        // provide the required services, so that the configuration constraints are fulfilled.
        context.registerService(ComponentProvider.class.getName(), dummyComponentProvider, null);
        Dictionary props = new Hashtable();
        props.put("osgi.unit.name", "JenkinsPU");
        context.registerService(EntityManagerFactory.class.getName(), dummyEmf, props);

        // check the service which should have been created by the configuration
        ServiceReference serviceReference = context.getServiceReference(ApplicationProvider.class.getName());
        // assertThat(serviceReference, is(notNullValue()));
        
        ApplicationProvider service = (ApplicationProvider) context.getService(serviceReference);
        Application applicationFromService = service.getApplication();

        assertTrue(applicationFromService.getName().equals("jenkins"));
    }

}
