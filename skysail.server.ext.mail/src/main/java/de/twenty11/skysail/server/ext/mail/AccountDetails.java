package de.twenty11.skysail.server.ext.mail;

import org.codehaus.jackson.annotate.JsonIgnore;

import de.twenty11.skysail.common.PresentableHeader;

public class AccountDetails extends AccountDescriptor {

    @Override
    @JsonIgnore
    public PresentableHeader getHeader() {
        return new PresentableHeader.Builder(getName()).setLink(getName() + "/boxes").build();
    }

}
