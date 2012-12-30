package de.twenty11.skysail.server.ext.regprox;

import org.restlet.resource.Get;

import de.twenty11.skysail.common.selfdescription.ResourceDetails;
import de.twenty11.skysail.server.restlet.ListServerResource;

/**
 * Restlet Root Resource for regprox application.
 * 
 */
public class MyRootResource extends ListServerResource<ResourceDetails> {

    public MyRootResource() {
        setAutoDescribing(false);
        setName("regprox root resource");
        setDescription("The root resource of the regprox application");
    }
    
    @Get
    public String getDefaultPage() {
        return "hi";
    }


}
