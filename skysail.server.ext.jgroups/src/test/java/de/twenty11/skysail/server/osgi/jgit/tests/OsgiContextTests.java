package de.twenty11.skysail.server.osgi.jgit.tests;

import static org.junit.Assert.assertTrue;
import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.options;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;

import de.twenty11.skysail.server.osgi.jgit.service.definition.JGroupsServiceImpl;

@RunWith(JUnit4TestRunner.class)
public class OsgiContextTests {
    private static JGroupsServiceImpl jgit;

    /**
     * @return the options for the testing framework.
     */
    @Configuration
    public final Option[] config() {
        return options(mavenBundle("de.2011.skysail", "skysail.server.osgi.jgit", "0.0.1-SNAPSHOT"),
                mavenBundle("org.eclipse.jgit", "org.eclipse.jgit", "1.1.0.201109151100-r"),
                mavenBundle("org.openengsb.wrapped", "com.jcraft.jsch-all", "0.1.42.w1"),
                systemProperty("osgi.console").value("6666"),
                // mavenBundle("org.slf4j", "slf4j-simple", "1.6.1"),
                // mavenBundle("org.ops4j.pax.exam","pax-exam-junit","2.2.0"),
                // TODO make maven bundle
                // bundle("file:///home/carsten/workspaces/skysale2/skysail.server.restlet/src/main/webapp/WEB-INF/eclipse/plugins/freemarker_2.3.18.jar"),
                // scanDir("/home/carsten/workspaces/skysale2/skysail.server.osgi.ext.freemarker"),
                junitBundles()
        // equinox().version("3.6.2")
        );
    }

    @BeforeClass
    public static void setUp() throws IOException {
    }

    @AfterClass
    public static void tearDown() throws Exception {
    }

}
