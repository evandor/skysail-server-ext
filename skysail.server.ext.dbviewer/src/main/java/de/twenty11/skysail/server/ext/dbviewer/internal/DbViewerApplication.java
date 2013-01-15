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
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.ChallengeResponse;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.server.listener.SkysailApplicationServiceListener;
import de.twenty11.skysail.server.listener.UrlMappingServiceListener;
import de.twenty11.skysail.server.restlet.RestletOsgiApplication;

/**
 * @author carsten
 * 
 */
public class DbViewerApplication extends RestletOsgiApplication {

    private EntityManagerFactory emf;
    private static DbViewerApplication self;

    /** deals with json objects */
    private final ObjectMapper mapper = new ObjectMapper();
    
    /**
     * @param staticPathTemplate
     * @param bundleContext 
     */
    public DbViewerApplication(String staticPathTemplate, BundleContext bundleContext) {
        super(DbViewerApplicationDescriptor.APPLICATION_NAME, staticPathTemplate);
        getLogger().info("Starting DbViewerApplication");
        setDescription("RESTful DbViewer OSGi bundle");
        setOwner("twentyeleven");
        setBundleContext(bundleContext);
    }

    /**
     * this is done to give osgi a chance to inject services to restlet; should be changed to some javax.inject approach
     * (like using InjectedServerResource) once this is available.
     * 
     * @return
     */
    public static DbViewerApplication get() {
        return self;
    }

    @Override
    public void handle(Request request, Response response) {
        super.handle(request, response);
    }

    // TODO proper place for this here? what about multiple instances?
    protected void attach() {
        if (FrameworkUtil.getBundle(RestletOsgiApplication.class) != null) {
            urlMappingServiceListener = new UrlMappingServiceListener(this);
            new SkysailApplicationServiceListener(this);
        }
    }

    public void setEntityManagerProvider(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return this.emf != null ? this.emf.createEntityManager() : null;
    }

    public DataSource getDataSource(String connectionName, ChallengeResponse challengeResponse) {
        ConnectionDetails result = getConnection(connectionName, challengeResponse);
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(result.getUrl());
        ds.setUsername(result.getUsername());
        ds.setDriverClassName(result.getDriverName());
        ds.setPassword(result.getPassword());
        return ds;
    }

    private ConnectionDetails getConnection(String connectionName, ChallengeResponse challengeResponse) {
        ClientResource columns = new ClientResource("riap://application/"
                + DbViewerApplicationDescriptor.APPLICATION_NAME + "/connections/" + connectionName);
        columns.setChallengeResponse(challengeResponse);
        Representation representation = columns.get();
        de.twenty11.skysail.common.responses.Response<ConnectionDetails> response;
        try {
            response = mapper.readValue(representation.getText(),
                    new TypeReference<de.twenty11.skysail.common.responses.Response<ConnectionDetails>>() {
                    });
            return response.getData();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
}
