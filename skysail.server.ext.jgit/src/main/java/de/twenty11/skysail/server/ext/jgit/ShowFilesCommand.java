package de.twenty11.skysail.server.ext.jgit;

import java.util.List;

import de.twenty11.skysail.common.commands.Command;

public class ShowFilesCommand implements Command {

    @Override
    public String getName() {
        return "Show Files";
    }

    @Override
    public String getDescription() {
        return "shows contents of this directory";
    }

    @Override
    public boolean applicable() {
        return true;
    }

    @Override
    public void execute() {

    }

    @Override
    public List<String> executionMessages() {
        // TODO Auto-generated method stub
        return null;
    }

}
