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

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.grids.ColumnsBuilder;
import de.twenty11.skysail.common.grids.RowData;
import de.twenty11.skysail.common.messages.GridData;
import de.twenty11.skysail.server.GridDataServerResource;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;

public class ColumnsResource extends GridDataServerResource {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public ColumnsResource() {
        super(new GridData());
        setTemplate("skysail.server.ext.dbviewer:columns.ftl");
    }
    
    @Override
    public void configureColumns(ColumnsBuilder builder) {
        builder.addColumn("typeName").addColumn("ColSize").addColumn("ColName").addColumn("DataType");
        
    }

    @Override
    public void filterData() {
        
        String connectionName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.CONNECTION_NAME);
        String tableName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.TABLE_NAME);

        BasicDataSource ds = ConnectionsResource.datasources.get(connectionName);
        GridData grid = getSkysailData();

        Connection connection;
        try {
            connection = ds.getConnection();
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet columns = meta.getColumns(null, null, tableName, null);
            while (columns.next()) {
                RowData rowData = new RowData();
                List<Object> cols = new ArrayList<Object>();
                cols.add(columns.getString("TYPE_NAME"));
                cols.add(columns.getString("COLUMN_SIZE"));
                cols.add(columns.getString("COLUMN_NAME"));
                cols.add(columns.getString("DATA_TYPE"));
                rowData.setColumnData(cols);
                grid.addRowData(rowData);
            }
        } catch (SQLException e) {
            throw new RuntimeException("could not execute select statement",e);
        }
    }


}
