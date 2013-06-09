package de.twenty11.skysail.server.ext.facebook.resources;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twenty11.skysail.server.ext.facebook.FacebookApplication;
import de.twenty11.skysail.server.ext.facebook.domain.FacebookUser;

public class FriendsResource extends ListServerResource2<FacebookUser> {

    private ObjectMapper mapper = new ObjectMapper();
    private String myFriendsOnFacebookUrl;
    private FacebookApplication facebookApp;
    private String currentUser;

    @Override
    protected void doInit() throws ResourceException {
        facebookApp = (FacebookApplication) getApplication();
        currentUser = getRequest().getChallengeResponse().getIdentifier();
        myFriendsOnFacebookUrl = "https://graph.facebook.com/1395451850/friends";
    }

    @Override
    protected List<FacebookUser> getData() {
        JsonNode jsonRootNode;
        List<FacebookUser> friends = new ArrayList<FacebookUser>();
        try {
            String friendsLink = myFriendsOnFacebookUrl + "?access_token=";
            friendsLink += facebookApp.getAccessToken(currentUser);
            jsonRootNode = mapper.readTree(new URL(friendsLink));
            JsonNode data = jsonRootNode.get("data");
            if (data != null) {
                Iterator<JsonNode> elements = data.getElements();
                while (elements.hasNext()) {
                    JsonNode next = elements.next();
                    friends.add(new FacebookUser(next, null));
                }
            }
            return friends;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
