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

package de.twenty11.skysail.server.ext.osgimonitor;

import org.osgi.framework.BundleContext;
import org.restlet.Context;

import de.twenty11.skysail.server.core.restlet.RouteBuilder;
import de.twenty11.skysail.server.restlet.SkysailApplication;

/**
 * @author carsten
 * 
 */
public class OsgiMonitorViewerApplication extends SkysailApplication {

    // non-arg constructor needed for scr
    public OsgiMonitorViewerApplication() {
        this(null, null);
    }

    public OsgiMonitorViewerApplication(Context componentContext) {
        this(null, componentContext);
    }

    /**
     * @param staticPathTemplate
     * @param bundleContext
     */
    public OsgiMonitorViewerApplication(BundleContext bundleContext, Context componentContext) {
        super(componentContext == null ? null : componentContext.createChildContext());
        setDescription("RESTful OsgiMonitor bundle");
        setOwner("twentyeleven");
        setName("osgimonitor");
        setBundleContext(bundleContext);
    }

    protected void attach() {
        // @formatter:off
        router.attach(new RouteBuilder("", OsgiMonitorRootResource.class).setVisible(false));
        router.attach(new RouteBuilder("/", OsgiMonitorRootResource.class).setVisible(false));
        router.attach(new RouteBuilder("/bundles", BundlesResource.class).setText("Bundles"));
        router.attach(new RouteBuilder("/bundles/asGraph", IFrameResource.class).setText("Bundles as visualized Graph"));
        router.attach(new RouteBuilder("/bundles/asGraph/", BundlesAsGraphResource.class).setText("Json Graph representation"));
        router.attach(new RouteBuilder("/bundles/asGraph/d3Simple", BundlesAsD3GraphResource.class).setVisible(false));
        router.attach(new RouteBuilder("/bundles/details/{bundleId}", BundleResource.class).setVisible(false));
        router.attach(new RouteBuilder("/bundles/details/{bundleId}/action", BundleResource.class).setVisible(false));
        router.attach(new RouteBuilder("/bundles/details/{bundleId}/headers", HeaderResource.class).setVisible(false));
        router.attach(new RouteBuilder("/services", ServicesResource.class).setText("Services"));
        // @formatter:on
    }

}