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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.ext.dbviewer.internal.entities.ConnectionDetails;
import de.twenty11.skysail.server.listener.UrlMappingServiceListener;
import de.twenty11.skysail.server.restlet.RestletOsgiApplication;

/**
 * @author carsten
 * 
 */
public class SkysailApplication extends RestletOsgiApplication {

    private Connections connections;

    /** slf4j based logger implementation. */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @param staticPathTemplate
     */
    public SkysailApplication(String staticPathTemplate) {
        super(staticPathTemplate);
        connections = new Connections();
        ConnectionDetails defaultConnectionDetails = new ConnectionDetails("default", "root", "websphere",
                "jdbc:mysql://localhost/skysailosgi", "com.mysql.jdbc.Driver");
        connections.add(defaultConnectionDetails);
    }

    public Connections getConnections() {
        return connections;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.twenty11.skysail.server.RestletOsgiApplication#attach()
     */
    protected void attach() {
        new UrlMappingServiceListener(this);
    }

}
