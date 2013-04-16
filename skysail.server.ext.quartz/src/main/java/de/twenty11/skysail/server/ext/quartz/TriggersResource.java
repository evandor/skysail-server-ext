package de.twenty11.skysail.server.ext.quartz;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import de.twenty11.skysail.server.ext.quartz.internal.MyApplication;
import de.twenty11.skysail.server.restlet.ListServerResource2;

public class TriggersResource extends ListServerResource2<TriggerDescriptor> {

    @Override
    protected List<TriggerDescriptor> getData() {
        List<TriggerDescriptor> results = new ArrayList<TriggerDescriptor>();
        MyApplication application = (MyApplication) getApplication();
        Scheduler scheduler = application.getScheduler();
        try {
            String[] triggerGroupNames = scheduler.getTriggerGroupNames();
            if (triggerGroupNames == null) {
                return results;
            }
            for (String triggerGroupName : triggerGroupNames) {
                String[] triggerNames = scheduler.getTriggerNames(triggerGroupName);
                for (String triggerName : triggerNames) {
                    results.add(new TriggerDescriptor(scheduler.getJobDetail(triggerName, triggerGroupName)));
                }
            }
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return results;
    }

}
