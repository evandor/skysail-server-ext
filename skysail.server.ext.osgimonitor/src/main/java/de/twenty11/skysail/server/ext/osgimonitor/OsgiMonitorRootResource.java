package de.twenty11.skysail.server.ext.osgimonitor;

import java.util.List;

import org.restlet.resource.Get;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.selfdescription.ResourceDetails;
import de.twenty11.skysail.server.core.restlet.ListServerResource2;

/**
 * Restlet Root Resource for dbViewer application.
 * 
 */
public class OsgiMonitorRootResource<T extends ResourceDetails> extends ListServerResource2<ResourceDetails> {

    public OsgiMonitorRootResource() {
        setName("osgimonitor root resource");
        setDescription("The root resource of the osgimonitor application");
    }

    @Override
    @Get("html|json|csv")
    public SkysailResponse<List<ResourceDetails>> getEntities() {
        return getEntities("All entry points for osgimonitor");
    }

    @Override
    protected List<ResourceDetails> getData() {
        return allMethods();
    }

}
