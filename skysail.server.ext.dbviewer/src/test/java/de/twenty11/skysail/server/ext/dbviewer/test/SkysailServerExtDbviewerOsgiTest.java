package de.twenty11.skysail.server.ext.dbviewer.test;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.equalTo;
import static org.ops4j.pax.exam.CoreOptions.provision;
import static org.ops4j.pax.tinybundles.core.TinyBundles.bundle;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.integrationtest.SkysailServerOsgiSetup;
import de.twenty11.skysail.server.internal.Activator;

/**
 * @author carsten
 * 
 */
@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class SkysailServerExtDbviewerOsgiTest {

    private static Logger logger = LoggerFactory.getLogger(SkysailServerExtDbviewerOsgiTest.class);

    @Inject
    private BundleContext context;

    @Configuration
    public Option[] config() {
        SkysailServerOsgiSetup setup = new SkysailServerOsgiSetup();
        List<Option> options = setup.getOptions();
        
        //options.add(mavenBundle("de.twentyeleven.skysail","skysail.server", "0.2.1-SNAPSHOT"));

        InputStream bundleUnderTest = bundle().add(Activator.class)
                .set(Constants.BUNDLE_SYMBOLICNAME, "skysail.server.ext.dbviewer")
                //.set(Constants.IMPORT_PACKAGE, "de.twenty11.skysail.common.test")
                .build();
        options.add(provision(bundleUnderTest));
        return options.toArray(new Option[options.size()]);
    }

    /**
     * @param bc
     *            bundleContext
     * @throws IOException
     *             should not happen
     */
    @Test
    public void test() {
        debug(context);
        expect().body("lotto.lottoId", equalTo(5)).when().get("/lotto");
    }

    private void debug(BundleContext context2) {
        System.out.println("=============================");
        Bundle[] bundles = context2.getBundles();
        for(Bundle bundle : bundles) {
            System.out.println(bundle.getBundleId() + ": " + bundle.getSymbolicName() + ": " + bundle.getState() + " - " + bundle.getLocation());
        }
    }

}
