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

import java.io.IOException;

import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configuration {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static ConfigurationAdmin configadmin;

    protected void activate(ComponentContext ctxt) {
        logger.info("Activating DbViewer Config");
        
    }

    protected void deactivate(ComponentContext ctxt) {
    }

    public synchronized void setConfigAdmin(ConfigurationAdmin configadmin) {
        Configuration.configadmin = configadmin;
    }

    public static String getDriverClassName() {
        try {
            org.osgi.service.cm.Configuration config = configadmin.getConfiguration("de.twenty11.skysail.server.config.Configuration");
            return config.getProperties().get("skysail.db.driverClassName").toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }

}
