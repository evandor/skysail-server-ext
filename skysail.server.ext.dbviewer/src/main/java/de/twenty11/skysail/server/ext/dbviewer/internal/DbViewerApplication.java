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

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.osgi.framework.BundleContext;
import org.restlet.Context;
import org.restlet.data.ChallengeResponse;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.server.core.restlet.RouteBuilder;
import de.twenty11.skysail.server.ext.dbviewer.AddConnectionResource;
import de.twenty11.skysail.server.ext.dbviewer.ColumnsResource;
import de.twenty11.skysail.server.ext.dbviewer.ConnectionResource;
import de.twenty11.skysail.server.ext.dbviewer.ConnectionsResource;
import de.twenty11.skysail.server.ext.dbviewer.Constants;
import de.twenty11.skysail.server.ext.dbviewer.ConstraintsResource;
import de.twenty11.skysail.server.ext.dbviewer.DataResource;
import de.twenty11.skysail.server.ext.dbviewer.RootResource;
import de.twenty11.skysail.server.ext.dbviewer.SchemasResource;
import de.twenty11.skysail.server.ext.dbviewer.TablesResource;
import de.twenty11.skysail.server.restlet.SkysailApplication;

/**
 * @author carsten
 * 
 */
public class DbViewerApplication extends SkysailApplication {

    private EntityManagerFactory emf;

    /** deals with json objects */
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * @param staticPathTemplate
     * @param bundleContext
     * @param emf
     */
    public DbViewerApplication(BundleContext bundleContext, Context componentContext, EntityManagerFactory emf) {
        super(componentContext == null ? null : componentContext.createChildContext());
        getLogger().info("Starting DbViewerApplication");
        setDescription("RESTful DbViewer OSGi bundle");
        setOwner("twentyeleven");
        setName("dbviewer");
        this.emf = emf;
    }

    protected void attach() {

        String conn = Constants.CONNECTION_NAME;
        String schema = Constants.SCHEMA_NAME;
        String table = Constants.TABLE_NAME;

        // @formatter:off
        router.attach(new RouteBuilder("", RootResource.class).setVisible(false));
        router.attach(new RouteBuilder("/", RootResource.class).setVisible(false));
        router.attach(new RouteBuilder("/connections", ConnectionsResource.class).setText("all Connections"));
        router.attach(new RouteBuilder("/connections/", AddConnectionResource.class).setText("add Connection"));
        router.attach(new RouteBuilder("/connections/{"+conn+"}", ConnectionResource.class).setVisible(false));
        router.attach(new RouteBuilder("/connections/{"+conn+"}/schemas", SchemasResource.class).setVisible(false));
        router.attach(new RouteBuilder("/connections/{"+conn+"}/schemas/{"+schema+"}/tables", TablesResource.class).setVisible(false));
        router.attach(new RouteBuilder("/connections/{"+conn+"}/schemas/{"+schema+"}/tables/{"+table+"}/columns", ColumnsResource.class).setVisible(false));
        router.attach(new RouteBuilder("/connections/{"+conn+"}/schemas/{"+schema+"}/tables/{"+table+"}/constraints", ConstraintsResource.class).setVisible(false));
        router.attach(new RouteBuilder("/connections/{"+conn+"}/schemas/{"+schema+"}/tables/{"+table+"}/data", DataResource.class).setVisible(false));
        // @formatter:on
    }

    public EntityManager getEntityManager() {

        if (this.emf == null) {
            return null;
        }
        Map<String, Object> properties = emf.getProperties();
        properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
        properties.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost/skysailosgi");
        properties.put("javax.persistence.jdbc.user", "root");
        properties.put("javax.persistence.jdbc.password", "websphere");
        // properties.put("eclipselink.ddl-generation", "create-tables");

        return this.emf.createEntityManager();
    }

    public DataSource getDataSource(String connectionName, ChallengeResponse challengeResponse) throws Exception {
        ConnectionDetails result = getConnection(connectionName, challengeResponse);
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(result.getUrl());
        ds.setUsername(result.getUsername());
        ds.setDriverClassName(result.getDriverName());
        ds.setPassword(result.getPassword());
        return ds;
    }

    private ConnectionDetails getConnection(String connectionName, ChallengeResponse challengeResponse)
            throws Exception {
        ClientResource columns = new ClientResource("riap://application/"
        // + DbViewerApplicationDescriptor.APPLICATION_NAME +
                + "connections/" + connectionName);
        columns.setChallengeResponse(challengeResponse);
        Representation representation = columns.get();
        de.twenty11.skysail.common.responses.SkysailResponse<ConnectionDetails> response;
        response = mapper.readValue(representation.getText(),
                new TypeReference<de.twenty11.skysail.common.responses.SkysailResponse<ConnectionDetails>>() {
                });
        return response.getData();
    }

}
