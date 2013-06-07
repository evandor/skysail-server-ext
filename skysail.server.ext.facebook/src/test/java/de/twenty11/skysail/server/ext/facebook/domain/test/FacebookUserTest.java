package de.twenty11.skysail.server.ext.facebook.domain.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.net.URL;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.twenty11.skysail.server.ext.facebook.domain.FacebookUser;

public class FacebookUserTest {

    private ObjectMapper mapper = new ObjectMapper();
    private String jobUrl = "https://graph.facebook.com/1395451850";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFacebookAccess() throws Exception {
        JsonNode jsonRootNode = mapper.readTree(new URL(jobUrl));
        FacebookUser me = new FacebookUser(jsonRootNode);
        assertThat(me.getId(), equalTo("1395451850"));
    }

}
