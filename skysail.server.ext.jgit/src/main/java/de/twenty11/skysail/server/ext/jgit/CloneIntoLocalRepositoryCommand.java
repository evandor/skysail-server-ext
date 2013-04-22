package de.twenty11.skysail.server.ext.jgit;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jgit.api.Git;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.commands.Command;
import de.twenty11.skysail.server.ext.jgit.internal.CloneFormDescriptor;

public class CloneIntoLocalRepositoryCommand implements Command {

    private static Logger logger = LoggerFactory.getLogger(CloneIntoLocalRepositoryCommand.class);

    private final LocalRepositoryDescriptor repositoryDescriptor;
    private CloneFormDescriptor entity;

    public CloneIntoLocalRepositoryCommand(LocalRepositoryDescriptor repositoryDescriptor, CloneFormDescriptor entity) {
        this.repositoryDescriptor = repositoryDescriptor;
        this.entity = entity;
    }

    @Override
    public String getName() {
        return "Clone";
    }

    @Override
    public String getDescription() {
        return "clones git repository to current local path";
    }

    @Override
    public boolean applicable() {
        CreateLocalRepositoryCommand createCommand = new CreateLocalRepositoryCommand(repositoryDescriptor);
        if (createCommand.applicable()) {
            return false;
        }
        String path = repositoryDescriptor.getPath() + ".git";
        if (!new File(path).exists()) {
            return false;
        }
        return true;
    }

    @Override
    public void execute() {
        if (applicable()) {
            if (entity == null || StringUtils.isBlank(entity.getRemotePath())) {
                return;
            }
            String path = repositoryDescriptor.getPath();
            String remotePath = entity.getRemotePath();
            logger.info("Attempting git cloning from '{}' into '{}'", remotePath, path);
            try {
                Git.cloneRepository().setURI(remotePath).setDirectory(new File(path)).call();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
