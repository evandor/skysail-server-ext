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

import javax.sql.DataSource;

import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.ext.dbviewer.ColumnsDetails;
import de.twenty11.skysail.common.ext.dbviewer.RestfulColumns;
import de.twenty11.skysail.common.responses.FailureResponse;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.common.responses.SuccessResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;
import de.twenty11.skysail.server.restlet.ListServerResource;

public class ColumnsResource extends ListServerResource<List<ColumnsDetails>> implements RestfulColumns {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private String connectionName;
    private String tableName;
    private DataSource dataSource;
    private String schemaName;

    @Override
    protected void doInit() throws ResourceException {
        connectionName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.CONNECTION_NAME);
        schemaName = (String) getRequest().getAttributes().get("schema");
        tableName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.TABLE_NAME);
        DataSource dataSource = ((SkysailApplication) getApplication()).getConnections(connectionName);
    }

    @Override
    public void buildGrid() {

        setMessage("all columns");
        Connection connection;
        try {
            connection = dataSource.getConnection();
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet columns = meta.getColumns(schemaName, null, tableName, null);
            List<ColumnsDetails> result = new ArrayList<ColumnsDetails>();
            while (columns.next()) {
                result.add(new ColumnsDetails(columns.getString("COLUMN_NAME")));
            }
            setSkysailData(result);
        } catch (SQLException e) {
            throw new RuntimeException("could not execute select statement: " + e.getMessage(), e);
        }
    }

    @Override
    @Get
    public Response<List<ColumnsDetails>> getColumns() {
        Response<List<ColumnsDetails>> response;
        try {
            response = new SuccessResponse<List<ColumnsDetails>>(getFilteredData());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response = new FailureResponse<List<ColumnsDetails>>(e);
        }
        return response;
    }
}
