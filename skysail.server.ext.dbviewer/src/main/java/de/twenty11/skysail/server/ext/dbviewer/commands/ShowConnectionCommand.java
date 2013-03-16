package de.twenty11.skysail.server.ext.dbviewer.commands;

import de.twenty11.skysail.common.commands.Command;

public class ShowConnectionCommand implements Command {

    @Override
    public String getName() {
        // The buttons text will be "Add Connection", even though we will just show the page
        // where the connection is defined".
        return "Add Connection";
    }

    @Override
    public String getDescription() {
        return "Opens a page to add a new Connection";
    }

    @Override
    public boolean applicable() {
        return true;
    }

    @Override
    public void execute() {

    }

}
