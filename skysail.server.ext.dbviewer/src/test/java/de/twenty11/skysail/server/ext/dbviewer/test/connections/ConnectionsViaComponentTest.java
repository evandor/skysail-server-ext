package de.twenty11.skysail.server.ext.dbviewer.test.connections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;
import org.restlet.resource.ClientResource;

import de.twenty11.skysail.common.ext.dbviewer.RestfulConnections;
import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerUrlMapper;
import de.twenty11.skysail.server.ext.dbviewer.test.ComponentTests;

public class ConnectionsViaComponentTest extends ComponentTests {

    @Test
    public void canIssueGetRequestForXml() throws Exception {
        ClientResource clientResource = new ClientResource("http://localhost:8111"
                + DbViewerUrlMapper.CONNECTION_PREFIX + "?media=xml");
        clientResource.setChallengeResponse(authentication);
        RestfulConnections proxy = clientResource.wrap(RestfulConnections.class);

        SkysailResponse<GridData> connections = proxy.getConnections();
        assertThat(connections.getMessage(), is(nullValue()));
        GridData data = connections.getData();
        assertThat(data.getColumns().getAsList().size(), is(5));
        assertThat(data.getRows().size(), is(0));
    }

    @Test
    public void canIssueGetRequestForJson() throws Exception {
        ClientResource clientResource = new ClientResource("http://localhost:8111"
                + DbViewerUrlMapper.CONNECTION_PREFIX + "?media=json");
        clientResource.setChallengeResponse(authentication);
        RestfulConnections proxy = clientResource.wrap(RestfulConnections.class);
        SkysailResponse<GridData> connections = proxy.getConnections();
        assertThat(connections.getMessage(), is(nullValue()));

    }

}
