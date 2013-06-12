package de.twenty11.skysail.server.ext.notes.resources;

import java.util.List;

import org.restlet.resource.Get;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.selfdescription.ResourceDetails;
import de.twenty11.skysail.server.core.restlet.ListServerResource2;

/**
 * Restlet Root Resource for dbViewer application.
 * 
 */
public class FacebookRootResource extends ListServerResource2<ResourceDetails> {

    public FacebookRootResource() {
        setName("osgimonitor root resource");
        setDescription("The root resource of the osgimonitor application");
    }

    @Override
    @Get("html|json|csv")
    public SkysailResponse<List<ResourceDetails>> getEntities() {
        return getEntities("All entry points for facebook");
    }

    @Override
    protected List<ResourceDetails> getData() {
        return allMethods();
    }

}
