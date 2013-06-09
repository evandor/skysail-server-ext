package de.twenty11.skysail.server.ext.facebook.util;

public class FacebookStringUtils {

    /**
     * from https://developers.facebook.com/docs/facebook-login/login-flow-for-web-no-jssdk/:
     * 
     * assuming string looks like 'access_token={access-token}&expires={seconds-til-expiration}'
     */
    public static String extractAccessTokenFromFbResponse(String strContainingToken) {
        if (strContainingToken == null || strContainingToken.trim().length() == 0) {
            return null;
        }
        String[] ampSplit = strContainingToken.split("&");
        if (ampSplit.length != 2) {
            return null;
        }
        String[] equalSplit = ampSplit[0].split("=");
        if (equalSplit.length != 2 || !equalSplit[0].equals("access_token")) {
            return null;
        }
        return equalSplit[1];
    }

}
