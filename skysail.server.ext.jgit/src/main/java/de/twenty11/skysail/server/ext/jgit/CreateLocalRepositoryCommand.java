package de.twenty11.skysail.server.ext.jgit;

import de.twenty11.skysail.common.commands.Command;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepository;

import java.io.IOException;

public class CreateLocalRepositoryCommand implements Command {

    private final LocalRepositoryDescriptor repositoryDescriptor;

    public CreateLocalRepositoryCommand(LocalRepositoryDescriptor repositoryDescriptor) {
        this.repositoryDescriptor = repositoryDescriptor;
    }

    @Override
    public String getName() {
        return "Create";
    }

    @Override
    public String getDescription() {
        return "creates new repository at given location";
    }

    @Override
    // TODO
    public boolean applicable() {
        return true;
    }

    @Override
    public void execute() {
        if (applicable()) {
            String path = repositoryDescriptor.getPath();
            FileRepository localRepo = null;
            try {
                //localRepo = new FileRepository(path + "/.git");
                //Git git = new Git(localRepo);
                Repository newRepo = new FileRepository(path + ".git");
                newRepo.create();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
