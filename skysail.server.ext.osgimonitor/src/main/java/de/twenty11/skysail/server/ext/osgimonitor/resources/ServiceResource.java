package de.twenty11.skysail.server.ext.osgimonitor.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.UniqueResultServerResource2;
import de.twenty11.skysail.server.ext.osgimonitor.OsgiMonitorViewerApplication;
import de.twenty11.skysail.server.ext.osgimonitor.domain.ServiceDescriptor;

public class ServiceResource extends UniqueResultServerResource2<ServiceDescriptor> {

    private Long serviceId;
    private List<ServiceReference> services = new ArrayList<ServiceReference>();

    @Override
    protected void doInit() throws ResourceException {
        serviceId = Long.valueOf((String) getRequest().getAttributes().get("serviceId"));
        OsgiMonitorViewerApplication app = (OsgiMonitorViewerApplication) getApplication();
        BundleContext bundleContext = app.getBundleContext();
        if (bundleContext != null) {
            try {
                ServiceReference[] allServiceReferences = bundleContext.getAllServiceReferences(null, null);
                services = Arrays.asList(allServiceReferences);
            } catch (InvalidSyntaxException e) {
                throw new ResourceException(e);
            }
        }
    }

    @Override
    @Get("html|json")
    public SkysailResponse<ServiceDescriptor> getEntity() {
        return getEntity("Details for service " + serviceId);
    }

    @Override
    protected ServiceDescriptor getData() {
        for (ServiceReference sr : services) {
            ServiceDescriptor serviceDescriptor = new ServiceDescriptor(sr, getReference());
            if (serviceDescriptor.getServiceId().equals(serviceId)) {
                return serviceDescriptor;
            }
        }
        return null;
    }

}
