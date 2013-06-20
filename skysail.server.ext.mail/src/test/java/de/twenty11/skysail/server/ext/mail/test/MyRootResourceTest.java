package de.twenty11.skysail.server.ext.mail.test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Method;
import org.restlet.ext.jackson.JacksonRepresentation;

import de.twenty11.skysail.server.ext.mail.MyRootResource;
import de.twenty11.skysail.server.ext.mail.internal.MyApplication;

public class MyRootResourceTest {

    private MyRootResource rootResource;
    private MyApplication application;

    @Before
    public void setUp() throws Exception {
        application = new MyApplication(null, null);
        application.createInboundRoot();
        rootResource = new MyRootResource();
        rootResource.setApplication(application);
    }

    @Test
    @Ignore
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
        rootResource.init(new Context(), request, response);
        rootResource.handle();
    }

}
