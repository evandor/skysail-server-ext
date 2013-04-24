package de.twenty11.skysail.server.ext.jgit;

import de.twenty11.skysail.common.navigation.LinkedPage;

public class ExecuteMavenPage implements LinkedPage {

    private String path;

    public ExecuteMavenPage(String path) {
        this.path = path;
    }

    @Override
    public boolean applicable() {
        return true;
    }

    @Override
    public String getHref() {
        return "../maven/" + path;
    }

    @Override
    public String getLinkText() {
        return "Run Maven...";
    }

}
