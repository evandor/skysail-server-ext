package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.ops4j.pax.exam.CoreOptions.mavenBundle;

import java.util.EnumSet;
import java.util.List;

import org.ops4j.pax.exam.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.osgi.PaxExamOptionSet;
import de.twenty11.skysail.common.osgi.SkysailCommonOsgiSetup;
import de.twenty11.skysail.server.integrationtest.SkysailServerOsgiSetup;

public class SkysailServerExtDbViewerOsgiSetup extends SkysailServerOsgiSetup {

    private static Logger logger = LoggerFactory.getLogger(SkysailCommonOsgiSetup.class.getName());

    @Override
    public List<Option> getOptions(EnumSet<PaxExamOptionSet> optionSets) {

        List<Option> options = super.getOptions(optionSets);

        options.add(mavenBundle("de.twentyeleven.skysail", "skysail.server"));

        // restassured:
        options.add(mavenBundle("de.twentyeleven.skysail", "com.jayway.rest-assured-osgi", "1.6.2"));
        options.add(mavenBundle("de.twentyeleven.skysail", "org.hamcrest.hamcrest-all-osgi", "1.3.0.1"));
        options.add(mavenBundle("de.twentyeleven.skysail", "org.ccil.cowan.tagsoup-osgi", "1.2.1"));
        options.add(mavenBundle("commons-collections", "commons-collections", "3.2.1"));
        options.add(mavenBundle("org.apache.commons", "commons-lang3", "3.1"));
        options.add(mavenBundle("org.apache.httpcomponents", "httpcore-osgi", "4.1.4"));
        options.add(mavenBundle("org.apache.httpcomponents", "httpclient-osgi", "4.1.3"));
        options.add(mavenBundle("org.codehaus.groovy", "groovy-all", "1.8.4"));
        options.add(mavenBundle("org.codehaus.jackson", "jackson-core-lgpl", "1.9.5"));

        // mysql
        options.add(mavenBundle("mysql","skysail.bundles.mysql-connector-java","5.1.6"));
        
        // felix config admin
        options.add(mavenBundle("org.apache.felix", "org.apache.felix.configadmin", "1.4.0"));

        options.add(mavenBundle("commons-dbcp", "skysail.bundles.commons-dbcp", "1.4"));

        logger.info("using options from {} for tests", this.getClass());

        return options;
    }

}
