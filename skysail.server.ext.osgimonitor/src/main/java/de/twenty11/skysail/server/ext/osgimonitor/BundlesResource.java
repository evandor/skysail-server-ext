package de.twenty11.skysail.server.ext.osgimonitor;

import java.util.Collections;
import java.util.List;

import org.restlet.resource.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.ext.osgimonitor.BundleDetails;
import de.twenty11.skysail.common.ext.osgimonitor.RestfulBundles;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.server.restlet.ListServerResource;

/**
 * Restlet Resource class for handling Connections.
 * 
 * Provides a method to retrieve the existing connections and to add a new one.
 * 
 * The managed entity is of type {@link BundleDetails}, providing details (like jdbc url, username
 * and password about what is needed to actually connect to a datasource.
 *
 */
public class BundlesResource extends ListServerResource<BundleDetails> implements RestfulBundles {

    /** slf4j based logger implementation */
    private static Logger logger = LoggerFactory.getLogger(BundlesResource.class);

    public BundlesResource() {
        setName("osgimonitor bundles resource");
        setDescription("The resource containing the list of bundles");
    }

    @Override
    @Get
    public Response<List<BundleDetails>> getBundles() {
        return getEntities(allBundles(), "all Bundles");
    }

    @SuppressWarnings("unchecked")
    private List<BundleDetails> allBundles() {
        return Collections.emptyList();
    }


}
