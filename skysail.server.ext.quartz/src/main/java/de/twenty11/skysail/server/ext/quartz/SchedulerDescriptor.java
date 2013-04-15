package de.twenty11.skysail.server.ext.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

public class SchedulerDescriptor {

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

}
