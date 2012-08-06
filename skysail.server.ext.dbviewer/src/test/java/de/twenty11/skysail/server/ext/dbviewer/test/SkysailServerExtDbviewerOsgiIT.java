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

		// _this_ bundle from target directory
		options.add(bundle("file:target/skysail.server.ext.dbviewer-"
				+ setup.getProjectVersion() + ".jar"));

		// options.add(systemProperty("org.osgi.service.http.port").value(
		// "8888" ));
		options.add(systemProperty("jetty.home.bundle").value("skysail.server"));
		options.add(systemProperty("ds.loglevel").value("4"));

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
		RestAssured.port = 8554;
		expect().body("success", equalTo(true)).given().auth()
				.basic("scott", "tiger").when().get("/dbviewer/?media=json");
	}

}
