package de.twenty11.skysail.server.ext.notes;

import javax.persistence.EntityManagerFactory;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;
import org.jbehave.core.annotations.BeforeStory;
import org.restlet.Component;
import org.restlet.data.Protocol;

import de.twenty11.skysail.server.ResourceTestWithUnguardedAppication;
import de.twenty11.skysail.server.internal.SkysailComponent;
import de.twenty11.skysail.server.um.domain.SkysailUser;
import de.twenty11.skysail.server.um.repos.UserRepository;
import de.twenty11.skysail.server.um.services.UserManager;

public class AcceptanceTests extends ResourceTestWithUnguardedAppication<NotesApplication> {

    protected static Component component = new SkysailComponent();

    private UserManager userManagerForTests;

    static {
        component.getServers().add(Protocol.HTTP, TEST_PORT);
        try {
            component.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class UserManagerForTests implements UserManager {

        private UserRepository userRepository;

        public UserManagerForTests(EntityManagerFactory enitityManagerFactory) {
            userRepository = new UserRepository(enitityManagerFactory);
        }

        @Override
        public SkysailUser findByUsername(String username) {
            return userRepository.getByName(username);
        }

    }

    @BeforeStory
    public void setUp() {

        de.twenty11.skysail.server.um.db.FlywaySetup flywaySetupUm = new de.twenty11.skysail.server.um.db.FlywaySetup();
        flywaySetupUm.setEntityManager(getEmfForTests("NotesPU"));
        flywaySetupUm.setLocation("dbmig/server_um/derby");
        flywaySetupUm.init();

        de.twenty11.skysail.server.ext.notes.db.FlywaySetup flywaySetupNotes = new de.twenty11.skysail.server.ext.notes.db.FlywaySetup();
        flywaySetupNotes.setEntityManager(getEmfForTests("NotesPU"));
        flywaySetupNotes.setLocation("dbmig/server_ext_notes/derby");
        flywaySetupNotes.init();

        userManagerForTests = new UserManagerForTests(getEmfForTests("NotesPU"));

        application = (NotesApplication) setUpApplication(new NotesApplication());
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        application.setEntityManager(getEmfForTests("NotesPU"));
        // UserManager userManager = Mockito.mock(UserManager.class);
        // Mockito.when(userManager.findByUsername(org.mockito.Matchers.anyString())).thenReturn(new SkysailUser());

        application.setUserManager(userManagerForTests);
        component.getDefaultHost().attach(application);
    }
}
