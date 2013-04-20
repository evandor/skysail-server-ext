package de.twenty11.skysail.server.ext.jgit;

import de.twenty11.skysail.common.navigation.LinkedPage;

//@Presentation(preferred = PresentationStyle.EDIT)
public class ClonePage implements LinkedPage {

    private LocalRepositoryDescriptor repositoryDescriptor;

    public ClonePage(LocalRepositoryDescriptor result) {
        this.repositoryDescriptor = result;
    }

    @Override
    public boolean applicable() {
        return true;
    }

    @Override
    public String getHref() {
        return repositoryDescriptor.getName() + "/cloneform";
    }

    @Override
    public String getLinkText() {
        return "Clone...";
    }

}
