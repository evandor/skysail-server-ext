package de.twentyeleven.skysail.skysail.server.ext.osgideps.test;

import static org.ops4j.pax.exam.CoreOptions.bundle;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;

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
import org.osgi.framework.BundleContext;

import de.twenty11.skysail.common.osgi.PaxExamOptionSet;
import de.twentyeleven.skysail.skysail.server.ext.osgideps.JGraphOsgiServicesVisualizer;

/**
 * @author carsten
 * 
 */
@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class SkysailServerExtOsgiDepsIT {

    private List<PaxExamOptionSet> dependencies = new ArrayList<PaxExamOptionSet>();

    @Inject
    private BundleContext bundleContext;

    @Configuration
    public Option[] config() {

        dependencies.add(PaxExamOptionSet.BASE);
        dependencies.add(PaxExamOptionSet.DEBUGGING);

        OsgiTestIntegrationSetup setup = new OsgiTestIntegrationSetup();
        List<Option> options = setup.getOptions(EnumSet.copyOf(dependencies));

        // _this_ bundle from target directory
        options.add(bundle("file:target/skysail.server.ext.osgideps-" + setup.getProjectVersion() + ".jar"));

        // options.add(systemProperty("org.osgi.service.http.port").value(
        // "8888" ));
        // options.add(systemProperty("jetty.home.bundle").value("skysail.server"));
        options.add(systemProperty("ds.loglevel").value("4"));

        Option[] options2Use = options.toArray(new Option[options.size()]);
        setup.logOptionsUsed(options2Use);
        return options2Use;
    }

    @Test
    @Ignore
    public void test() {
        // OsgiServicesVisualizer visualizer = new OsgiServicesVisualizer();
        // visualizer.buildGraphFromOsgiContext(bundleContext);
        // visualizer.run();
        // assert (true);
    }

    @Test
    public void testJGraphCall() {
        JGraphOsgiServicesVisualizer visualizer = new JGraphOsgiServicesVisualizer();
        visualizer.buildGraphFromOsgiContext(bundleContext);
        visualizer.run();
        assert (true);
    }

}
