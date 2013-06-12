package de.twenty11.skysail.server.ext.notes.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkUtils {

    private static final String facebookAppId = "495131030558484";
    private static final String redirectUrl = "http://localhost:2013/facebook/me";
    private static final String loginLink = "https://www.facebook.com/dialog/oauth";
    private static final String accessTokenLink = "https://graph.facebook.com/oauth/access_token";

    private static Logger logger = LoggerFactory.getLogger(LinkUtils.class);

    public static String getFacebookLoginUrl() {
        return loginLink + "?client_id=" + facebookAppId + "&redirect_uri=" + redirectUrl;
    }

    public static String queryForFacebookAccessToken(String fb_code, String appSecret) {
        // @formatter:off
        String getAccessTokenLink = accessTokenLink 
                + "?client_id=" + facebookAppId 
                + "&redirect_uri=" + redirectUrl
                + "&client_secret=" + appSecret 
                + "&code=" + fb_code;
        // @formatter:on
        try {
            return new HttpUtils().get(getAccessTokenLink);
        } catch (Exception e) {
            logger.warn("got exception '{}' trying to access '{}'", e.getMessage(), getAccessTokenLink);
            return null;
        }
    }
}
