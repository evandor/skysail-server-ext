package de.twenty11.skysail.server.ext.regprox;

import org.restlet.data.MediaType;
import org.restlet.representation.Variant;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Restlet Root Resource for regprox application.
 * 
 */
public class MyRootResource extends ServerResource {

    public MyRootResource() {
        // setAutoDescribing(false);
        // setName("regprox root resource");
        // setDescription("The root resource of the regprox application");
        getVariants().add(new Variant(MediaType.TEXT_HTML));
    }
    
    @Get
    public String getDefaultPage() {
        return "hi";
    }


}
