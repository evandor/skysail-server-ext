package de.twenty11.skysail.server.ext.jenkins.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.selfdescription.ResourceDetails;
import de.twenty11.skysail.server.ext.jenkins.MyRootResource;
import de.twenty11.skysail.server.ext.jenkins.internal.MyApplication;

import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class MyRootResourceTest {

    private MyApplication myApplication;
    private MyRootResource myRootResource;

    @Before
    public void setUp() {
        myApplication = new MyApplication();
        myApplication.createInboundRoot();
        myRootResource = Mockito.spy(new MyRootResource());
        Mockito.when(myRootResource.getApplication()).thenReturn(myApplication);
    }

    @Test
    public void returns_data_when_getMethods_is_called() {
        SkysailResponse<List<ResourceDetails>> methods = myRootResource.getMethods();
        assertThat(methods.getData().size(), is(greaterThan(0)));
    }

}
