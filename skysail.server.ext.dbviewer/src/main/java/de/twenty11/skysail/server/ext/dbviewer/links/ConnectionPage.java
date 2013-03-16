package de.twenty11.skysail.server.ext.dbviewer.links;

import de.twenty11.skysail.common.navigation.LinkedPage;

public class ConnectionPage implements LinkedPage {

    @Override
    public boolean applicable() {
        return true;
    }

    @Override
    public String getHref() {
        return "connections/";
    }

    @Override
    public String getLinkText() {
        return "add Connection";
    }

}
