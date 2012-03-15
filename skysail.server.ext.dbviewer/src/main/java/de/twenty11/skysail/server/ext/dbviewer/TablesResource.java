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

public class TablesResource extends GridDataServerResource {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public TablesResource() {
        super(new GridData());
        setTemplate("skysail.server.ext.dbviewer:tables.ftl");
    }

    @Override
    public void configureColumns(ColumnsBuilder builder) {
        builder.addColumn("Table");

    }

    @Override
    public void filterData() {
        String connectionName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.CONNECTION_NAME);
        BasicDataSource ds = ConnectionsResource.datasources.get(connectionName);
        GridData grid = getSkysailData();
        Connection connection;
        try {
            connection = ds.getConnection();
            DatabaseMetaData meta = connection.getMetaData();

            ResultSet tables = meta.getTables(null, null, null, new String[]{"TABLE"});
            while (tables.next()) {
                RowData rowData = new RowData();
                List<Object> cols = new ArrayList<Object>();
                cols.add(tables.getString("TABLE_NAME"));
                rowData.setColumnData(cols);
                grid.addRowData(rowData);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
