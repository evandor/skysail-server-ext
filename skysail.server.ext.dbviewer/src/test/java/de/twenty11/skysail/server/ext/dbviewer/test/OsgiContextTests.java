package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.junit.Assert.assertTrue;
import static org.ops4j.pax.exam.CoreOptions.bootDelegationPackage;
import static org.ops4j.pax.exam.CoreOptions.cleanCaches;
import static org.ops4j.pax.exam.CoreOptions.equinox;
import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.options;
import static org.ops4j.pax.exam.CoreOptions.provision;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;
import static org.ops4j.pax.exam.CoreOptions.vmOption;
import static org.ops4j.pax.tinybundles.core.TinyBundles.bundle;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.ext.dbviewer.ConnectionsResource;

/**
 * @author carsten
 * 
 */
@RunWith(JUnit4TestRunner.class)
// @ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class OsgiContextTests {

    private static Logger logger = LoggerFactory.getLogger(OsgiContextTests.class);

    /**
     * @return the options for the testing framework.
     */
    @Configuration
    public final Option[] config() {

        InputStream bundleUnderTest = bundle().add(ConnectionsResource.class)
                .set(Constants.BUNDLE_SYMBOLICNAME, "skysail.server.serviceprovider")
                .set(Constants.EXPORT_PACKAGE, "de.twenty11.skysail.server.serviceprovider")
                .set(Constants.DYNAMICIMPORT_PACKAGE, "*")
                .set(Constants.IMPORT_PACKAGE, "de.twenty11.skysail.server.services;version=\"[0.2.0, 0.3.0)\"")
                .build();

        // @formatter:off
        return options(
                bootDelegationPackage( "sun.*" ),
                cleanCaches(),
                provision(bundleUnderTest),
                //mavenBundle("ch.qos.logback","logback-core","0.9.29"),
                //mavenBundle("ch.qos.logback","skysail.bundles.logback-classic","0.9.29"),
//                mavenBundle("org.slf4j","slf4j-api","1.6.1"),
                mavenBundle("de.twentyeleven.skysail","skysail.common","0.3.2-SNAPSHOT"),
                mavenBundle("de.twentyeleven.skysail","skysail.server","0.2.1-SNAPSHOT"),
                mavenBundle("de.twentyeleven.skysail","skysail.server.configuration.byPropertiesService","0.1.1-SNAPSHOT"),
                //mavenBundle("osgi.enterprise","osgi.enterprise","4.2.0.201003190513"),
                mavenBundle("org.eclipse.equinox","org.eclipse.equinox.ds","1.2.1"),
                mavenBundle("org.eclipse.equinox","org.eclipse.equinox.util","1.0.200"),
                junitBundles(),
                vmOption("-consoleLog"),
                systemProperty("osgi.console").value("6666"),
                systemProperty("equinox.ds.debug").value("true"),
                systemProperty("equinox.ds.print").value("true"),
                equinox().version("3.6.2")
                
        );
    	
    	
    	
    	
    	
		// return options(mavenBundle("de.twenty11.skysail",
		// "skysail.server.ext.dbviewer", "0.0.1-SNAPSHOT")
		// // mavenBundle("org.slf4j", "slf4j-api", "1.6.1"),
		// //
		// //mavenBundle("de.evandor","skysail.server.osgi.logback.config","0.4.0").noStart(),
		// //
		// mavenBundle("de.evandor","skysail.server.osgi.logging.osgi-over-slf4j","0.0.1-SNAPSHOT"),
		// // mavenBundle("ch.qos.logback", "logback-core",
		// "0.9.29").startLevel(3),
		// // mavenBundle("ch.qos.logback", "logback-classic",
		// "0.9.29").startLevel(3),
		// // mavenBundle("org.eclipse.equinox","log","1.2.100.v20100503"),
		// // mavenBundle("org.ops4j.pax.exam","pax-exam-junit","2.2.0"),
		// // TODO make maven bundle
		// //
		// bundle("file:///home/carsten/workspaces/skysale2/skysail.server.restlet/src/main/webapp/WEB-INF/eclipse/plugins/freemarker_2.3.16.jar"),
		// //
		// scanDir("/home/carsten/workspaces/skysale2/skysail.server.osgi.ext.freemarker"),
		// // junitBundles()
		// // equinox().version("3.6.2")
		// );
		// // return options(
		// //
		// scanDir("C:/workspaces/skysail/skysail.server.restlet/src/main/webapp/WEB-INF/eclipse/plugins/skysail/").filter("*.jar"),
		// // junitBundles()
		// // // equinox().version("3.6.2")
		// // );
    }

    /**
     * @param bc
     *            bundleContext
     * @throws IOException
     *             should not happen
     */
    @Test
    public final void testLimitedQueue(final BundleContext bc) throws IOException {
    	assertTrue(true);
    }

}
