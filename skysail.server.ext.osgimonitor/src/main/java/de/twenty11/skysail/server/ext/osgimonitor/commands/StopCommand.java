package de.twenty11.skysail.server.ext.osgimonitor.commands;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

import de.twenty11.skysail.common.commands.Command;

public class StopCommand implements Command {

    private final Bundle bundle;

    public StopCommand(Bundle bundle) {
        Validate.notNull(bundle, "bundle may not be null");
        this.bundle = bundle;
    }

    @Override
    public boolean applicable() {
        return (bundle.getState() == Bundle.ACTIVE);
    }

    @Override
    public void execute() {
        try {
            bundle.stop();
        } catch (BundleException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return "Stop Bundle";
    }

    @Override
    public String getDescription() {
        return "Stops the bundle (available if bundle is started)";
    }

    @Override
    public List<String> executionMessages() {
        // TODO Auto-generated method stub
        return null;
    }
}
