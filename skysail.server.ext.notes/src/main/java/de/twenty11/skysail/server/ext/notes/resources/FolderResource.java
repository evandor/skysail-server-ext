package de.twenty11.skysail.server.ext.notes.resources;

import org.restlet.resource.ResourceException;

import de.twenty11.skysail.server.core.restlet.UniqueResultServerResource2;
import de.twenty11.skysail.server.ext.notes.domain.Folder;

public class FolderResource extends UniqueResultServerResource2<Folder> {

    private String folderId;

    @Override
    protected void doInit() throws ResourceException {
        folderId = (String) getRequest().getAttributes().get("id");

    }

    @Override
    protected Folder getData() {
        return null;
    }

}
