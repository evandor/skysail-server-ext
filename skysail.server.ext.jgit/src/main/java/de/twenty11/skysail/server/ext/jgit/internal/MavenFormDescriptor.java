package de.twenty11.skysail.server.ext.jgit.internal;

import javax.validation.constraints.NotNull;

import de.twenty11.skysail.common.forms.Field;
import de.twenty11.skysail.common.forms.Form;

@Form(name = "MavenForm")
public class MavenFormDescriptor {

    private String workingDir;

    @Field
    @NotNull(message = "maven command may not be null")
    private String command;

    public MavenFormDescriptor(String cmd, String workingDir) {
        this.command = cmd;
        this.workingDir = workingDir;
    }

    public MavenFormDescriptor() {
    }

    public String getCommand() {
        return command;
    }

    public String getWorkingDir() {
        return workingDir;
    }

    @Override
    public String toString() {
        return workingDir + "> " + command;
    }
}
