package de.twenty11.skysail.server.ext.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import de.twenty11.skysail.common.commands.Command;

public class StopCommand implements Command {

    private Scheduler scheduler;

    public StopCommand(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public String getName() {
        return "stop scheduler";
    }

    @Override
    public String getDescription() {
        return "stop quartz scheduler";
    }

    @Override
    public boolean applicable() {
        try {
            return scheduler.isStarted();
        } catch (SchedulerException e) {
            return false;
        }
    }

    @Override
    public void execute() {
        if (!applicable()) {
            return;
        }
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
