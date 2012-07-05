package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.junit.Assert.assertTrue;
import static org.ops4j.pax.exam.CoreOptions.bootDelegationPackage;
import static org.ops4j.pax.exam.CoreOptions.cleanCaches;
import static org.ops4j.pax.exam.CoreOptions.felix;
import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.options;
import static org.ops4j.pax.exam.CoreOptions.provision;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;
import static org.ops4j.pax.exam.CoreOptions.vmOption;
import static org.ops4j.pax.tinybundles.core.TinyBundles.bundle;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.osgi.framework.Bundle;
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

    @javax.inject.Inject
    private BundleContext context;
    
    /**
     * @return the options for the testing framework.
     */
    @Configuration
    public final Option[] config() {

        InputStream bundleUnderTest = bundle().add(ConnectionsResource.class)
                .set(Constants.BUNDLE_SYMBOLICNAME, "skysail.server.ext.dbviewer")
                .set(Constants.EXPORT_PACKAGE, "de.twenty11.skysail.server.ext.dbviewer")
                .set(Constants.IMPORT_PACKAGE, "org.hamcrest")
                .set(Constants.DYNAMICIMPORT_PACKAGE, "*")
                //.set(Constants.IMPORT_PACKAGE, "de.twenty11.skysail.server.services;version=\"[0.2.0, 0.3.0)\"")
                .build();

        // @formatter:off
        return options(
                bootDelegationPackage( "sun.*" ),
                cleanCaches(),
                provision(bundleUnderTest),
                mavenBundle("de.twentyeleven.skysail","skysail.common","0.3.2-SNAPSHOT"),
                mavenBundle("commons-lang","commons-lang","2.6"),
                
                mavenBundle("de.twentyeleven.skysail","skysail.server","0.2.1-SNAPSHOT"),
                mavenBundle("org.codehaus.jackson","jackson-core-lgpl","1.9.5"),
                mavenBundle("org.codehaus.jackson","jackson-mapper-lgpl","1.9.5"),    
                
                mavenBundle("org.restlet.jee","org.restlet","2.0.11"),
                mavenBundle("org.restlet.jee","org.restlet.ext.freemarker","2.0.11"),
                mavenBundle("org.restlet.jee","org.restlet.ext.xml","2.0.11"),
                mavenBundle("org.restlet.jee","org.restlet.ext.servlet","2.0.11"),
                mavenBundle("org.restlet.jee","org.restlet.ext.jackson","2.0.11"),
                mavenBundle("org.restlet.jee","org.restlet.ext.xstream","2.0.11"),
                mavenBundle("org.restlet.jee","org.restlet.ext.wadl","2.0.11"),
                mavenBundle("org.freemarker","com.springsource.freemarker","2.3.18"),
                
                mavenBundle("com.thoughtworks.xstream","com.springsource.com.thoughtworks.xstream","1.3.1"),
                mavenBundle("javax.xml.stream","com.springsource.javax.xml.stream","1.0.1"),
                mavenBundle("org.xmlpull","com.springsource.org.xmlpull","1.1.4.c"),
                mavenBundle("org.codehaus.jettison","com.springsource.org.codehaus.jettison","1.0.1"),
                
                mavenBundle("javax.servlet","com.springsource.javax.servlet","2.5.0"),
                
//                mavenBundle("de.twentyeleven.skysail","skysail.server.configuration.byPropertiesService","0.1.1-SNAPSHOT"),
//                mavenBundle("org.eclipse.equinox","org.eclipse.equinox.ds","1.2.1"),
//                mavenBundle("org.eclipse.equinox","org.eclipse.equinox.util","1.0.200"),
                // --- rest-assured (testing only) ---
                mavenBundle("com.jayway.restassured", "skysail.bundles.rest-assured", "1.6.2"),
                mavenBundle("org.codehaus.groovy","groovy", "1.8.4"),
                mavenBundle("org.antlr", "com.springsource.antlr","2.7.7"),
                mavenBundle("org.objectweb.asm","com.springsource.org.objectweb.asm","3.2.0"),
                mavenBundle("org.apache.commons", "commons-lang3", "3.1"),
                mavenBundle("commons-collections", "commons-collections", "3.2.1"),
                mavenBundle("org.hamcrest", "skysail.bundles.hamcrest-all", "1.2.1"),
                
                mavenBundle("org.apache.httpcomponents","httpclient-osgi","4.1.3"), 
                mavenBundle("org.apache.commons","com.springsource.org.apache.commons.codec","1.5.0"),
                mavenBundle("org.apache.httpcomponents","httpcore-osgi","4.1.4"),
                
                //junitBundles(),
                vmOption("-consoleLog"),
                systemProperty("osgi.console").value("6666"),
                systemProperty("equinox.ds.debug").value("true"),
                systemProperty("equinox.ds.print").value("true"),
                //equinox().version("3.6.2")
                felix().version("3.2.2")
        );
    	
    	
    	

    }

    /**
     * @param bc
     *            bundleContext
     * @throws IOException
     *             should not happen
     */
    @Test
    public final void test() {
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
