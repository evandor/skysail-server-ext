package de.twenty11.skysail.server.ext.jgit;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.twenty11.skysail.common.commands.Command;
import de.twenty11.skysail.server.ext.jgit.internal.MavenFormDescriptor;

public class ExecuteMavenCommand implements Command {

    private LocalRepositoryDescriptor repositoryDescriptor;
    private MavenFormDescriptor entity;

    public ExecuteMavenCommand(LocalRepositoryDescriptor repositoryDescriptor, MavenFormDescriptor entity) {
        this.repositoryDescriptor = repositoryDescriptor;
        this.entity = entity;
    }

    @Override
    public String getName() {
        return "Execute Maven";
    }

    @Override
    public String getDescription() {
        return "execute maven";
    }

    @Override
    public boolean applicable() {
        return true;
    }

    @Override
    public void execute() {
        if (applicable()) {
            try {
                // repositoryDescriptor.
                Process exec = Runtime.getRuntime().exec("mvn -version");
                InputStream errorStream = exec.getErrorStream();
                OutputStream outputStream = exec.getOutputStream();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
