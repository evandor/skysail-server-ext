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

import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.restlet.Component;
import org.restlet.data.LocalReference;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;
import org.restlet.routing.VirtualHost;
import org.restlet.security.SecretVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.directory.ClassLoaderDirectory;
import de.twenty11.skysail.server.directory.CompositeClassLoader;

/**
 * Concurrency note from parent class: instances of this class or its subclasses
 * can be invoked by several threads at the same time and therefore must be
 * thread-safe. You should be especially careful when storing state in member
 * variables.
 * 
 * @author carsten
 * 
 */
public class OsgiMonitorComponent extends Component {

	/** slf4j based log4jLogger implementation. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private OsgiMonitorViewerApplication application;

	private ServiceRegistration registration;

	/**
	 * @param componentContext
	 * 
	 */
	public OsgiMonitorComponent(ComponentContext componentContext,
			SecretVerifier verifier) {

		getClients().add(Protocol.CLAP);
		getClients().add(Protocol.HTTP);
		getClients().add(Protocol.FILE);
        // getClients().add(Protocol.WAR);

		// Create a restlet application
		logger.info("new restlet application: {}",
				OsgiMonitorViewerApplication.class.getName());
		application = new OsgiMonitorViewerApplication("/static",
				componentContext.getBundleContext());
		application.setVerifier(verifier);

		// Attach the application to the component and start it
		logger.info("attaching application and starting {}", this.toString());
		getDefaultHost().attachDefault(application);

		VirtualHost virtualHost = createVirtualHost();
		if (componentContext.getBundleContext() != null) {
			this.registration = componentContext.getBundleContext()
					.registerService("org.restlet.routing.VirtualHost",
							virtualHost, null);
		}
        // // String rootUri = "war:///js/"; // "file:///" + System.getProperty("user.home");
        // String rootUri = "clap://class/js/index.html";
        // Directory directory = new Directory(getContext(), rootUri);
        // directory.setListingAllowed(true);
        // getDefaultHost().attach("/js", directory);

        LocalReference localReference = LocalReference.createClapReference(LocalReference.CLAP_THREAD, "/static/");

        CompositeClassLoader customCL = new CompositeClassLoader();
        customCL.addClassLoader(Thread.currentThread().getContextClassLoader());
        customCL.addClassLoader(Router.class.getClassLoader());
        customCL.addClassLoader(this.getClass().getClassLoader());

        ClassLoaderDirectory staticDirectory = new ClassLoaderDirectory(getContext(), localReference, customCL);

        getDefaultHost().attach("/" + OsgiMonitorApplicationDescriptor.APPLICATION_NAME + "/static", staticDirectory);

	}

	private VirtualHost createVirtualHost() {
		VirtualHost vh = new VirtualHost();
		vh.setHostDomain("127.0.0.1");
		vh.setHostPort("2013");
		vh.attach(this);
		return vh;
	}

	public ServiceRegistration getRegistration() {
		return registration;
	}

	@Override
	public OsgiMonitorViewerApplication getApplication() {
		return this.application;
	}
}
