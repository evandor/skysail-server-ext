package de.twenty11.skysail.server.ext.mail;

import java.util.ArrayList;
import java.util.List;

import de.twenty11.skysail.server.restlet.ListServerResource2;

public class AccountsResource extends ListServerResource2<AccountDescriptor> {

    @Override
    protected List<AccountDescriptor> getData() {
        List<AccountDescriptor> results = new ArrayList<AccountDescriptor>();

        return results;
    }

}
