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

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.grids.ColumnsBuilder;
import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.server.GridDataServerResource;

public class DataResource extends GridDataServerResource {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public DataResource() {
        super(new GridData());
        setTemplate("skysail.server.ext.dbviewer:data.ftl");
    }


    @Override
    public void configureColumns(ColumnsBuilder builder) {
        // get (dynamic) Columns definition by "eating our own dogfood"
        ClientResource columns =
                        new ClientResource("http://localhost:8099/rest/dbviewer/default/SKYSAILCLIENTS/columns/");
        try {
            columns.get().write(System.out);
        } catch (ResourceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        builder.addColumn("test");

    }

    @Override
    public void buildGrid() {
        // TODO Auto-generated method stub
        
    }

    
//   // @Override
//    public GridData getData() {
//        
//        String connectionName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.CONNECTION_NAME);
//        String tableName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.TABLE_NAME);
//
//        BasicDataSource ds = ConnectionsResource.datasources.get(connectionName);
//        GridInfo fieldsList = SkysailUtils.createFieldList(fields);
//        GridData grid = new GridData(fieldsList.getColumns());
//
//        Connection connection;
//        try {
//            connection = ds.getConnection();
//            Statement stmt = connection.createStatement();
//            //List<Map<String, Object>> dataGrid = new ArrayList<Map<String, Object>>();
//            if (stmt.execute("SELECT * FROM " + tableName)) {
//                ResultSet resultSet = stmt.getResultSet();
//                int i = 0;
//                int size = 0;
//                while (resultSet.next()) {
//                    if (i == 0) {
//                        ResultSetMetaData metaData = resultSet.getMetaData();
//                        size = metaData.getColumnCount();
//                    }
//                    RowData rowData = new RowData();
//                    List<Object> cols = new ArrayList<Object>();
//
//                    Map<String, Object> row = new HashMap<String, Object>();
//                    for (int j = 1; j <= size; j++) {
//                        //row.put(resultSet.getMetaData().getColumnLabel(j), resultSet.getObject(j));
//                        cols.add(resultSet.getObject(j));
//                    }
//                    rowData.setColumnData(cols);
//                    grid.addRowData(rowData);
//                }
//            }
//            return grid;
//        } catch (SQLException e) {
//            throw new RuntimeException("could not execute select statement",e);
//        }
//    }


}
