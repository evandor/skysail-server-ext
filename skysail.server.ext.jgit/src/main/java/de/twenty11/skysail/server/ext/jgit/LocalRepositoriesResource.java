package de.twenty11.skysail.server.ext.jgit;

import java.util.List;

import org.restlet.resource.Get;

import de.twenty11.skysail.common.navigation.LinkedPage;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.jgit.internal.DbRepository;
import de.twenty11.skysail.server.ext.jgit.internal.MyApplication;
import de.twenty11.skysail.server.restlet.ListServerResource2;

public class LocalRepositoriesResource extends ListServerResource2<LocalRepositoryDescriptor> {

    @Override
    @Get("html|json|csv")
    public SkysailResponse<List<LocalRepositoryDescriptor>> getEntities() {
        return getEntities("all jgit repository locations");
    }

    @Override
    protected List<LocalRepositoryDescriptor> getData() {
        registerLinkedPage(new LinkedPage() {

            @Override
            public String getLinkText() {
                return "add new local repository";
            }

            @Override
            public String getHref() {
                return "repos/";
            }

            @Override
            public boolean applicable() {
                return true;
            }
        });
        MyApplication app = (MyApplication) getApplication();
        DbRepository rep = app.getRepository();
        return rep.getLocalRepositoryDescriptors();
    }
}
