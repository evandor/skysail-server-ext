package de.twenty11.skysail.server.ext.dbviewer.test.columns;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.restlet.Response;
import org.restlet.data.MediaType;

import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.TableDetails;
import de.twenty11.skysail.server.ext.dbviewer.test.ApplicationTests;

public class ColumnsResourceTest extends ApplicationTests {

    @Test
    @Ignore
    public void shouldGetValidResponseForGetRequest() throws Exception {
        addDefaultConnection();
        addTable("default", new TableDetails("tableA"));
        Response response = get(DbViewerUrlMapper.CONNECTION_PREFIX + "default/schemas/PUBLIC/tables/tableA/columns");
        assertEquals(200, response.getStatus().getCode());
        assertThat(response.isEntityAvailable(), is(true));
        assertThat(response.getEntity().getMediaType(), is(MediaType.APPLICATION_JSON));
    }

    @Test
    @Ignore
    public void shouldGetValidGridDataForGetRequestToExistingConnection() throws Exception {
        addDefaultConnection();
        addTable("default", new TableDetails("tableA"));
        Response response = get(DbViewerUrlMapper.CONNECTION_PREFIX + "default/schemas/PUBLIC/tables");
        SkysailResponse<GridData> skysailResponse = getGridDataResponse(response);
        assertThat(skysailResponse.getMessage(), is("listing 1 tables"));
        GridData gridData = skysailResponse.getData();
        assertThat(gridData.getColumns().getAsList().size(), is(3));
        assertThat(gridData.getRows().size(), is(1));
    }

}
