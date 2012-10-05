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

import de.twenty11.skysail.common.grids.ColumnDefinition;
import de.twenty11.skysail.common.grids.Columns;
import de.twenty11.skysail.common.grids.ColumnsBuilder;
import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.grids.RowData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.Connections;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;
import de.twenty11.skysail.server.ext.dbviewer.spi.RestfulDbData;
import de.twenty11.skysail.server.restlet.GridDataServerResource;

public class DataResource extends GridDataServerResource implements RestfulDbData {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /** deals with json objects */
    private final ObjectMapper mapper = new ObjectMapper();

    private String connectionName;

    private DataSource dataSource;

    private String tableName;

    public DataResource() {
        super(new ColumnsBuilder() {
            @Override
            public void configure() {
            }
        });
    }

    @Override
    protected void doInit() throws ResourceException {
        tableName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.TABLE_NAME);
        connectionName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.CONNECTION_NAME);
        Connections connections = ((SkysailApplication) getApplication()).getConnections();
        dataSource = connections.getDataSource(connectionName);
    }

    @Override
    public void buildGrid() {
        ResultSet executeQuery = null;
        try {
            getColumns();
            executeQuery = getRows(getSkysailData());
        } catch (Exception e) {
            throw new RuntimeException("Problem accessing data: " + e.getMessage(), e);
        } finally {
            closeResultSet(executeQuery);
        }
    }

    @Override
    @Get
    public SkysailResponse<GridData> getData() {
        return createResponse();
    }

    private void getColumns() throws IOException, JsonParseException, JsonMappingException {
        ClientResource columns = new ClientResource("riap://application/dbviewer/connections/" + connectionName
                + "/tables/" + tableName + "/columns");
        columns.setChallengeResponse(getChallengeResponse());
        Representation representation = columns.get();
        SkysailResponse<GridData> response = mapper.readValue(representation.getText(),
                new TypeReference<SkysailResponse<GridData>>() {
                });
        GridData payload = response.getData();
        List<RowData> gridData = payload.getRows();
        for (RowData rowData : gridData) {
            List<Object> columnData = rowData.getColumnData();
            ColumnDefinition columnDefinition = new ColumnDefinition((String) columnData.get(1));
            getSkysailData().getColumns().getAsList().add(columnDefinition);
        }
    }

    private ResultSet getRows(GridData grid) throws SQLException {
        ResultSet executeQuery;
        Connection connection = dataSource.getConnection();

        Statement createStatement = connection.createStatement();
        executeQuery = createStatement.executeQuery("SELECT * FROM " + tableName);

        int count = 0;
        while (executeQuery.next()) {
            Columns queryColumns = getSkysailData().getColumns();
            RowData row = new RowData(queryColumns);
            for (ColumnDefinition column : queryColumns.getColumnsInSortOrder()) {
                String result = executeQuery.getString(column.getName());
                row.add(result != null ? result : "null");
            }
            grid.addRowData(row);
            count++;
        }
        setMessage("found " + count + " rows");
        return executeQuery;
    }

    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                logger.debug("Could not close  ResultSet", ex);
            } catch (Throwable ex) {
                // We don't trust the driver: It might throw RuntimeException or Error.
                logger.debug("Unexpected exception on closing  ResultSet", ex);
            }
        }
    }

}
