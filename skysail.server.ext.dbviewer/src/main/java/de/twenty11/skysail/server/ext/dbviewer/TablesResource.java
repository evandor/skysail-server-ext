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

package de.twenty11.skysail.server.ext.dbviewer;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.ext.dbviewer.RestfulTables;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerApplication;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.restlet.ListServerResource;

public class TablesResource extends ListServerResource<String> implements RestfulTables {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private String connectionName;
    private String schemaName;

    public TablesResource() {
        setName("dbviewer tables resource");
        setDescription("The resource containing the list of tables for the current connection and schema");
    }

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        connectionName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.CONNECTION_NAME);
        schemaName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.SCHEMA_NAME);
        setDescription("The resource containing the list of tables for the connection " + connectionName
                + " and schema " + schemaName);
    }
    
    @Override
    @Get
    public Response<List<String>> getTables() {
        return getEntities(allTables(), "all Tables");
    }


    private List<String> allTables() {
//        EntityManager em = ((DbViewerApplication) getApplication()).getEntityManager();
//        em.getTransaction().begin();
//        java.sql.Connection connection = em.unwrap(java.sql.Connection.class);
        DataSource ds = ((DbViewerApplication) getApplication()).getDataSource(connectionName, getChallengeResponse());
        List<String> result = new ArrayList<String>();
        int count = 0;
        try {
            Connection connection = ds.getConnection();
            DatabaseMetaData meta = connection.getMetaData();

            ResultSet tables = meta.getTables(schemaName, null, null, new String[] { "TABLE" });
            while (tables.next()) {
                count++;
                result.add(tables.getString("TABLE_NAME"));
            }
            setMessage("listing " + count + " tables");
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Database Problem: " + e.getMessage(), e);
        }
    }

//    @Post()
//    public Representation add(JsonRepresentation entity) {
//        SkysailResponse skysailResponse;
//        try {
//            JSONObject jsonObject = entity.getJsonObject();
//            String name = determineValue(jsonObject, "name");
//            DataSource ds = getDataSourceForConnection();
//            TableDetails table = new TableDetails(name);
//            Set<ConstraintViolation<TableDetails>> constraintViolations = validator.validate(table);
//            int size = constraintViolations.size();
//            if (size > 0) {
//                skysailResponse = new SkysailFailureResponse(constraintViolations.toString());
//            } else {
//                String sql = "CREATE TABLE IF NOT EXISTS " + name + " ();";
//                try {
//                    Connection connection = ds.getConnection();
//                    Statement stmt = connection.createStatement();
//                    stmt.execute(sql);
//                    skysailResponse = new SkysailSuccessResponse<SkysailData>();
//                } catch (SQLException e) {
//                    skysailResponse = new SkysailFailureResponse<SkysailData>(e);
//                }
//            }
//        } catch (JSONException e) {
//            skysailResponse = new SkysailFailureResponse(e);
//        }
//        return new JacksonRepresentation<SkysailResponse<GridData>>(skysailResponse);
//    }


}
