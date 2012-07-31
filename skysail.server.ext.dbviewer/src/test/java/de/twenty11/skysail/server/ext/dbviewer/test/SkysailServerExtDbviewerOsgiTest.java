package de.twenty11.skysail.server.ext.dbviewer.test;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.equalTo;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.provision;
import static org.ops4j.pax.exam.CoreOptions.systemPackages;
import static org.ops4j.pax.exam.CoreOptions.systemProperties;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;
import static org.ops4j.pax.tinybundles.core.TinyBundles.bundle;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;

import com.jayway.restassured.RestAssured;

import de.twenty11.skysail.common.osgi.PaxExamOptionSet;
import de.twenty11.skysail.server.internal.Activator;

/**
 * @author carsten
 * 
 */
@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class SkysailServerExtDbviewerOsgiTest {

	@Configuration
	public Option[] config() {
		SkysailServerExtDbViewerOsgiSetup setup = new SkysailServerExtDbViewerOsgiSetup();
		List<Option> options = setup.getOptions(EnumSet.noneOf(PaxExamOptionSet.class));

		//options.add(systemProperty("org.osgi.service.http.port").value( "8888" ));
		options.add(systemProperty("jetty.home.bundle").value( "skysail.server" ));
		options.add(systemProperty("ds.loglevel").value("4"));
		// equinox 
		//options.add(mavenBundle("org.eclipse", "osgi", "3.6.2.R36x_v20110210"));
		  
		options.add(mavenBundle("de.twentyeleven.skysail", "skysail.server.configuration.byPropertiesService", "0.1.1-SNAPSHOT"));

		// felix webconsole
		options.add(mavenBundle("commons-fileupload", "commons-fileupload", "1.2.1"));
		options.add(mavenBundle("commons-io", "commons-io", "1.4"));
		options.add(mavenBundle("de.twentyeleven.bundled", "json", "20070829"));
		options.add(mavenBundle("org.apache.felix", "org.apache.felix.webconsole", "4.0.0"));

		// felix config admin
		options.add(mavenBundle("org.apache.felix", "org.apache.felix.configadmin", "1.4.0"));

		// freemarker
		options.add(mavenBundle("de.twentyeleven.skysail", "skysail.server.freemarker", "0.1.1-SNAPSHOT"));
		
		// ds:
//		options.add(mavenBundle("org.eclipse.equinox","org.eclipse.equinox.ds","1.2.1"));
//	 	options.add(mavenBundle("org.eclipse.equinox","org.eclipse.equinox.util","1.0.200"));
//	 	options.add(mavenBundle("org.eclipse.equinox","org.eclipse.equinox.log","1.2.100.v20100503"));
		options.add(mavenBundle("org.apache.felix", "org.apache.felix.scr", "1.6.0"));

		// ok... jetty:
		options.add(mavenBundle("org.eclipse.jetty",  "jetty-server","7.5.4.v20111024"));
		options.add(mavenBundle("javax.servlet",	  "com.springsource.javax.servlet","2.5.0"));
		options.add(mavenBundle("org.eclipse.jetty", "jetty-continuation","7.5.4.v20111024"));
		options.add(mavenBundle("org.eclipse.jetty", "jetty-http","7.5.4.v20111024"));
		options.add(mavenBundle("org.eclipse.jetty", "jetty-io","7.5.4.v20111024"));
		options.add(mavenBundle("org.eclipse.jetty", "jetty-util","7.5.4.v20111024"));
		options.add(mavenBundle("org.eclipse.jetty.osgi", "jetty-osgi-boot","7.5.4.v20111024"));
		options.add(mavenBundle("org.eclipse.jetty", "jetty-client","7.5.4.v20111024"));
		options.add(mavenBundle("org.eclipse.jetty", "jetty-deploy","7.5.4.v20111024"));
		options.add(mavenBundle("org.eclipse.jetty", "jetty-security","7.5.4.v20111024"));
		options.add(mavenBundle("org.eclipse.jetty", "jetty-servlet","7.5.4.v20111024"));
		options.add(mavenBundle("org.eclipse.jetty", "jetty-servlets","7.5.4.v20111024"));
		options.add(mavenBundle("org.eclipse.jetty", "jetty-webapp","7.5.4.v20111024"));
		options.add(mavenBundle("org.eclipse.jetty", "jetty-xml","7.5.4.v20111024"));
		//options.add(mavenBundle("org.apache.felix",	"org.apache.felix.http.bundle", "2.2.0"));

		// restassured:
		options.add(mavenBundle("com.jayway.restassured", "skysail.bundles.rest-assured", "1.6.2"));
		options.add(mavenBundle("commons-collections", "commons-collections", "3.2.1"));
		options.add(mavenBundle("org.apache.commons", "commons-lang3", "3.1"));
		options.add(mavenBundle("org.apache.httpcomponents", "httpcore-osgi", "4.1.4"));
		options.add(mavenBundle("org.apache.httpcomponents", "httpclient-osgi", "4.1.3"));
		options.add(mavenBundle("org.codehaus.groovy", "groovy-all", "1.8.4"));
		options.add(mavenBundle("org.hamcrest", "skysail.bundles.hamcrest-all", "1.2.1"));
		options.add(mavenBundle("de.twentyeleven.bundled", "tagsoup", "1.2.1"));
		options.add(mavenBundle("org.codehaus.jackson", "jackson-core-lgpl", "1.9.5"));
		
		InputStream bundleUnderTest = bundle()
				.add(de.twenty11.skysail.server.ext.dbviewer.internal.Application.class)
				.set(Constants.BUNDLE_SYMBOLICNAME, "skysail.server.ext.dbviewer")
				.set(Constants.IMPORT_PACKAGE, "de.twenty11.skysail.common")
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
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8100;
		expect().body("lotto.lottoId", equalTo(5)).when().get("/lotto");
	}

}
