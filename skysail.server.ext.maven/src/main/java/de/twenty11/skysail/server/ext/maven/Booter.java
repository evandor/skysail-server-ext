package de.twenty11.skysail.server.ext.maven;

import org.apache.maven.repository.internal.DefaultServiceLocator;
import org.apache.maven.repository.internal.MavenRepositorySystemSession;
import org.sonatype.aether.RepositorySystem;
import org.sonatype.aether.RepositorySystemSession;
import org.sonatype.aether.connector.wagon.WagonProvider;
import org.sonatype.aether.connector.wagon.WagonRepositoryConnectorFactory;
import org.sonatype.aether.repository.LocalRepository;
import org.sonatype.aether.spi.connector.RepositoryConnectorFactory;

public class Booter {
	// public static RepositorySystem newRepositorySystem() {
	// return ManualRepositorySystemFactory.newRepositorySystem();
	// }
	//
	// public static RepositorySystemSession newRepositorySystemSession(
	// RepositorySystem system) {
	// MavenRepositorySystemSession session = new
	// MavenRepositorySystemSession();
	//
	// LocalRepository localRepo = new LocalRepository("target/local-repo");
	// //
	// session.setLocalRepositoryManager(system.newLocalRepositoryManager(localRepo));
	// //
	// // session.setTransferListener(new ConsoleTransferListener());
	// // session.setRepositoryListener(new ConsoleRepositoryListener());
	//
	// // uncomment to generate dirty trees
	// // session.setDependencyGraphTransformer( null );
	//
	// return session;
	// }
	//
	// public static RemoteRepository newCentralRepository() {
	// return new RemoteRepository.Builder("central", "default",
	// "http://repo1.maven.org/maven2/").build();
	// }

	static RepositorySystem newRepositorySystem() {
		DefaultServiceLocator locator = new DefaultServiceLocator();
		locator.setServices(WagonProvider.class, new ManualWagonProvider());
		locator.addService(RepositoryConnectorFactory.class,
				WagonRepositoryConnectorFactory.class);

		return locator.getService(RepositorySystem.class);
	}

	static RepositorySystemSession newSession(RepositorySystem system) {
		MavenRepositorySystemSession session = new MavenRepositorySystemSession();

		LocalRepository localRepo = new LocalRepository("target/local-repo");
		session.setLocalRepositoryManager(system
				.newLocalRepositoryManager(localRepo));

		return session;
	}
}
