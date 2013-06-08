package de.twenty11.skysail.server.ext.facebook.resources;

import java.net.URL;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.restlet.data.Parameter;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.navigation.LinkedPage;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.UniqueResultServerResource2;
import de.twenty11.skysail.server.ext.facebook.FacebookApplication;
import de.twenty11.skysail.server.ext.facebook.domain.FacebookUser;
import de.twenty11.skysail.server.ext.facebook.util.LinkUtils;

public class MeResource extends UniqueResultServerResource2<FacebookUser> {

    private ObjectMapper mapper = new ObjectMapper();
    private String meOnFacebookUrl;
    private String token;

    @Override
    protected void doInit() throws ResourceException {
        meOnFacebookUrl = "https://graph.facebook.com/1395451850";
        getTokenInCaseOfLoginRequest();
    }

    @Override
    @Get("html|json")
    public SkysailResponse<FacebookUser> getEntity() {
        registerLinkedPage(new LinkedPage() {

            @Override
            public String getLinkText() {
                return "Login to Facebook";
            }

            @Override
            public String getHref() {
                return LinkUtils.getFacebookLoginUrl();
            }

            @Override
            public boolean applicable() {
                return token == null;
            }
        });

        return getEntity("Me on Facebook");
    }

    @Override
    protected FacebookUser getData() {
        JsonNode jsonRootNode;
        try {
            jsonRootNode = mapper.readTree(new URL(meOnFacebookUrl));
            return new FacebookUser(jsonRootNode);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void getTokenInCaseOfLoginRequest() {
        Parameter code = getRequest().getResourceRef().getQueryAsForm().getFirst("code");
        if (code != null) {
            FacebookApplication facebookApp = (FacebookApplication) getApplication();
            String appSecret = facebookApp.getConfigForKey("facebookAppSecret");
            token = LinkUtils.getFacebookAccessToken(code.getValue(), appSecret);
            if (token != null) {
                String[] split = token.split("=");
                if (split.length == 1 && split[0].equals("access_token")) {
                    facebookApp.setAccessToken(getRequest().getChallengeResponse().getIdentifier(), split[1]);
                }
            }
        }
    }

}
