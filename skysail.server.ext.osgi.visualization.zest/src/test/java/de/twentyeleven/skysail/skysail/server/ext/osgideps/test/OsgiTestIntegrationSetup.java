//package de.twentyeleven.skysail.skysail.server.ext.osgideps.test;
//
//import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
//
//import java.util.EnumSet;
//import java.util.List;
//
//import org.ops4j.pax.exam.Option;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import de.twenty11.skysail.common.osgi.PaxExamOptionSet;
//import de.twenty11.skysail.common.osgi.SkysailCommonOsgiSetup;
//import de.twenty11.skysail.server.integrationtest.SkysailServerOsgiSetup;
//
//public class OsgiTestIntegrationSetup extends SkysailServerOsgiSetup {
//
//    private static Logger logger = LoggerFactory.getLogger(SkysailCommonOsgiSetup.class.getName());
//
//    @Override
//    public List<Option> getOptions(EnumSet<PaxExamOptionSet> optionSets) {
//
//        List<Option> options = super.getOptions(optionSets);
//
//        options.add(mavenBundle("de.twentyeleven.skysail", "skysail.server", "0.2.5-SNAPSHOT"));
//
//        options.add(mavenBundle("de.twentyeleven.skysail", "net.sf.jgrapht-osgi", "0.8.3"));
//        options.add(mavenBundle("de.twentyeleven.skysail", "jgraphx-osgi", "1.10.3.1"));
//
//        logger.info("using options from {} for tests", this.getClass());
//
//        return options;
//    }
//
//}
