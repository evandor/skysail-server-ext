package de.twenty11.skysail.server.ext.jgit.test;

import de.twenty11.skysail.server.ext.jgit.TriggersResource;
import de.twenty11.skysail.server.ext.jgit.internal.MyApplication;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.jgit.Scheduler;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Method;
import org.restlet.ext.jackson.JacksonRepresentation;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class TriggersResourceTest {

    private TriggersResource resource;
    private MyApplication application;

    @Before
    public void setUp() throws Exception {
        Scheduler scheduler = Mockito.mock(Scheduler.class);
        application = new MyApplication(null, scheduler);
        application.createInboundRoot();
        resource = new TriggersResource();
        resource.setApplication(application);
    }

    @Test
    public void creates_json_response_for_get_request() {
        Request request = new Request(Method.GET, "/");
        Response response = new Response(request);
        executeRequest(request, response);

        System.out.println(response.getEntityAsText());
        assertThat(response.getStatus().isSuccess(), is(equalTo(true)));
        assertThat(response.getEntityAsText(), is(notNullValue()));
        assertThat(response.getEntity(), is(instanceOf(JacksonRepresentation.class)));
        assertThat(response.getEntityAsText(), containsString("\"success\":true"));
    }

    private void executeRequest(Request request, Response response) {
        resource.init(new Context(), request, response);
        resource.handle();
    }

}
