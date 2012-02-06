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

import java.util.HashMap;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.jpa.osgi.PersistenceProvider;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.ext.eclipselink.service.definition.IEntityManagerProvider;
import de.twenty11.skysail.server.ext.osgi.eventlogger.EventLoggerBundleEvent;
import de.twenty11.skysail.server.servicedefinitions.ConfigService;

/**
 * listener for bundle events.
 * 
 * @author carsten
 * 
 */
public class EventLoggerBundleListener implements BundleListener {

    /** slf4j based logger implementation. */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public final void bundleChanged(final BundleEvent event) {
        Bundle bundle = event.getBundle();

        EventLoggerBundleEvent bundleEvent = new EventLoggerBundleEvent();
        bundleEvent.setEventType(event.getType());
        bundleEvent.setSymbolicName(bundle.getSymbolicName());

        try {
            ConfigService configService = ServiceProvider.getConfigService();
            Properties properties = configService.getProperties("skysail.defaultDb.", true);
            
            //HashMap<String, Object> properties = new HashMap<String, Object>();
            properties.put(PersistenceUnitProperties.CLASSLOADER, this.getClass().getClassLoader());
//            properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
//            properties.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/skysail");
//            properties.put("javax.persistence.jdbc.user", "root");
//            properties.put("eclipselink.ddl-generation", "create-tables");
            
            EntityManagerFactory emf = new PersistenceProvider().createEntityManagerFactory(
                    "skysail.server.ext.osgi.eventlogger", 
                    properties);
            
            EntityManager em = emf.createEntityManager();
            if (em == null) {
                logger.warn("EntityManager not available, could not persist bundleEvent '{}'", bundleEvent);
                return;
            }
            em.getTransaction().begin();
            em.persist(bundleEvent);
            em.getTransaction().commit();
            em.close();
        } catch (Throwable t) {
            logger.error("Persisting Entity threw error", t);
            logger.warn("Could not persist bundleEvent '{}'", bundleEvent);
        }
    }

}
