package de.twenty11.skysail.server.ext.maven;

import org.apache.maven.wagon.Wagon;
import org.sonatype.aether.connector.wagon.WagonProvider;


public class ManualWagonProvider implements WagonProvider {

	public Wagon lookup(String roleHint) throws Exception {
		if ("http".equals(roleHint)) {
			return new org.sonatype.maven.wagon.AhcWagon();
		}
		return null;
	}

	public void release(Wagon wagon) {

	}

}