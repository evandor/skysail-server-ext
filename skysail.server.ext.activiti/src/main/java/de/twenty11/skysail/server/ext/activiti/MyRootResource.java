package de.twenty11.skysail.server.ext.activiti;

import java.util.List;

import org.restlet.resource.Get;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.selfdescription.ResourceDetails;
import de.twenty11.skysail.common.selfdescription.RestfulRoot;
import de.twenty11.skysail.server.restlet.ListServerResource;

/**
 * Restlet Root Resource for dbViewer application.
 * 
 */
public class MyRootResource extends ListServerResource<ResourceDetails> implements RestfulRoot {

    public MyRootResource() {
        setAutoDescribing(false);
        setName("activiti root resource");
        setDescription("The root resource of the activiti application");
    }

    @Override
    @Get("html|json")
    public SkysailResponse<List<ResourceDetails>> getMethods() {
        return getEntities(allMethods(), "skysail activiti application (x.y.z-SNAPSHOT): listing all entry points");
    }

}