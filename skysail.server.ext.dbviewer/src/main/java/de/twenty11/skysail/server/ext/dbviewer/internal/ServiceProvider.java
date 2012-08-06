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

package de.twenty11.skysail.server.ext.dbviewer.internal;

import org.osgi.service.component.ComponentContext;
import org.restlet.Request;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.MediaType;
import org.restlet.data.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.internal.ConfigServiceProvider;
import de.twenty11.skysail.server.services.ConfigService;


public class ServiceProvider {

    private static ConfigService configService;
	/** slf4j based logger implementation */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
	private DbViewerComponent dbViewerComponent;
	private Server server;

    protected void activate(ComponentContext ctxt) {
        //logValServiceListener = new LogValidationInputProviderServiceListener(ctxt.getBundleContext());
    	
//    	server = new Server(Protocol.HTTP, 8554, new Restlet() {
//			@Override
//			public void handle(Request request, org.restlet.Response response) {
//				response.setEntity("Hello world!", MediaType.TEXT_PLAIN);
//			}
//		});
//
//		try {
//			server.start();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//    	
    	
    	dbViewerComponent = new DbViewerComponent();
    	try {
    		server = new Server(Protocol.HTTP, 8554, dbViewerComponent);
			server.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    protected void deactivate(ComponentContext ctxt) {
        //logValServiceListener = null;
    	try {
			server.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public synchronized void setConfigService(ConfigService configService) {
    	ServiceProvider.configService = configService;
    }
    
    public static ConfigService getConfigService() {
        return configService;
    }
    
    


}
