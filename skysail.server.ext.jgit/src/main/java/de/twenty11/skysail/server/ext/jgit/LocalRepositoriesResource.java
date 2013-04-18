package de.twenty11.skysail.server.ext.jgit;

import de.twenty11.skysail.server.restlet.ListServerResource2;
import org.restlet.resource.ServerResource;

import java.util.List;

public class LocalRepositoriesResource extends ListServerResource2<LocalRepositoryDescriptor> {

    @Override
    protected List<LocalRepositoryDescriptor> getData() {
        return null;
    }
}
