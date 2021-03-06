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

package de.twenty11.skysail.server.ext.inlinebrowser.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.RestletOsgiApplication;
import de.twenty11.skysail.server.listener.UrlMappingServiceListener;

/**
 * @author carsten
 *
 */
public class SkysailApplication extends RestletOsgiApplication {

    /** slf4j based logger implementation. */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private UrlMappingServiceListener urlMappingListener;

    public SkysailApplication() {
        //super(null);
    }

    protected void attach() {

        // TODO wrong place
        setBundleContext(Activator.getContext());

        // TODO wrong place
        urlMappingListener = new UrlMappingServiceListener(Activator.getContext(), this);
        
        // dynamic content
        logger.info ("attachting {} to '/'", ProductRootResource.class.getName());
        router.attach("/", ProductRootResource.class);

    }

}