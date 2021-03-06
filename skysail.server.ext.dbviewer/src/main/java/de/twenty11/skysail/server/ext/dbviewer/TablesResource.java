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

import de.twenty11.skysail.common.ext.dbviewer.RestfulTables;
import de.twenty11.skysail.common.ext.dbviewer.TableDetails;
import de.twenty11.skysail.common.graphs.Graph;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerApplication;
import de.twenty11.skysail.server.restlet.ListServerResource;

@Graph
public class TablesResource extends ListServerResource<TableDetails> implements RestfulTables {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private String connectionName;
    private String schemaName;

    public TablesResource() {
        setName("dbviewer tables resource");
        setDescription("The resource containing the list of tables for the current connection and schema");
    }

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        connectionName = (String) getRequest().getAttributes().get(Constants.CONNECTION_NAME);
        schemaName = (String) getRequest().getAttributes().get(Constants.SCHEMA_NAME);
        setDescription("The resource containing the list of tables for the connection " + connectionName
                + " and schema " + schemaName);
    }

    @Override
    @Get("html|json")
    public SkysailResponse<List<TableDetails>> getTables() {
        return getEntities(allTables(), "all Tables");
    }

    private List<TableDetails> allTables() {
        try {
            DataSource ds = ((DbViewerApplication) getApplication()).getDataSource(connectionName,
                    getChallengeResponse());
            List<TableDetails> result = new ArrayList<TableDetails>();
            int count = 0;

            Connection connection = ds.getConnection();
            DatabaseMetaData meta = connection.getMetaData();

            ResultSet tables = meta.getTables(schemaName, null, null, new String[] { "TABLE" });
            while (tables.next()) {
                count++;
                TableDetails details = new TableDetails(tables.getString("TABLE_NAME"));
                result.add(details);
            }
            setMessage("listing " + count + " tables");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Database Problem: " + e.getMessage(), e);
        }
    }

}
