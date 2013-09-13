package de.twenty11.skysail.server.ext.facebook.resources;

import java.net.URL;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.restlet.data.Form;
import org.restlet.data.Parameter;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.UniqueResultServerResource2;
import de.twenty11.skysail.server.ext.facebook.FacebookApplication;
import de.twenty11.skysail.server.ext.facebook.domain.FacebookUser;
import de.twenty11.skysail.server.ext.facebook.links.LoginLink;
import de.twenty11.skysail.server.ext.facebook.links.LogoutLink;
import de.twenty11.skysail.server.ext.facebook.util.FacebookStringUtils;
import de.twenty11.skysail.server.ext.facebook.util.LinkUtils;

/**
 * A resource representing "me" as a facebook user.
 * 
 * Handles login to facebook as well.
 * 
 */
public class MeResource extends UniqueResultServerResource2<FacebookUser> {

    private ObjectMapper mapper = new ObjectMapper();
    private String meOnFacebookUrl;
    private FacebookApplication facebookApp;
    private String currentUser;

    @Override
    protected void doInit() throws ResourceException {
        facebookApp = (FacebookApplication) getApplication();
        currentUser = getRequest().getChallengeResponse().getIdentifier();
        meOnFacebookUrl = "https://graph.facebook.com/1395451850";
        getTokenInCaseOfLoginRequest();
    }

    @Override
    @Get("html|json")
    public SkysailResponse<FacebookUser> getEntity() {
        registerLinkedPage(new LoginLink(facebookApp, currentUser));
        registerLinkedPage(new LogoutLink(facebookApp, currentUser));
        return getEntity("Me on Facebook");
    }

    @Override
    protected FacebookUser getData() {
        JsonNode jsonRootNode;
        try {
            jsonRootNode = mapper.readTree(new URL(meOnFacebookUrl));
            return new FacebookUser(jsonRootNode, facebookApp.getAccessToken(currentUser));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void getTokenInCaseOfLoginRequest() {
        Parameter code = getRequest().getResourceRef().getQueryAsForm().getFirst("code");
        if (code == null) {
            return;
        }
        if (code.getValue().equals("logout")) {
            facebookApp.logoutUser(currentUser);
        } else {
            String appSecret = facebookApp.getConfigForKey("facebookAppSecret");
            String strContainingToken = LinkUtils.queryForFacebookAccessToken(code.getValue(), appSecret);
            if (strContainingToken != null) {
                String token = FacebookStringUtils.extractAccessTokenFromFbResponse(strContainingToken);
                if (token != null && currentUser != null) {
                    facebookApp.setAccessToken(currentUser, token);
                }
            }
        }
    }

    @Override
    public FacebookUser getData(Form form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SkysailResponse<?> addEntity(FacebookUser entity) {
        // TODO Auto-generated method stub
        return null;
    }

}
