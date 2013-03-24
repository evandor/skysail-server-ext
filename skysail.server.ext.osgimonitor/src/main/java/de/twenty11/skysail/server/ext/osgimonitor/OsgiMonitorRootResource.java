package de.twenty11.skysail.server.ext.osgimonitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.restlet.resource.Get;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.selfdescription.ResourceDetails;
import de.twenty11.skysail.common.selfdescription.RestfulRoot;
import de.twenty11.skysail.server.restlet.ListServerResource;
import de.twenty11.skysail.server.restlet.RouteBuilder;
import de.twenty11.skysail.server.restlet.SkysailApplication;

/**
 * Restlet Root Resource for dbViewer application.
 * 
 */
public class OsgiMonitorRootResource extends ListServerResource<ResourceDetails> implements RestfulRoot {

    public OsgiMonitorRootResource() {
        setAutoDescribing(false);
        setName("osgimonitor root resource");
        setDescription("The root resource of the osgimonitor application");
    }

    @Override
    @Get("html|json")
    public SkysailResponse<List<ResourceDetails>> getMethods() {
        return getEntities(allMethods(), "skysail osgimonitor application (x.y.z-SNAPSHOT): listing all entry points");
    }

    private List<ResourceDetails> allMethods() {
        List<ResourceDetails> result = new ArrayList<ResourceDetails>();
        SkysailApplication restletOsgiApp = (SkysailApplication) getApplication();
        Map<String, RouteBuilder> skysailRoutes = restletOsgiApp.getSkysailRoutes();
        for (Entry<String, RouteBuilder> entry : skysailRoutes.entrySet()) {
            handleSkysailRoute(result, entry);
        }
        return result;
    }

    private void handleSkysailRoute(List<ResourceDetails> result, Entry<String, RouteBuilder> entry) {
        RouteBuilder builder = entry.getValue();
        if (builder.isVisible()) {
            String from = getHostRef() + "/" + getApplication().getName() + entry.getKey();
            String text = builder.getText() != null ? builder.getText() : from;
            ResourceDetails resourceDetails = new ResourceDetails(from, text, builder.getTargetClass()
                    .toString(), "desc");
            result.add(resourceDetails);
        }
    }

}
