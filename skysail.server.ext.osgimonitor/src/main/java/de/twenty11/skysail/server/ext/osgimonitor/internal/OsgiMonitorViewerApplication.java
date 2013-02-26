/**
 *  Copyright 2011 Carsten Gräf
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * 
 */

package de.twenty11.skysail.server.ext.osgimonitor.internal;

import org.osgi.framework.BundleContext;
import org.restlet.Request;
import org.restlet.Response;

import de.twenty11.skysail.server.ext.osgimonitor.BundleResource;
import de.twenty11.skysail.server.ext.osgimonitor.BundlesAsGraphResource;
import de.twenty11.skysail.server.ext.osgimonitor.BundlesAsJsGraphResource;
import de.twenty11.skysail.server.ext.osgimonitor.BundlesResource;
import de.twenty11.skysail.server.ext.osgimonitor.OsgiMonitorRootResource;
import de.twenty11.skysail.server.ext.osgimonitor.ServicesResource;
import de.twenty11.skysail.server.restlet.SkysailApplication;

/**
 * @author carsten
 * 
 */
public class OsgiMonitorViewerApplication extends SkysailApplication {

    // non-arg constructor needed for scr
    public OsgiMonitorViewerApplication() {
        this(null);
    }

    /**
     * @param staticPathTemplate
     * @param bundleContext
     */
    public OsgiMonitorViewerApplication(BundleContext bundleContext) {
        super(OsgiMonitorApplicationDescriptor.APPLICATION_NAME);
        setDescription("RESTful OsgiMonitor bundle");
        setOwner("twentyeleven");
        setName("osgimonitor");
        setBundleContext(bundleContext);
    }

    @Override
    public void handle(Request request, Response response) {
        super.handle(request, response);
    }

    protected void attach() {
    	router.attach("/", OsgiMonitorRootResource.class);
    	router.attach("/bundles", BundlesResource.class);
    	router.attach("/bundles/asGraph", BundlesAsGraphResource.class);
    	router.attach("/bundles/asJsGraph", BundlesAsJsGraphResource.class);
    	router.attach("/bundles/details/{bundleId}", BundleResource.class);
    	router.attach("/bundles/details/{bundleId}/{action}", BundleResource.class);
    	router.attach("/services", ServicesResource.class);
    }

}
