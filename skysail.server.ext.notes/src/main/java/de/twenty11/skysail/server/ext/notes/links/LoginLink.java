package de.twenty11.skysail.server.ext.notes.links;

import de.twenty11.skysail.common.navigation.LinkedPage;
import de.twenty11.skysail.server.ext.notes.NotesApplication;
import de.twenty11.skysail.server.ext.notes.util.LinkUtils;

public class LoginLink implements LinkedPage {

    private NotesApplication notesApp;
    private String currentUser;

    public LoginLink(NotesApplication app, String user) {
        this.notesApp = app;
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
        return notesApp.getAccessToken(currentUser) == null;
    }
}
