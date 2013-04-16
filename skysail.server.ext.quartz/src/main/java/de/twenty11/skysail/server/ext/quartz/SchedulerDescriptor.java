package de.twenty11.skysail.server.ext.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import de.twenty11.skysail.common.AbstractPresentable;

public class SchedulerDescriptor extends AbstractPresentable {

    private String schedulerName = "unknown";

    private boolean started;

    public SchedulerDescriptor(Scheduler scheduler) {
        try {
            this.schedulerName = scheduler.getSchedulerName();
            this.started = scheduler.isStarted();
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getSchedulerName() {
        return schedulerName;
    }

    public boolean isStarted() {
        return started;
    }

}
