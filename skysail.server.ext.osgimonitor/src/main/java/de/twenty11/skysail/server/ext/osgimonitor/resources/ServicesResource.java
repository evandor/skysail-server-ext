package de.twenty11.skysail.server.ext.osgimonitor.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twenty11.skysail.server.ext.osgimonitor.domain.ServiceDescriptor;
import de.twenty11.skysail.server.restlet.SkysailApplication;

/**
 * Restlet Resource for OSGi Services.
 * 
 * The managed entity is of type {@link ServiceDescriptor}.
 * 
 */
@Presentation(preferred = PresentationStyle.LIST2)
public class ServicesResource extends ListServerResource2<ServiceDescriptor> {

    private List<ServiceReference> services = Collections.emptyList();

    public ServicesResource() {
        setName("osgimonitor bundles resource");
        setDescription("The resource containing the list of bundles");
    }

    /**
     * initializes the services collection from the bundleContext. 
     */
    @Override
    protected void doInit() throws ResourceException {
        try {
            BundleContext bundleContext = ((SkysailApplication) getApplication()).getBundleContext();
            ServiceReference[] allServiceReferences = bundleContext.getAllServiceReferences(null, null);
            services = Arrays.asList(allServiceReferences);
        } catch (InvalidSyntaxException e) {
            throw new ResourceException(e);
        }
    }

    @Override
    @Get("html|json|csv")
    public SkysailResponse<List<ServiceDescriptor>> getEntities() {
        return getEntities("Listing of all services:");
    }

    @Override
    protected List<ServiceDescriptor> getData() {
        List<ServiceDescriptor> result = new ArrayList<ServiceDescriptor>();
        for (ServiceReference sr : services) {
            ServiceDescriptor descriptor = new ServiceDescriptor(sr, getReference());
            result.add(descriptor);
        }
        Collections.sort(result);
        return result;
    }

    @Post
    public Representation install(String location) {
        String prefix = "prefix";
        if (!location.startsWith(prefix)) {
            return new StringRepresentation("location didn't start with '" + prefix + "'");
        }
        return new StringRepresentation("success");
    }

}
