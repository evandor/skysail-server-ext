package de.twenty11.skysail.server.ext.facebook.resources;

import java.net.URL;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twenty11.skysail.server.ext.facebook.domain.FacebookUser;

public class FriendsResource extends ListServerResource2<FacebookUser> {

    private ObjectMapper mapper = new ObjectMapper();
    private String myFriendsOnFacebookUrl;

    @Override
    protected void doInit() throws ResourceException {
        myFriendsOnFacebookUrl = "https://graph.facebook.com/1395451850/friends";
    }

    @Override
    protected List<FacebookUser> getData() {
        JsonNode jsonRootNode;
        try {
            jsonRootNode = mapper.readTree(new URL(myFriendsOnFacebookUrl));
            return null;// new FacebookUser(jsonRootNode);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
