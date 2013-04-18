package de.twenty11.skysail.server.ext.mail;

import de.twenty11.skysail.common.navigation.LinkedPage;

public class AddAccountPage implements LinkedPage {

    @Override
    public boolean applicable() {
        return true;
    }

    @Override
    public String getHref() {
        return "accounts/";
    }

    @Override
    public String getLinkText() {
        return "add Account";
    }

}
