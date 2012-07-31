package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.ops4j.pax.exam.CoreOptions.mavenBundle;

import java.util.EnumSet;
import java.util.List;

import org.ops4j.pax.exam.Option;

import de.twenty11.skysail.common.osgi.PaxExamOptionSet;
import de.twenty11.skysail.server.integrationtest.SkysailServerOsgiSetup;

public class SkysailServerExtDbViewerOsgiSetup extends SkysailServerOsgiSetup {

    @Override
	public List<Option> getOptions(EnumSet<PaxExamOptionSet> optionSets) {
        
        List<Option> options = super.getOptions(optionSets);
        
        options.add(mavenBundle("de.twentyeleven.skysail", "skysail.server", "0.2.1-SNAPSHOT"));
        
        return options;
	}
   

}
