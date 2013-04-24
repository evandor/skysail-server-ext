package de.twenty11.skysail.server.ext.osgimonitor.commands;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

import de.twenty11.skysail.common.commands.Command;

public class StartCommand implements Command {

    private final Bundle bundle;

    public StartCommand(Bundle bundle) {
        Validate.notNull(bundle, "bundle may not be null");
        this.bundle = bundle;
    }

    @Override
    public boolean applicable() {
        return (bundle.getState() != Bundle.STARTING && bundle.getState() != Bundle.ACTIVE);
    }

    @Override
    public void execute() {
        try {
            bundle.start();
        } catch (BundleException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return "Start Bundle";
    }

    @Override
    public String getDescription() {
        return "Starts the bundle (available if bundle hasn't been started yet)";
    }

    @Override
    public List<String> executionMessages() {
        // TODO Auto-generated method stub
        return null;
    }

}
