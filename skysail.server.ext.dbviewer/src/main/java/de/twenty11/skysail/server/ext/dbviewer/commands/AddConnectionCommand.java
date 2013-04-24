package de.twenty11.skysail.server.ext.dbviewer.commands;

import java.util.List;

import de.twenty11.skysail.common.commands.Command;

public class AddConnectionCommand implements Command {

    @Override
    public String getName() {
        return "Add Connection";
    }

    @Override
    public String getDescription() {
        return "Adds a new Connection";
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
