package de.twenty11.skysail.server.ext.jgit.internal;

import javax.validation.constraints.NotNull;

import de.twenty11.skysail.common.forms.Field;
import de.twenty11.skysail.common.forms.Form;

@Form(name = "MavenForm")
public class MavenFormDescriptor {

    @Field
    @NotNull(message = "maven command may not be null")
    private String command;

    public MavenFormDescriptor(String cmd) {
        this.command = cmd;
    }

    public MavenFormDescriptor() {
    }

    public String getCommand() {
        return command;
    }

}
