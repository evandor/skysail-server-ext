package de.twenty11.skysail.server.ext.facebook.domain.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.net.URL;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.twenty11.skysail.server.ext.facebook.domain.FacebookUser;
import de.twenty11.skysail.server.ext.facebook.util.HttpUtils;
import de.twenty11.skysail.server.ext.facebook.util.LinkUtils;

public class FacebookUserTest {

    private ObjectMapper mapper = new ObjectMapper();
    private String meOnFBLink = "https://graph.facebook.com/1395451850";
    private HttpUtils httpUtils;

    @Before
    public void setUp() throws Exception {
        httpUtils = new HttpUtils();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFacebookAccess() throws Exception {
        JsonNode jsonRootNode = mapper.readTree(new URL(meOnFBLink));
        FacebookUser me = new FacebookUser(jsonRootNode, null);
        assertThat(me.getId(), equalTo("1395451850"));
    }

    @Test
    public void testFacebookFriendsYieldsErrorMessage() throws Exception {
        String response = httpUtils.get(meOnFBLink + "/friends");

        JsonNode jsonRootNode = mapper.readTree(response);
        ObjectNode node = (ObjectNode) jsonRootNode.path("error");
        // System.out.println(node.get("message"));
        assertThat(node, is(not((nullValue()))));
    }

    @Test
    public void loginSuccessful() throws Exception {
        String response = httpUtils.get(LinkUtils.getFacebookLoginUrl());
        System.out.println(response);
    }

}
