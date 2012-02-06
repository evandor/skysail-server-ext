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

package de.twenty11.skysail.server.ext.freemarker.devConfig.internal;

import de.twenty11.skysail.server.servicedefinitions.ConfigService;

/**
 * class to access the configuration service.
 * 
 * @author carsten
 *
 */
public class ConfigServiceProvider {

    /** the skysail configuration service. */
    private static ConfigService configService;

    /**
     * setter used by the framework.
     * @param service the configuration service
     */
    public final synchronized void setConfigService(final ConfigService service) {
        ConfigServiceProvider.configService = service;
    }

    
    /**
     * returns the configuration value for a given string.
     * @param identifier key
     * @return the corresponding value
     */
    public static String getConfigString(final String identifier) {
        return configService.getString(identifier);
    }

}
