package de.twenty11.skysail.server.ext.notes;

import org.jbehave.core.annotations.BeforeStory;
import org.restlet.Component;
import org.restlet.data.Protocol;

import de.twenty11.skysail.server.ResourceTestWithUnguardedAppication;
import de.twenty11.skysail.server.internal.SkysailComponent;

public class AcceptanceTests extends ResourceTestWithUnguardedAppication<NotesApplication> {

    private NotesApplication application;

    private static Component component = new SkysailComponent();

    static {
        component.getServers().add(Protocol.HTTP, TEST_PORT);
        try {
            component.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @AfterStories
    // public void stop() throws Exception {
    // component.stop();
    // }

    @BeforeStory
    public void setUp() {
        application = (NotesApplication) setUpApplication(new NotesApplication());
        application.setEntityManager(getEmfForTests("NotesPU"));
        component.getDefaultHost().attach(application);
    }

}
