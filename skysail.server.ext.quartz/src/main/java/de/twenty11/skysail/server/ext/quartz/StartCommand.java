package de.twenty11.skysail.server.ext.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import de.twenty11.skysail.common.commands.Command;

public class StartCommand implements Command {

    private Scheduler scheduler;

    public StartCommand(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public String getName() {
        return "start scheduler";
    }

    @Override
    public String getDescription() {
        return "start quartz scheduler";
    }

    @Override
    public boolean applicable() {
        try {
            return !scheduler.isStarted();
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
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
