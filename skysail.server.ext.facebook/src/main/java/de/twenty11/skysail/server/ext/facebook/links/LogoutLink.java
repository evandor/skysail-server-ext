package de.twenty11.skysail.server.ext.facebook.links;

import de.twenty11.skysail.common.navigation.LinkedPage;
import de.twenty11.skysail.server.ext.facebook.FacebookApplication;

public class LogoutLink implements LinkedPage {

    private FacebookApplication facebookApp;
    private String currentUser;

    public LogoutLink(FacebookApplication app, String user) {
        this.facebookApp = app;
        this.currentUser = user;
    }

    @Override
    public String getLinkText() {
        return "Logout from Facebook";
    }

    @Override
    public String getHref() {
        return "?code=logout";
    }

    @Override
    public boolean applicable() {
        return facebookApp.getAccessToken(currentUser) != null;
    }
}
