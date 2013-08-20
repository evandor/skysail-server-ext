package de.twenty11.skysail.server.ext.notes.resources.test;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.restlet.Component;
import org.restlet.data.Protocol;

import de.twenty11.skysail.server.ResourceTestWithUnguardedAppication;
import de.twenty11.skysail.server.ext.notes.NotesApplication;
import de.twenty11.skysail.server.internal.SkysailComponent;

public class IntegrationTestBase extends ResourceTestWithUnguardedAppication<NotesApplication> {

    private static Component component = new SkysailComponent();

    @BeforeClass
    public static void init() throws Exception {
        component.getServers().add(Protocol.HTTP, TEST_PORT);
        component.start();
    }

    @AfterClass
    public static void stop() throws Exception {
        component.stop();
    }

    @Before
    public void setUp() {
        NotesApplication application = (NotesApplication) setUpApplication(new NotesApplication());
        application.setEntityManager(getEmfForTests("NotesPU"));
        component.getDefaultHost().attach(application);
    }

}
