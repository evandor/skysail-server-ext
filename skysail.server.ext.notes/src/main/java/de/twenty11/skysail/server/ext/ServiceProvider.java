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

package de.twenty11.skysail.server.ext;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import de.twenty11.skysail.server.servicedefinitions.ConfigService;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.jpa.osgi.PersistenceProvider;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * provides central access to the OSGi services.
 * @author carsten
 *
 */
public class ServiceProvider {

    /** OSGi-provided configuration service. */
    private static ConfigService configService;
    
    /** slf4j based logger. */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static EntityManager em;

    /**
     * Activating this component will check if the skysail database is setup correctly.
     * 
     * @param component provided by framework
     */
    protected void activate(final ComponentContext component) {
        logger.info("activating component in {}", component.getBundleContext().getBundle().getSymbolicName());
        try {
            em = getEntityManager();
            // validateDefaultClient(em);
            // validateDefaultRole(em);
            // validateDefaultPermission(em);
            // validateDefaultUser(em);
            em.close();
        } catch (Throwable t) {
            logger.error("Persisting Entity threw error", t);
        }
    }

    public static EntityManager entityManager() {
        return em;
    }

    /**
     * @return EntityManager or throws exception
     */
    private EntityManager getEntityManager() {
        Properties properties = configService.getProperties("skysail.defaultDb.");
        properties.put(PersistenceUnitProperties.CLASSLOADER, this.getClass().getClassLoader());
        EntityManagerFactory emf = new PersistenceProvider().createEntityManagerFactory("skysail.server.ext.notes",
                        properties);
        EntityManager em = emf.createEntityManager();
        return em;
    }
    
    /**
     * cleanup. 
     * @param component provided by framework
     */
    protected void deactivate(final ComponentContext component) {
        logger.info("deactivating component in {}", component.getBundleContext().getBundle().getSymbolicName());
    }
    
    /**
     * set by framework.
     * @param service provided config service
     */
    public final synchronized void setConfigService(final ConfigService service) {
        ServiceProvider.configService = service;
    }
    
    /**
     * @return the config service provided by the framework.
     */
    public static ConfigService getConfigService() {
        return configService;
    }
    
    // private void validateDefaultClient(EntityManager em) {
    // try {
    // em.getTransaction().begin();
    // SkysailClient client = new SkysailClient();
    // client.setId(1);
    // client.setClientname("default");
    // em.persist(client);
    // em.getTransaction().commit();
    // } catch (Throwable t) {
    // logger.error("Persisting Entity threw error", t);
    // }
    // }

    // private void validateDefaultRole(EntityManager em) {
    // try {
    // SkysailClient defaultClient = ClientService.getClient(1);
    //
    // em.getTransaction().begin();
    // SkysailRole adminrole = new SkysailRole();
    // adminrole.setRolename("administrator");
    // adminrole.setId(1);
    // adminrole.setClient(defaultClient);
    // em.persist(adminrole);
    // em.getTransaction().commit();
    //
    // } catch (Throwable t) {
    // logger.error("Persisting Entity threw error", t);
    // }
    // }
    //

}
