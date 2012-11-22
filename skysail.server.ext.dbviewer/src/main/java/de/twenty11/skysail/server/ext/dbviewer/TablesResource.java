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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;
import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.SkysailData;
import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.ext.dbviewer.RestfulTables;
import de.twenty11.skysail.common.grids.ColumnsBuilder;
import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.grids.RowData;
import de.twenty11.skysail.common.responses.FailureResponse;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.common.responses.SkysailFailureResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.responses.SkysailSuccessResponse;
import de.twenty11.skysail.common.responses.SuccessResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.Connections;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.TableDetails;
import de.twenty11.skysail.server.restlet.GenericServerResource;
import de.twenty11.skysail.server.restlet.GridDataServerResource;

public class TablesResource extends GenericServerResource<List<String>> implements RestfulTables {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Validator validator;

    private String connectionName;

    private String schemaName;

    public TablesResource() {
        Configuration<?> config = Validation.byDefaultProvider().providerResolver(new OSGiServiceDiscoverer())
                .configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        validator = factory.getValidator();
    }

    @Override
    protected void doInit() throws ResourceException {
        connectionName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.CONNECTION_NAME);
        schemaName = (String) getRequest().getAttributes().get("schema");
    }

    @Override
    public void buildGrid() {
        DataSource ds = getDataSourceForConnection();
        //GridData grid = getSkysailData();
        Connection connection;
        List<String> result = new ArrayList<String>();
        int count = 0;
        try {
            connection = ds.getConnection();
            DatabaseMetaData meta = connection.getMetaData();

            ResultSet tables = meta.getTables(schemaName, null, null, new String[] { "TABLE" });
            while (tables.next()) {
//                RowData row = new RowData(getSkysailData().getColumns());
//                row.add(tables.getString("TABLE_NAME"));
//                row.add(getParent() + connectionName + "/" + tables.getString("TABLE_NAME") + "/data/?media=json");
//                row.add(getParent() + connectionName + "/" + tables.getString("TABLE_NAME") + "/columns/?media=json");
//                grid.addRowData(row);
                count++;
                result.add(tables.getString("TABLE_NAME"));
            }
            setMessage("listing " + count + " tables");
            setSkysailData(result);
        } catch (SQLException e) {
            throw new RuntimeException("Database Problem: " + e.getMessage(), e);
        }
    }

    @Get
    public Response<List<String>> getTables() {
        Response<List<String>> response;
        try {
            response = new SuccessResponse<List<String>>(getFilteredData());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response = new FailureResponse<List<String>>(e);
        }
        return response;
    }

    @Post()
    public Representation add(JsonRepresentation entity) {
        SkysailResponse skysailResponse;
        try {
            JSONObject jsonObject = entity.getJsonObject();
            String name = determineValue(jsonObject, "name");
            DataSource ds = getDataSourceForConnection();
            TableDetails table = new TableDetails(name);
            Set<ConstraintViolation<TableDetails>> constraintViolations = validator.validate(table);
            int size = constraintViolations.size();
            if (size > 0) {
                skysailResponse = new SkysailFailureResponse(constraintViolations.toString());
            } else {
                String sql = "CREATE TABLE IF NOT EXISTS " + name + " ();";
                try {
                    Connection connection = ds.getConnection();
                    Statement stmt = connection.createStatement();
                    stmt.execute(sql);
                    skysailResponse = new SkysailSuccessResponse<SkysailData>();
                } catch (SQLException e) {
                    skysailResponse = new SkysailFailureResponse<SkysailData>(e);
                }
            }
        } catch (JSONException e) {
            skysailResponse = new SkysailFailureResponse(e);
        }
        return new JacksonRepresentation<SkysailResponse<GridData>>(skysailResponse);
    }

    private DataSource getDataSourceForConnection() {
        return ((SkysailApplication) getApplication()).getConnections(connectionName);
    }

}
