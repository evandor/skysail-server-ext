package de.twenty11.skysail.server.ext.maven;

import java.util.List;

import org.restlet.Restlet;
import org.restlet.resource.Finder;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Route;
import org.restlet.routing.TemplateRoute;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.selfdescription.ResourceDetails;
import de.twenty11.skysail.common.selfdescription.RestfulRoot;
import de.twenty11.skysail.server.restlet.ListServerResource;
import de.twenty11.skysail.server.restlet.SkysailServerResource2;

/**
 * Restlet Root Resource for dbViewer application.
 * 
 */
public class MyRootResource extends ListServerResource<ResourceDetails> implements RestfulRoot {

    public MyRootResource() {
        setAutoDescribing(false);
        setName("osgimonitor root resource");
        setDescription("The root resource of the osgimonitor application");
    }
    
    @Override
    @Get
    public SkysailResponse<List<ResourceDetails>> getMethods() {
        return getEntities(allMethods(), "listing all entry points for the skysail osgimonitor application");
    }

    private void handleTemplateRoutes(List<ResourceDetails> result, Route route) {
        TemplateRoute tr = (TemplateRoute) route;
        String from = (tr.getTemplate() == null) ? super.toString() : tr.getTemplate().getPattern();
        
        if (!from.contains("{")) { // some link we can acutally follow
            from = getHostRef() + from + "?media=json";
            String to = (tr.getNext() == null) ? "null" : tr.getNext().toString();
            String desc = "no description available";
            if (tr.getNext() != null) {
                Restlet next = tr.getNext();
                if (next instanceof Finder) {
                    Finder finder = (Finder) next;
                    Class<? extends ServerResource> targetClass = finder.getTargetClass();
                    ServerResource serverResource;
                    try {
                        serverResource = targetClass.newInstance();
                        if (serverResource instanceof SkysailServerResource2<?>) {
                            SkysailServerResource2<?> ssr = (SkysailServerResource2<?>) serverResource;
                            desc = ssr.getDescription();
                        }
                    } catch (Exception e) {
                    }
                    next.getFinderClass();
                }
            }
            ResourceDetails resourceDetails = new ResourceDetails(from, from, to, desc);// + " -> " + to);
            result.add(resourceDetails);
        }
    }
}
