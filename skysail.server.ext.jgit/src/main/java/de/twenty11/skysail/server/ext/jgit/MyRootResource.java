package de.twenty11.skysail.server.ext.jgit;

import java.util.List;

import org.restlet.resource.Get;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.selfdescription.ResourceDetails;
import de.twenty11.skysail.server.restlet.ListServerResource2;

/**
 * Restlet Root Resource for dbViewer application.
 * 
 */
public class MyRootResource extends ListServerResource2<ResourceDetails> {

    public MyRootResource() {
        setAutoDescribing(false);
        setName("jgit root resource");
        setDescription("The root resource of the jgit application");
    }

    @Override
    @Get("html|json|csv")
    public SkysailResponse<List<ResourceDetails>> getEntities() {
        return getEntities("all methods");
    }

    @Override
    protected List<ResourceDetails> getData() {
        return allMethods();
    }

}
