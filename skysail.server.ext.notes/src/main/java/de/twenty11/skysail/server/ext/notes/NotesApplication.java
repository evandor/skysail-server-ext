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

    public static final String APP_NAME = "notes";
    public static final String FOLDERS_PATH = "/folders";
    public static final String FOLDERS_FORM_PATH = FOLDERS_PATH + "?media=htmlform";

    private EntityManagerFactory enitityManagerFactory;
    private ComponentRepository<Folder> folderRepository;
    private ComponentRepository<Note> notesRepository;

    public NotesApplication() {
        setDescription("RESTful skysail.server.ext.notes bundle");
        setOwner("twentyeleven");
        setName(APP_NAME);
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
        
        router.attach(new RouteBuilder(FOLDERS_PATH, FoldersResource.class).setVisible(false));
        //router.attach(new RouteBuilder(FOLDERS_PATH + "/", FolderResource.class).setVisible(false));
        router.attach(new RouteBuilder(FOLDERS_PATH + "/{id}", FolderResource.class).setVisible(false));
        
        // pattern: (path entity name always in plural):
        // "/folders"         XsResource    GET: all      POST: w/o id  PUT: with id DELETE: all OPTION: info
        // "/folders/"        XResource     GET: ?htmlform POST: w/o id  PUT: with id DELETE: all OPTION: info
        // "/folders/{id}     XResource     GET: single   POST: with id PUT: w/o id  DELETE: #id OPTION: info
        
        // FolderResource  GET     (htmlform, html, json, ...)
        //                 POST    with / w/o id
        //                 PUT     with / w/o id
        //                 DELETE  all / #id
        // FoldersResource GET     (..., ...)
        //                 POST    w/o id
        //                 PUT     w/o id
        //                 DELETE  all / #id
        
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

    public static String getPostNewFolderPath() {
        return "/" + APP_NAME + FOLDERS_PATH + "/";
    }
}
