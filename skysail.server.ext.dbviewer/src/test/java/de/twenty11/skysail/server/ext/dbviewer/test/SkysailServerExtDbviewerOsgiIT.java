package de.twenty11.skysail.server.ext.dbviewer.test;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.equalTo;
import static org.ops4j.pax.exam.CoreOptions.bundle;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.provision;
import static org.ops4j.pax.exam.CoreOptions.systemPackages;
import static org.ops4j.pax.exam.CoreOptions.systemProperties;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;
import static org.ops4j.pax.tinybundles.core.TinyBundles.bundle;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
public class SkysailServerExtDbviewerOsgiIT {

	private List<PaxExamOptionSet> dependencies = new ArrayList<PaxExamOptionSet>();
	
	@Configuration
	public Option[] config() {
		
		dependencies.add(PaxExamOptionSet.BASE);
		dependencies.add(PaxExamOptionSet.DEBUGGING);
		
		SkysailServerExtDbViewerOsgiSetup setup = new SkysailServerExtDbViewerOsgiSetup();
		List<Option> options = setup.getOptions(EnumSet.copyOf(dependencies));

		//options.add(systemProperty("org.osgi.service.http.port").value( "8888" ));
		options.add(systemProperty("jetty.home.bundle").value( "skysail.server" ));
		options.add(systemProperty("ds.loglevel").value("4"));
		// equinox 
		//options.add(mavenBundle("org.eclipse", "osgi", "3.6.2.R36x_v20110210"));
		  
		// felix webconsole
//		options.add(mavenBundle("commons-fileupload", "commons-fileupload", "1.2.1"));
//		options.add(mavenBundle("commons-io", "commons-io", "1.4"));
//		options.add(mavenBundle("de.twentyeleven.bundled", "json", "20070829"));
//		options.add(mavenBundle("org.apache.felix", "org.apache.felix.webconsole", "4.0.0"));

		// felix config admin
		options.add(mavenBundle("org.apache.felix", "org.apache.felix.configadmin", "1.4.0"));

		// this  bundle
		options.add(mavenBundle("de.twentyeleven.skysail", "skysail.server.ext.dbviewer", "0.0.4-SNAPSHOT"));

		options.add(mavenBundle("commons-dbcp", "skysail.bundles.commons-dbcp", "1.4"));
		
//		options.add(bundle("reference:file:./"));
//		
//		InputStream bundleUnderTest = bundle()
//				.add(de.twenty11.skysail.server.ext.dbviewer.internal.Application.class)
//				.set(Constants.BUNDLE_SYMBOLICNAME, "skysail.server.ext.dbviewer")
//				.set(Constants.IMPORT_PACKAGE, "de.twenty11.skysail.common")
//				.build();
		//options.add(provision(bundleUnderTest));
		Option[] options2Use = options.toArray(new Option[options.size()]);
		setup.logOptionsUsed(options2Use);
		return options2Use;
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
