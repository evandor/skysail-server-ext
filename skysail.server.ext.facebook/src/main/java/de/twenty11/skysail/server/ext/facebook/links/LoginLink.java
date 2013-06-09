package de.twenty11.skysail.server.ext.facebook.links;

import de.twenty11.skysail.common.navigation.LinkedPage;
import de.twenty11.skysail.server.ext.facebook.FacebookApplication;
import de.twenty11.skysail.server.ext.facebook.util.LinkUtils;

public class LoginLink implements LinkedPage {

    private FacebookApplication facebookApp;
    private String currentUser;

    public LoginLink(FacebookApplication app, String user) {
        this.facebookApp = app;
        this.currentUser = user;
    }

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
        return facebookApp.getAccessToken(currentUser) == null;
    }
}
