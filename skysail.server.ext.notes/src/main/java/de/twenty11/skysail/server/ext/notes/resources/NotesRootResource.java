package de.twenty11.skysail.server.ext.notes.resources;

import java.util.Collections;
import java.util.List;

import org.restlet.resource.Get;

import de.twenty11.skysail.common.navigation.LinkedPage;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twenty11.skysail.server.ext.notes.domain.Component;

/**
 * Restlet Root Resource for dbViewer application.
 * 
 */
public class NotesRootResource extends ListServerResource2<Component> {

    public NotesRootResource() {
        setName("osgimonitor root resource");
        setDescription("The root resource of the osgimonitor application");
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
                return "notes/folder";
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
        return Collections.emptyList();
    }

}
