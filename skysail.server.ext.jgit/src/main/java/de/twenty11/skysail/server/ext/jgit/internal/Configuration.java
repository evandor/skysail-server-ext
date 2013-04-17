package de.twenty11.skysail.server.ext.jgit.internal;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.restlet.Application;
import org.restlet.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.services.ApplicationProvider;
import de.twenty11.skysail.server.services.ComponentProvider;

import java.io.File;
import java.io.IOException;

public class Configuration implements ApplicationProvider {

    private static Logger logger = LoggerFactory.getLogger(Configuration.class);
    private ComponentProvider componentProvider;
    private Component component;
    private MyApplication application;
    private Repository defaultRepository = null;

    public void activate() {
        logger.info("Activating Configuration Component for Skysail Bookmarks Extension");
        component = componentProvider.getComponent();
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        try {
            defaultRepository = builder.setGitDir(new File("C:\\tmp\\gittest"))
                    .readEnvironment() // scan environment GIT_* variables
                    //.findGitDir() // scan up the file system tree
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            defaultRepository.create(false);
        } catch (IllegalStateException e) {
            // Rep already exists
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        Git git = new Git(defaultRepository);
        AddCommand add = git.add();
        try {
            add.addFilepattern(".").call();
        } catch (GitAPIException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        application = new MyApplication(component.getContext(), defaultRepository);
    }

    public void deactivate() {
        logger.info("Deactivating Configuration Component for Skysail Bookmarks Extension");
        application = null;

    }

    public void setComponentProvider(ComponentProvider componentProvider) {
        this.componentProvider = componentProvider;
    }

    @Override
    public Application getApplication() {
        return application;
    }

}
