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

import de.twenty11.skysail.common.RowData;
import de.twenty11.skysail.common.messages.GridData;
import de.twenty11.skysail.common.messages.GridInfo;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.osgi.SkysailUtils;
import de.twenty11.skysail.server.restletosgi.SkysailServerResource;

public class TablesResource extends SkysailServerResource<GridData> {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String[] fields = { "name" };

    public TablesResource() {
        setTemplate("skysail.server.ext.dbviewer:tables.ftl");
    }

    @Override
    public GridData getData() {
        String connectionName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.CONNECTION_NAME);
        BasicDataSource ds = ConnectionsResource.datasources.get(connectionName);
        GridInfo fieldsList = SkysailUtils.createFieldList(fields);
        GridData grid = new GridData(fieldsList.getColumns());

        try {
            Connection connection = ds.getConnection();
            DatabaseMetaData meta = connection.getMetaData();
            
            ResultSet tables = meta.getTables(null, null, null, new String[] {"TABLE"});
            while (tables.next()) {
                RowData rowData = new RowData();
                List<Object> cols = new ArrayList<Object>();
                cols.add(tables.getString("TABLE_NAME"));
                rowData.setColumnData(cols);
                grid.addRowData(rowData );
            }

            //            ResultSet schemas = meta.getSchemas(null, null);
            //            while (schemas.next()) {
            //                RowData rowData = new RowData();
            //                List<Object> cols = new ArrayList<Object>();
            //                cols.add(schemas.getString(0));
            //                rowData.setColumnData(cols);
            //                grid.addRowData(rowData );
            //
            //            }
        } catch (SQLException e) {
            throw new RuntimeException("Exception when trying to retrieve metadata", e);
        }
        return grid;
    }

}
