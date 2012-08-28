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
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.grids.ColumnDefinition;
import de.twenty11.skysail.common.grids.Columns;
import de.twenty11.skysail.common.grids.ColumnsBuilder;
import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.grids.RowData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.restlet.GridDataServerResource;

public class DataResource extends GridDataServerResource {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /** deals with json objects */
    private final ObjectMapper mapper = new ObjectMapper();

    public DataResource() {
        super(new ColumnsBuilder() {
            @Override
            public void configure() {
            }
        });
    }

    @Override
    public void buildGrid() {

        String connectionName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.CONNECTION_NAME);
        String tableName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.TABLE_NAME);
        
        ResultSet executeQuery = null;
        
        try {
            ClientResource columns = new ClientResource("riap://application/dbviewer/" + connectionName + "/"
                    + tableName + "/columns/");
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

            BasicDataSource ds = (BasicDataSource)ConnectionsResource.datasources.get(connectionName);
            GridData grid = getSkysailData();
            Connection connection = ds.getConnection();

            Statement createStatement = connection.createStatement();
            executeQuery = createStatement.executeQuery("SELECT * FROM " + tableName);

            while (executeQuery.next()) {
                Columns queryColumns = getSkysailData().getColumns();
                RowData row = new RowData(queryColumns);
                for (ColumnDefinition column : queryColumns.getColumnsInSortOrder()) {
                    String result = executeQuery.getString(column.getName());
                    row.add(result != null ? result : "null");
                }
                grid.addRowData(row);
            }
        } catch (Exception e) {
            throw new RuntimeException("Problem accessing data: " + e.getMessage(), e);
        } 
        finally {
            closeResultSet(executeQuery);
        }

    }

    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
          try {
            resultSet.close();
          }
          catch (SQLException ex) {
            logger.debug("Could not close  ResultSet", ex);
          }
          catch (Throwable ex) {
            // We don't trust the  driver: It might throw RuntimeException or Error.
            logger.debug("Unexpected exception on closing  ResultSet", ex);
          }
        }
      }

}
