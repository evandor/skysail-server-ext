//package de.twenty11.skysail.server.ext.dbviewer;
//
//import java.util.List;
//
//import org.restlet.resource.Get;
//
//import de.twenty11.skysail.common.responses.SkysailResponse;
//import de.twenty11.skysail.server.restlet.ListServerResource;
//
///**
// * Restlet Root Resource for dbViewer application.
// * 
// */
//public class RootResource extends ListServerResource<ResourceDetails> {
//
//    public RootResource() {
//        setName("dbviewer root resource");
//        setDescription("The root resource of the dbviewer application");
//    }
//
//    @Override
//    @Get("html|json")
//    public SkysailResponse<List<ResourceDetails>> getMethods() {
//        return getEntities(allMethods(), "listing all entry points for the skysail dbViewer application");
//    }
// }
