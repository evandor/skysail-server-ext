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

package de.twenty11.skysail.server.ext.osgi.eventlogger.internal;

import de.twenty11.skysail.server.ext.eclipselink.service.definition.IEntityManagerProvider;

/**
 * provides central access to the OSGi services.
 * @author carsten
 *
 */
public class ServiceProvider {

    private static IEntityManagerProvider entityManagerService;

    public synchronized void setEntityManagerProvider(IEntityManagerProvider service) {
        ServiceProvider.entityManagerService = service;
    }

    public static IEntityManagerProvider getEntityManagerProvider() {
        return entityManagerService;
    }
}