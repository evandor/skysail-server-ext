package de.twenty11.skysail.server.ext.notes.integrationtests;

import static org.ops4j.pax.exam.CoreOptions.bootDelegationPackage;
import static org.ops4j.pax.exam.CoreOptions.bundle;
import static org.ops4j.pax.exam.CoreOptions.cleanCaches;
import static org.ops4j.pax.exam.CoreOptions.felix;
import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.options;
import static org.ops4j.pax.exam.CoreOptions.provision;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;
import static org.ops4j.pax.tinybundles.core.TinyBundles.bundle;

import java.io.InputStream;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.twenty11.skysail.server.ext.notes.Folder;
import de.twenty11.skysail.server.ext.notes.NotesFactory;
import de.twenty11.skysail.server.ext.notes.impl.NotesFactoryImpl;
import de.twenty11.skysail.server.ext.notes.impl.NotesPackageImpl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;

@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class FolderIntegrationTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    private NotesFactory notesFactory;
    private EntityManager em;

    @Inject
    private BundleContext context;

    @Configuration
    public final Option[] config() {

        InputStream bundleUnderTest = bundle().add(NotesFactory.class)
                .set(Constants.BUNDLE_SYMBOLICNAME, "skysail.server.ext.notes")
                .set(Constants.EXPORT_PACKAGE, "de.twenty11.skysail.server.ext.notes")
                .set(Constants.DYNAMICIMPORT_PACKAGE, "*")
                .set(Constants.IMPORT_PACKAGE, "de.twenty11.skysail.server.ext.notes, org.eclipse.emf.ecore")
                .build();

        // @formatter:off
        return options(
                bootDelegationPackage( "sun.*" ),
                cleanCaches(),
                provision(bundleUnderTest),
                //bundle("mvn:http://80.86.88.102:8081/nexus/content/groups/public!org.eclipse.core/core.runtime/3.8.0.v20120308-2101"),
                bundle("http://80.86.88.102:8081/nexus/content/groups/public/org/eclipse/core/core.runtime/3.8.0.v20120308-2101/core.runtime-3.8.0.v20120308-2101.jar"),
                bundle("http://80.86.88.102:8081/nexus/content/groups/public/org/eclipse/emf/emf/2.6.0.v20120601-0824/emf-2.6.0.v20120601-0824.jar"),
                bundle("http://80.86.88.102:8081/nexus/content/groups/public/org/eclipse/osgi/org.eclipse.osgi/3.8.0.v20120529-1548/org.eclipse.osgi-3.8.0.v20120529-1548.jar"),
                //bundle("http://80.86.88.102:8081/nexus/content/groups/public/org/eclipse/equinox/org.eclipse.equinox.common/3.6.0.v20100503/org.eclipse.equinox.common-3.6.0.v20100503.jar"),
                bundle("http://80.86.88.102:8081/nexus/content/groups/public/org/eclipse/equinox/equinox.common/3.6.100.v20120522-1841/equinox.common-3.6.100.v20120522-1841.jar"),
                bundle("http://80.86.88.102:8081/nexus/content/groups/public/org/eclipse/equinox/org.eclipse.equinox.log/1.2.100.v20100503/org.eclipse.equinox.log-1.2.100.v20100503.jar"),
                bundle("http://80.86.88.102:8081/nexus/content/groups/public/org/eclipse/core/org.eclipse.core.jobs/3.5.0.v20100515/org.eclipse.core.jobs-3.5.0.v20100515.jar"),
                bundle("http://80.86.88.102:8081/nexus/content/groups/public/org/eclipse/equinox/registry/org.eclipse.equinox.registry/3.5.200.v20120522-1841/org.eclipse.equinox.registry-3.5.200.v20120522-1841.jar"),
                bundle("http://80.86.88.102:8081/nexus/content/groups/public/org/eclipse/equinox/preferences/org.eclipse.equinox.preferences/3.5.0.v20120522-1841/org.eclipse.equinox.preferences-3.5.0.v20120522-1841.jar"),
                bundle("http://80.86.88.102:8081/nexus/content/groups/public/org/eclipse/core/contenttype/org.eclipse.core.contenttype/3.4.200.v20120523-2004/org.eclipse.core.contenttype-3.4.200.v20120523-2004.jar"),
                bundle("http://80.86.88.102:8081/nexus/content/groups/public/org/eclipse/equinox/app/org.eclipse.equinox.app/1.3.100.v20120522-1841/org.eclipse.equinox.app-1.3.100.v20120522-1841.jar"),
                mavenBundle("org.eclipse.emf","ecore","2.8.0.v20120516"),
                mavenBundle("org.eclipse.emf","common","2.8.0.v20120516"),
//                mavenBundle("mysql","skysail.bundles.mysql-connector","5.1.6"),
                junitBundles(),
                //vmOption("-consoleLog"),
                systemProperty("osgi.console").value("6666"),
                systemProperty("equinox.ds.debug").value("true"),
                systemProperty("equinox.ds.print").value("true"),
                //equinox().version("3.6.2")
                felix().version("3.2.2")
        );
        // @formatter:on
    }

    @Before
    public void setUp() throws Exception {
        NotesPackageImpl.init();
        notesFactory = NotesFactoryImpl.eINSTANCE;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("skysail.server.ext.notes");
        em = factory.createEntityManager();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveNewFolderSuccessfully() {
        Folder folder = notesFactory.createFolder();
        em.persist(folder);
    }


}
