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

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.common.ext.dbviewer.ColumnsDetails;
import de.twenty11.skysail.common.ext.dbviewer.RestfulData;
import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.grids.RowData;
import de.twenty11.skysail.common.responses.FailureResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.responses.SuccessResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerApplication;
import de.twenty11.skysail.server.restlet.GenericServerResource;

@Presentation(preferred = PresentationStyle.TABLE)
public class DataResource extends GenericServerResource<List<String>> implements RestfulData {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /** deals with json objects */
    private final ObjectMapper mapper = new ObjectMapper();

    private String connectionName;

    private String tableName;

    private String schemaName;

    public DataResource() {
        setName("dbviewer data resource");
        setDescription("The resource describing the data found in a table");
    }

    @Override
    protected void doInit() throws ResourceException {
        tableName = (String) getRequest().getAttributes().get(Constants.TABLE_NAME);
        connectionName = (String) getRequest().getAttributes().get(Constants.CONNECTION_NAME);
        schemaName = (String) getRequest().getAttributes().get("schema");
        setDescription("The resource describing the data found in the table '" + tableName + "'");
    }

    @Override
    public void buildGrid() {
        ResultSet executeQuery = null;
        try {
            getColumns();
            // executeQuery = getRows(getSkysailData());
        } catch (Exception e) {
            throw new RuntimeException("Problem accessing data: " + e.getMessage(), e);
        } finally {
            closeResultSet(executeQuery);
        }
    }

    @Override
    @Get("html|json")
    public SkysailResponse<GridData> getData() {
        SkysailResponse<GridData> response;
        try {
            response = new SuccessResponse<GridData>(getFilteredData());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response = new FailureResponse<GridData>(e);
        }
        return response;
    }

    private GridData getFilteredData() {
        ResultSet executeQuery = null;
        GridData gridData = new GridData();
        try {
            List<ColumnsDetails> columns = getColumns();
            return getRows(columns, gridData);
        } catch (Exception e) {
            throw new RuntimeException("Problem accessing data: " + e.getMessage(), e);
        } finally {
            closeResultSet(executeQuery);
        }
    }

    private List<ColumnsDetails> getColumns() throws IOException, JsonParseException, JsonMappingException {
        ClientResource columns = new ClientResource("riap://application/"
 + "connections/" + connectionName
                + "/schemas/"
                + schemaName + "/tables/" + tableName + "/columns");
        columns.setChallengeResponse(getChallengeResponse());
        Representation representation = columns.get();
        SkysailResponse<List<ColumnsDetails>> response = mapper.readValue(representation.getText(),
                new TypeReference<SkysailResponse<List<ColumnsDetails>>>() {
                });
        List<ColumnsDetails> payload = response.getData();
        return payload;
    }

    private GridData getRows(List<ColumnsDetails> columns, GridData grid) throws SQLException {
        ResultSet executeQuery;
//        EntityManager em = ((DbViewerApplication) getApplication()).getEntityManager();
//        em.getTransaction().begin();
//        java.sql.Connection connection = em.unwrap(java.sql.Connection.class);
        DataSource ds = ((DbViewerApplication) getApplication()).getDataSource(connectionName, getChallengeResponse());
        Connection connection = ds.getConnection();
        connection.setCatalog(schemaName);

        Statement createStatement = connection.createStatement();
        executeQuery = createStatement.executeQuery("SELECT * FROM " + tableName);

        int count = 0;
        while (executeQuery.next()) {
            RowData row = new RowData();
            for (ColumnsDetails column : columns) {
                String result = executeQuery.getString(column.getId());
                row.add(column.getId(), result != null ? result : "null");
            }
            grid.addRowData(row);
            count++;
        }
        setMessage("found " + count + " rows");
        return grid;
    }

    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                logger.debug("Could not close  ResultSet", ex);
            } catch (Throwable ex) {
                logger.debug("Unexpected exception on closing  ResultSet", ex);
            }
        }
    }

}
