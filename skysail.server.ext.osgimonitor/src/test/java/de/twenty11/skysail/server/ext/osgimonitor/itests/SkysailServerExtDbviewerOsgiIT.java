//package de.twenty11.skysail.server.ext.osgimonitor.itests;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.ops4j.pax.exam.Option;
//import org.ops4j.pax.exam.junit.Configuration;
//import org.ops4j.pax.exam.junit.ExamReactorStrategy;
//import org.ops4j.pax.exam.junit.JUnit4TestRunner;
//import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;
//
//import de.twenty11.skysail.common.testing.utils.PaxExamOptionSet;
//
///**
// * @author carsten
// * 
// */
//@RunWith(JUnit4TestRunner.class)
//@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
//public class SkysailServerExtDbviewerOsgiIT {
//
//    private List<PaxExamOptionSet> dependencies = new ArrayList<PaxExamOptionSet>();
//
//    @Configuration
//    public Option[] config() {
//
//        // dependencies.add(PaxExamOptionSet.BASE);
//        // dependencies.add(PaxExamOptionSet.DEBUGGING);
//        // dependencies.add(PaxExamOptionSet.SERVICES_VISUALIZATION);
//        //
//        // SkysailServerExtDbViewerOsgiSetup setup = new SkysailServerExtDbViewerOsgiSetup();
//        // List<Option> options = setup.getOptions(EnumSet.copyOf(dependencies));
//        //
//        // // _this_ bundle from target directory
//        // options.add(bundle("file:target/skysail.server.ext.osgimonitor-" + setup.getProjectVersion() + ".jar"));
//        //
//        // // options.add(systemProperty("org.osgi.service.http.port").value(
//        // // "8888" ));
//        // options.add(systemProperty("jetty.home.bundle").value("skysail.server"));
//        // options.add(systemProperty("ds.loglevel").value("4"));
//        //
//        // Option[] options2Use = options.toArray(new Option[options.size()]);
//        // setup.logOptionsUsed(options2Use);
//        return new Option[0];// options2Use;
//    }
//    
//    @Test
//    public void dummy() {
//    	
//    }
//
//  
// }
