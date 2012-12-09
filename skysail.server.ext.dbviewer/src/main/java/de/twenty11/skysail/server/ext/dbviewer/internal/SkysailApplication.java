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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.apache.commons.dbcp.BasicDataSource;
import org.osgi.framework.FrameworkUtil;
import org.restlet.Request;
import org.restlet.Response;

import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.server.listener.SkysailApplicationServiceListener;
import de.twenty11.skysail.server.listener.UrlMappingServiceListener;
import de.twenty11.skysail.server.restlet.RestletOsgiApplication;

/**
 * @author carsten
 * 
 */
public class SkysailApplication extends RestletOsgiApplication {

    private EntityManagerFactory emf;
    private static SkysailApplication self;

    /**
     * @param staticPathTemplate
     */
    public SkysailApplication(String staticPathTemplate) {
        super(staticPathTemplate);
        setDescription("RESTful DbViewer OSGi bundle");
        setOwner("twentyeleven");
        self = this;
    }

    /**
     * this is done to give osgi a chance to inject serives to restlet; should be changed to some javax.inject approach
     * (like using InjectedServerResource) once this is available.
     * 
     * @return
     */
    public static SkysailApplication get() {
        return self;
    }

    @Override
    public void handle(Request request, Response response) {
        super.handle(request, response);
    }

    public javax.sql.DataSource getConnections(String connectionName) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<ConnectionDetails> query = em.createQuery("SELECT c FROM ConnectionDetails c WHERE c.name=:name", ConnectionDetails.class);
        query.setParameter("name", connectionName);
        ConnectionDetails result = query.getSingleResult();
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(result.getUrl());
        ds.setUsername(result.getUsername());
        ds.setDriverClassName(result.getDriverName());
        ds.setPassword(result.getPassword());
        return ds;
    }
    // TODO proper place for this here? what about multiple instances?
    protected void attach() {
        if (FrameworkUtil.getBundle(RestletOsgiApplication.class) != null) {
            new UrlMappingServiceListener(this);
            new SkysailApplicationServiceListener(this);
        }
    }

    public void setEntityManagerProvider(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return this.emf != null ? this.emf.createEntityManager() : null;
    }

    public ConnectionDetails getConnectionByName(String connectionName) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<ConnectionDetails> query = em.createQuery("SELECT c FROM ConnectionDetails c WHERE c.name=:name", ConnectionDetails.class);
        query.setParameter(0, connectionName);
        return query.getSingleResult();
    }

}
