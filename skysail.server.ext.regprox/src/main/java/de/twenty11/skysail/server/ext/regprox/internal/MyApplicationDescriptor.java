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

package de.twenty11.skysail.server.ext.regprox.internal;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;

import de.twenty11.skysail.common.app.ApplicationDescription;
import de.twenty11.skysail.server.services.ApplicationDescriptor;

public class MyApplicationDescriptor implements ApplicationDescriptor {

    public static final String APPLICATION_NAME = "regprox";
    private static BundleContext bundleContext;

    @Override
    public ApplicationDescription getApplicationDescription() {
        return new ApplicationDescription(APPLICATION_NAME, "regprox", APPLICATION_NAME);
    }

    protected void activate(final ComponentContext component) {
        bundleContext = component.getBundleContext();
    }


}