package de.twenty11.skysail.server.ext.notes.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.restlet.data.Form;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.common.navigation.LinkedPage;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twenty11.skysail.server.ext.notes.NotesApplication;
import de.twenty11.skysail.server.ext.notes.domain.Component;
import de.twenty11.skysail.server.ext.notes.domain.Folder;
import de.twenty11.skysail.server.ext.notes.domain.Note;
import de.twenty11.skysail.server.ext.notes.repos.ComponentRepository;

/**
 * Restlet Root Resource for dbViewer application.
 * 
 */
@Presentation(preferred = PresentationStyle.LIST2)
public class NotesRootResource extends ListServerResource2<Component> {

    public NotesRootResource() {
        setName("osgimonitor root resource");
        setDescription("The root resource of the osgimonitor application");
    }

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
    }

    @Override
    @Get("html|json|csv")
    public SkysailResponse<List<Component>> getEntities() {
        registerLinkedPage(new LinkedPage() {

            @Override
            public String getLinkText() {
                return "new Folder";
            }

            @Override
            public String getHref() {
                return "notes" + NotesApplication.FOLDERS_FORM_PATH;
            }

            @Override
            public boolean applicable() {
                return true;
            }
        });
        registerLinkedPage(new LinkedPage() {

            @Override
            public String getLinkText() {
                return "new Note";
            }

            @Override
            public String getHref() {
                return "notes/notes?media=htmlform";
            }

            @Override
            public boolean applicable() {
                return true;
            }
        });
        registerLinkedPage(new LinkedPage() {

            @Override
            public String getLinkText() {
                return "Notes";
            }

            @Override
            public String getHref() {
                return "notes/notes";
            }

            @Override
            public boolean applicable() {
                return true;
            }
        });
        registerLinkedPage(new LinkedPage() {

            @Override
            public String getLinkText() {
                return "Folders";
            }

            @Override
            public String getHref() {
                return "notes/folders";
            }

            @Override
            public boolean applicable() {
                return true;
            }
        });

        return getEntities("Folders and Notes");
    }

    @Override
    protected List<Component> getData() {
        NotesApplication app = (NotesApplication) getApplication();
        List<Component> result = new ArrayList<Component>();
        addFolders(result, app);
        addNotes(result, app);
        return result;
    }

    private void addFolders(List<Component> result, NotesApplication app) {
        ComponentRepository<Folder> folderRepository = app.getFolderRepository();
        List<Folder> folders = folderRepository.getComponents();
        Collections.sort(folders);
        for (Folder folder : folders) {
            result.add(folder);
        }
    }

    private void addNotes(List<Component> result, NotesApplication app) {
        ComponentRepository<Note> notesRepository = app.getNotesRepository();
        List<Note> notes = notesRepository.getComponents();
        // Collections.sort(notes);
        for (Note note : notes) {
            result.add(note);
        }
    }

    @Override
    public Component getData(Form form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SkysailResponse<?> addEntity(Component entity) {
        // TODO Auto-generated method stub
        return null;
    }

}
