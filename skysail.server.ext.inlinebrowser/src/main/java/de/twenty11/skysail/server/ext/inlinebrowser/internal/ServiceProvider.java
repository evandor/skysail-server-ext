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

import org.osgi.service.component.ComponentContext;

import freemarker.template.Configuration;

public class ServiceProvider {

    private static Configuration freemarkerConfig;

    protected void activate (ComponentContext context) {
        
    }

    protected void deactivate (ComponentContext context) {
        
    }
    
    protected void setFreemarkerConf(Configuration config ) {
        ServiceProvider.freemarkerConfig = config;
    }
    
    public static Configuration getFreemarkerConfig() {
        return freemarkerConfig;
    }

}
