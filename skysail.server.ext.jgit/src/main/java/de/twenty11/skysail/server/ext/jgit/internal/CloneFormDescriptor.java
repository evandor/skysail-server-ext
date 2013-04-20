package de.twenty11.skysail.server.ext.jgit.internal;

import javax.validation.constraints.NotNull;

import de.twenty11.skysail.common.forms.Field;
import de.twenty11.skysail.common.forms.Form;

@Form(name = "LocalRepositoryForm")
public class CloneFormDescriptor {

    public CloneFormDescriptor(String path) {
        this.remotePath = path;
    }

    public CloneFormDescriptor() {
    }

    @Field
    @NotNull(message = "remote path may not be null")
    private String remotePath;

    public String getRemotePath() {
        return remotePath;
    }
}
