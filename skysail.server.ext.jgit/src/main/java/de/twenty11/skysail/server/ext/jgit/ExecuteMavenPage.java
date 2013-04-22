package de.twenty11.skysail.server.ext.jgit;

import de.twenty11.skysail.common.navigation.LinkedPage;

public class ExecuteMavenPage implements LinkedPage {

    private LocalRepositoryDescriptor repositoryDescriptor;

    public ExecuteMavenPage(LocalRepositoryDescriptor result) {
        this.repositoryDescriptor = result;
    }

    @Override
    public boolean applicable() {
        return true;
    }

    @Override
    public String getHref() {
        return repositoryDescriptor.getName() + "/maven";
    }

    @Override
    public String getLinkText() {
        return "Run Maven...";
    }

}
