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

import javax.persistence.EntityManagerFactory;

import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkysailEntityManagerProvider {

    /** slf4j based logger. */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    protected void activate(final ComponentContext component) {
        logger.info("activating component in {}", component.getBundleContext().getBundle().getSymbolicName());
    }

    public void setEntityManager(EntityManagerFactory emf) {
        SkysailApplication.get().setEntityManagerProvider(emf);
    }

}
