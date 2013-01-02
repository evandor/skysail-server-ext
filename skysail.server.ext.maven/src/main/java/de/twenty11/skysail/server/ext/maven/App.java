package de.twenty11.skysail.server.ext.maven;

import org.sonatype.aether.RepositorySystem;
import org.sonatype.aether.RepositorySystemSession;
import org.sonatype.aether.collection.CollectRequest;
import org.sonatype.aether.graph.Dependency;
import org.sonatype.aether.graph.DependencyNode;
import org.sonatype.aether.repository.RemoteRepository;
import org.sonatype.aether.resolution.DependencyRequest;
import org.sonatype.aether.util.artifact.DefaultArtifact;
import org.sonatype.aether.util.graph.PreorderNodeListGenerator;

public class App {

	public static void main(String[] args) throws Exception {

		RepositorySystem repoSystem = Booter.newRepositorySystem();
		RepositorySystemSession session = Booter.newSession(repoSystem);

		Dependency dependency = new Dependency(new DefaultArtifact(
				"org.apache.maven:maven-profile:2.2.1"), "compile");
		RemoteRepository central = new RemoteRepository("central", "default",
				"http://repo1.maven.org/maven2/");

		CollectRequest collectRequest = new CollectRequest();
		collectRequest.setRoot(dependency);
		collectRequest.addRepository(central);
		DependencyNode node = repoSystem.collectDependencies(session,
				collectRequest).getRoot();

		DependencyRequest dependencyRequest = new DependencyRequest(node, null);

		repoSystem.resolveDependencies(session, dependencyRequest);

		PreorderNodeListGenerator nlg = new PreorderNodeListGenerator();
		node.accept(nlg);
		System.out.println(nlg.getClassPath());
	}

}
