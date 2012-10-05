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

import javax.sql.DataSource;

import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.grids.ColumnsBuilder;
import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.grids.RowData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.Connections;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;
import de.twenty11.skysail.server.ext.dbviewer.spi.RestfulColumns;
import de.twenty11.skysail.server.restlet.GridDataServerResource;

public class ColumnsResource extends GridDataServerResource implements RestfulColumns {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private String connectionName;
    private String tableName;
    private DataSource dataSource;

    public ColumnsResource() {
        super(new ColumnsBuilder() {
            @Override
            public void configure() {
                addColumn("typeName");
                addColumn("colSize");
                addColumn("ColName");
                addColumn("DataType");
            }
        });
        setTemplate("skysail.server.ext.dbviewer:columns.ftl");
    }

    @Override
    protected void doInit() throws ResourceException {
        connectionName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.CONNECTION_NAME);
        tableName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.TABLE_NAME);
        Connections connections = ((SkysailApplication) getApplication()).getConnections();
        dataSource = connections.getDataSource(connectionName);
    }

    @Override
    public void buildGrid() {

        GridData grid = getSkysailData();

        Connection connection;
        try {
            connection = dataSource.getConnection();
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet columns = meta.getColumns(null, null, tableName, null);
            while (columns.next()) {
                RowData row = new RowData(getSkysailData().getColumns());
                row.add(columns.getString("TYPE_NAME"));
                row.add(columns.getString("COLUMN_SIZE"));
                row.add(columns.getString("COLUMN_NAME"));
                row.add(columns.getString("DATA_TYPE"));
                // grid.addRowData(getSkysailData().getFilter(), row);
                grid.addRowData(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException("could not execute select statement: " + e.getMessage(), e);
        }
    }

    @Override
    @Get
    public SkysailResponse<GridData> getColumns() {
        return createResponse();
    }
}
