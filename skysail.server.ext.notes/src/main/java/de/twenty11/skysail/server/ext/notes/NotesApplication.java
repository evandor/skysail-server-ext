package de.twenty11.skysail.server.ext.notes;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import de.twenty11.skysail.server.config.ServerConfiguration;
import de.twenty11.skysail.server.core.restlet.RouteBuilder;
import de.twenty11.skysail.server.ext.notes.domain.Folder;
import de.twenty11.skysail.server.ext.notes.domain.Note;
import de.twenty11.skysail.server.ext.notes.repos.ComponentRepository;
import de.twenty11.skysail.server.ext.notes.repos.FolderRepository;
import de.twenty11.skysail.server.ext.notes.repos.NotesRepository;
import de.twenty11.skysail.server.ext.notes.resources.AddFolderResource;
import de.twenty11.skysail.server.ext.notes.resources.AddNoteResource;
import de.twenty11.skysail.server.ext.notes.resources.FolderResource;
import de.twenty11.skysail.server.ext.notes.resources.FoldersResource;
import de.twenty11.skysail.server.ext.notes.resources.NoteResource;
import de.twenty11.skysail.server.ext.notes.resources.NotesResource;
import de.twenty11.skysail.server.ext.notes.resources.NotesRootResource;
import de.twenty11.skysail.server.restlet.SkysailApplication;
import de.twenty11.skysail.server.services.ApplicationProvider;
import de.twenty11.skysail.server.services.MenuEntry;
import de.twenty11.skysail.server.services.MenuProvider;

/**
 * The restlet application defined in this bundle.
 * 
 */
public class NotesApplication extends SkysailApplication implements ApplicationProvider, MenuProvider {

    private EntityManagerFactory enitityManagerFactory;
    private ComponentRepository<Folder> folderRepository;
    private ComponentRepository<Note> notesRepository;

    public NotesApplication() {
        setDescription("RESTful skysail.server.ext.notes bundle");
        setOwner("twentyeleven");
        setName("notes");
    }

    @Override
    protected void attach() {
        // make sure to match proper resource even if request url contains add. information
        // router.setDefaultMatchingMode(Template.MODE_STARTS_WITH);
        // router.setRoutingMode(Router.MODE_LAST_MATCH);

        // @formatter:off
        router.attach(new RouteBuilder("", NotesRootResource.class).setVisible(false));
        router.attach(new RouteBuilder("/notes", NotesResource.class).setVisible(false));
        router.attach(new RouteBuilder("/note", AddNoteResource.class).setVisible(false));
        router.attach(new RouteBuilder("/note/{id}", NoteResource.class).setVisible(false));
        router.attach(new RouteBuilder("/folders", FoldersResource.class).setVisible(false));
        router.attach(new RouteBuilder("/folder", AddFolderResource.class).setVisible(false));
        router.attach(new RouteBuilder("/folder/{id}", FolderResource.class).setVisible(false));
        // @formatter:on
    }

    public synchronized void setEntityManager(EntityManagerFactory emf) {
        this.enitityManagerFactory = emf;
    }

    @Override
    public void setServerConfiguration(ServerConfiguration sc) {
        super.setServerConfiguration(sc);
    }

    public synchronized ComponentRepository<Folder> getFolderRepository() {
        if (this.folderRepository == null) {
            this.folderRepository = new FolderRepository(enitityManagerFactory);
        }
        return this.folderRepository;
    }

    public synchronized ComponentRepository<Note> getNotesRepository() {
        if (this.notesRepository == null) {
            this.notesRepository = new NotesRepository(enitityManagerFactory);
        }
        return this.notesRepository;
    }

    @Override
    public List<MenuEntry> getMenuEntries() {
        return Arrays.asList(new MenuEntry("main", "Notes", "notes"));
    }

}
