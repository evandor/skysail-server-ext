package de.twenty11.skysail.server.ext.bookmarks.test;

import org.junit.Before;
import org.junit.Test;

import de.twenty11.skysail.server.ext.bookmarks.MyRootResource;

import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.equalTo;
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

}
