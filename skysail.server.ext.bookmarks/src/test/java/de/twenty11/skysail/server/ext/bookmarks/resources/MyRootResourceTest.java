package de.twenty11.skysail.server.ext.bookmarks.resources;

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

import de.twenty11.skysail.server.ext.bookmarks.BookmarkApplication;
import de.twenty11.skysail.server.ext.bookmarks.resources.MyRootResource;

public class MyRootResourceTest {

    private MyRootResource rootResource;
    private BookmarkApplication application;

    @Before
    public void setUp() throws Exception {
        application = new BookmarkApplication(null, null);
        application.createInboundRoot();
        rootResource = new MyRootResource();
        rootResource.setApplication(application);
    }

    @Test
    public void dummy() {

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

    @Test
    @Ignore
    public void testApplication() {
        Request request = new Request(Method.GET, "http://localhost:8111/");
        Response response = new Response(request);
        application.handle(request, response);
        assertThat(response.getStatus().isSuccess(), is(equalTo(true)));
    }

    // public void testComponent() throws Exception {
    // MailServerComponent component = new MailServerComponent();
    // component.start();
    // Request request = new Request(Method.GET, "http://localhost:8111/");
    // Response response = new Response(request);
    // component.handle(request, response);
    // component.stop();
    // }

    private void executeRequest(Request request, Response response) {
        rootResource.init(new Context(), request, response);
        rootResource.handle();
    }

}
