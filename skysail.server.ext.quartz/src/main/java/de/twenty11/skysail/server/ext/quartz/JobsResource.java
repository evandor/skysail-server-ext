package de.twenty11.skysail.server.ext.quartz;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import de.twenty11.skysail.server.ext.quartz.internal.MyApplication;
import de.twenty11.skysail.server.restlet.ListServerResource2;

public class JobsResource extends ListServerResource2<JobDescriptor> {

    @Override
    protected List<JobDescriptor> getData() {
        List<JobDescriptor> results = new ArrayList<JobDescriptor>();
        MyApplication application = (MyApplication) getApplication();
        Scheduler scheduler = application.getScheduler();
        try {
            String[] jobGroupNames = scheduler.getJobGroupNames();
            for (String jobGroupName : jobGroupNames) {
                String[] jobNames = scheduler.getJobNames(jobGroupName);
                for (String jobName : jobNames) {
                    results.add(new JobDescriptor(scheduler.getJobDetail(jobName, jobGroupName)));
                }
            }
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return results;
    }
}
