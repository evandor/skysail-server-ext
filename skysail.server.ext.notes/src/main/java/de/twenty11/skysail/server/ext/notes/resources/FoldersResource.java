package de.twenty11.skysail.server.ext.notes.resources;

import java.util.List;

import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twenty11.skysail.server.ext.notes.domain.Component;

public class FoldersResource extends ListServerResource2<Component> {

    @Override
    protected List<Component> getData() {
        return null;
    }

}
