package de.twenty11.skysail.server.ext.dbviewer.test.connections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;

import org.junit.Test;
import org.restlet.resource.ClientResource;

import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.ext.dbviewer.RestfulConnections;
import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.responses.Response;
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

        Response<List<ConnectionDetails>> connections = proxy.getConnections();
        assertThat(connections.getMessage(), is(nullValue()));
        List<ConnectionDetails> data = connections.getData();
        assertThat(data.size(), is(0));
    }

    @Test
    public void canIssueGetRequestForJson() throws Exception {
        ClientResource clientResource = new ClientResource("http://localhost:8111"
                + DbViewerUrlMapper.CONNECTION_PREFIX + "?media=json");
        clientResource.setChallengeResponse(authentication);
        RestfulConnections proxy = clientResource.wrap(RestfulConnections.class);
        Response<List<ConnectionDetails>> connections = proxy.getConnections();
        assertThat(connections.getMessage(), is(nullValue()));

    }

}
