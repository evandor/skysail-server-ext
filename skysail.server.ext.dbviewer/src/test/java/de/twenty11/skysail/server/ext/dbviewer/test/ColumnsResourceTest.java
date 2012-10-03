package de.twenty11.skysail.server.ext.dbviewer.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Method;

import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.TableDetails;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.ConnectionDetails;

public class ColumnsResourceTest extends ResourceTest {

    @Test
    public void shouldGetValidResponseForGetRequest() throws Exception {
        addDefaultConnection();
        addTable("default", new TableDetails("tableA"));
        Response response = get("/dbviewer/connections/default/tables/tableA/columns");
        assertEquals(200, response.getStatus().getCode());
        assertThat(response.isEntityAvailable(), is(true));
        assertThat(response.getEntity().getMediaType(), is(MediaType.APPLICATION_JSON));
    }

    @Test
    @Ignore
    public void shouldGetValidGridDataForGetRequestToExistingConnection() throws Exception {
        ConnectionDetails connection = new ConnectionDetails("default", "SA", "", "jdbc:hsqldb:mem:.",
                "org.hsqldb.jdbc.JDBCDriver");
        handleRequest(preparePostRequest(connection));

        Response response = handleRequest(new Request(Method.GET, "/dbviewer/connections/default/tables"));
        SkysailResponse<GridData> skysailResponse = getSkysailResponse(response);
        assertThat(skysailResponse.getMessage(), is("listing 0 tables"));
        GridData gridData = skysailResponse.getData();
        assertThat(gridData.getColumns().getAsList().size(), is(3));
        assertThat(gridData.getRows().size(), is(0));
    }

}
