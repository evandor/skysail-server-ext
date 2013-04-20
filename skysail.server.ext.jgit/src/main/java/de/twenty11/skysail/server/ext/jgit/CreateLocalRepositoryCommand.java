package de.twenty11.skysail.server.ext.jgit;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepository;

import de.twenty11.skysail.common.commands.Command;

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
    public boolean applicable() {
        String path = repositoryDescriptor.getPath() + ".git";
        return !new File(path).exists();
    }

    @Override
    public void execute() {
        if (applicable()) {
            String path = repositoryDescriptor.getPath();
            FileRepository localRepo = null;
            try {
                Repository newRepo = new FileRepository(path + ".git");
                newRepo.create();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
