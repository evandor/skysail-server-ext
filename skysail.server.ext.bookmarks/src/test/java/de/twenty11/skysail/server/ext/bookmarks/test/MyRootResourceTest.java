package de.twenty11.skysail.server.ext.bookmarks.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.selfdescription.ResourceDetails;
import de.twenty11.skysail.server.ext.bookmarks.MyRootResource;

import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;


public class MyRootResourceTest {

    private MyRootResource rootResource;

    @Before
    public void setUp() throws Exception {
        rootResource = new MyRootResource();
    }

    @Test
    public void has_proper_name() {
        assertThat(rootResource.getName(), is(equalTo("bookmarks root resource")));
    }

    @Test
    public void returns_methods() {
        SkysailResponse<List<ResourceDetails>> methods = rootResource.getEntities();
        assertThat(methods.getData().size(), is(greaterThan(1)));
    }

}
