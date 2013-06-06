package de.twenty11.skysail.server.ext.facebook.itest;

import static org.junit.Assert.assertTrue;
import static org.ops4j.pax.exam.CoreOptions.bundle;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.restlet.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.testing.utils.OsgiTestingUtils;
import de.twenty11.skysail.common.testing.utils.PaxExamOptionSet;
import de.twenty11.skysail.server.services.ApplicationProvider;

@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class SkysailServerExtensionOsgiIT {

    private static Logger logger = LoggerFactory.getLogger(SkysailServerExtensionOsgiIT.class.getName());

    private List<PaxExamOptionSet> dependencies = new ArrayList<PaxExamOptionSet>();

    @Inject
    private BundleContext context;

    @Configuration
    public Option[] config() {

        dependencies.add(PaxExamOptionSet.BASE);
        dependencies.add(PaxExamOptionSet.DEBUGGING);

        SkysailServerExtensionOsgiSetup setup = new SkysailServerExtensionOsgiSetup();
        List<Option> options = setup.getOptions(EnumSet.copyOf(dependencies));

        // _this_ bundle from target directory
        // TODO proper naming!
        String currentBundleSource = "file:target/skysail.server.ext.facebook-" + setup.getProjectVersion() + ".jar";
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

    }

    @Test
    @Ignore
    public void a() {
        ApplicationProvider dummyApplicationProvider = new ApplicationProvider() {

            @Override
            public Application getApplication() {
                return new Application() {
                    @Override
                    public String getAuthor() {
                        return "author";
                    }
                };
            }
        };
        assertTrue(dummyApplicationProvider != null);

        context.registerService(ApplicationProvider.class.getName(), dummyApplicationProvider, null);
        ServiceReference serviceReference = context.getServiceReference(ApplicationProvider.class.getName());
        ApplicationProvider service = (ApplicationProvider) context.getService(serviceReference);
        Application applicationFromService = service.getApplication();

        assertTrue(applicationFromService.getAuthor().equals("author"));
    }

}
