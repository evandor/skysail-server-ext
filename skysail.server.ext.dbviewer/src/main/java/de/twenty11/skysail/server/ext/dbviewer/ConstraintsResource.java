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

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.ext.dbviewer.ColumnsDetails;
import de.twenty11.skysail.common.ext.dbviewer.ConstraintDetails;
import de.twenty11.skysail.common.ext.dbviewer.RestfulColumns;
import de.twenty11.skysail.common.ext.dbviewer.RestfulConstraints;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;
import de.twenty11.skysail.server.restlet.ListServerResource;

public class ConstraintsResource extends ListServerResource<ConstraintDetails> implements RestfulConstraints {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private String connectionName;
    private String tableName;
    private String schemaName;

    public ConstraintsResource() {
        setName("dbviewer columns resource");
        setDescription("The resource describing the columns of a table of a schema of a connection");
    }

    @Override
    protected void doInit() throws ResourceException {
        connectionName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.CONNECTION_NAME);
        schemaName = (String) getRequest().getAttributes().get("schema");
        tableName = (String) getRequest().getAttributes().get(DbViewerUrlMapper.TABLE_NAME);
        setDescription("The resource describing the columns of the table '" + tableName + "' of the schema '"
                + schemaName + "' of the connection '" + connectionName + "'");
    }

    @Override
    @Get
    public Response<List<ConstraintDetails>> getConstraints() {
        return getEntities(allConstraints(), "all Constraints");
    }

    private List<ConstraintDetails> allConstraints() {
        try {
            EntityManager em = ((SkysailApplication) getApplication()).getEntityManager();
            em.getTransaction().begin();
            java.sql.Connection connection = em.unwrap(java.sql.Connection.class);
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet columns = meta.getIndexInfo(schemaName, null, tableName, false, true);
            List<ConstraintDetails> result = new ArrayList<ConstraintDetails>();
            while (columns.next()) {
                result.add(new ConstraintDetails(columns.getString("COLUMN_NAME")));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("could not execute select statement: " + e.getMessage(), e);
        }
    }
}
