package de.twenty11.skysail.server.ext.jgit;

import java.util.List;

import de.twenty11.skysail.server.ext.jgit.internal.DbRepository;
import de.twenty11.skysail.server.ext.jgit.internal.MyApplication;
import de.twenty11.skysail.server.restlet.ListServerResource2;

public class LocalRepositoriesResource extends ListServerResource2<LocalRepositoryDescriptor> {

    @Override
    protected List<LocalRepositoryDescriptor> getData() {
        MyApplication app = (MyApplication) getApplication();
        DbRepository rep = app.getRepository();
        return rep.getLocalRepositoryDescriptors();
    }
}
