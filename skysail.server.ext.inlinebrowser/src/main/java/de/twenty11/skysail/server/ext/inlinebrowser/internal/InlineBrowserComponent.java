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

import org.restlet.Component;
import org.restlet.data.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.listener.UrlMappingServiceListener;

/**
 * Concurrency note from parent class: instances of this class or its subclasses
 * can be invoked by several threads at the same time and therefore must be
 * thread-safe. You should be especially careful when storing state in member
 * variables.
 * 
 * @author carsten
 * 
 */
public class InlineBrowserComponent extends Component {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public InlineBrowserComponent() {
        //getServers().add(Protocol.HTTP, port);
        getClients().add(Protocol.CLAP);

        // Create a restlet application
        logger.info("new restlet application: {}", SkysailApplication.class.getName());
        final SkysailApplication application = new SkysailApplication();
        //application.setBundleContext(ctxt.getBundleContext());
        
        // Attach the application to the component and start it
        logger.info("attaching application and starting {}", this.toString());
        getDefaultHost().attachDefault(application);
       // start();

        // ServiceListener for UrlMappings, to be unregistered when bundle is stopped
        logger.info("adding listener {} ", UrlMappingServiceListener.class.getName());
        //urlMappingListener = new UrlMappingServiceListener(ctxt.getBundleContext(), application);
    
    }

}
