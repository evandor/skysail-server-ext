package de.twenty11.skysail.server.ext.bookmarks.test;

import org.junit.Before;
import org.junit.Test;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Method;

import de.twenty11.skysail.server.ext.bookmarks.MyRootResource;

import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


public class MyRootResourceTest {

    private MyRootResource rootResource;

    @Before
    public void setUp() throws Exception {
        // System.out.println("Hier");
    }

    @Test
    public void has_proper_name() {
        rootResource = new MyRootResource();
        Request request = new Request(Method.GET, "/");
        Response response = new Response(request);
        rootResource.init(new Context(), request, response);
        rootResource.handle();

        assertThat(response.getStatus().isSuccess(), is(equalTo(true)));
        assertThat(response.getEntityAsText(), is(notNullValue()));
    }

    // @Test
    // public void returns_methods() {
    // SkysailResponse<List<ResourceDetails>> methods = rootResource.getEntities();
    // assertThat(methods.getData().size(), is(greaterThan(1)));
    // }

}
