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

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.grids.ColumnsBuilder;
import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.responses.SkysailResponse;
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
                // TODO Auto-generated method stub
                
            }
        });
        getSkysailData().setColumns(new ColumnsBuilder() {

            @Override
            public void configure() {
                try {
                    ClientResource columns = new ClientResource("riap://application/dbviewer/default/my_notes/columns/");
                    columns.setChallengeResponse(getChallengeResponse());
                    Representation representation = columns.get();
                    SkysailResponse<GridData> response = mapper.readValue(representation.getText(),
                            new TypeReference<SkysailResponse<GridData>>() {
                    });
                    GridData payload = response.getData();
                    // List<RowData> gridData = payload.getGrid();
                    // for (RowData rowData : gridData) {
                    // List<Object> columnData = rowData.getColumnData();
                    // // builder.addColumn(rowData.get().get(0).toString());
                    // }
                } catch (Exception e) {

                }
            }
        });
    }

}