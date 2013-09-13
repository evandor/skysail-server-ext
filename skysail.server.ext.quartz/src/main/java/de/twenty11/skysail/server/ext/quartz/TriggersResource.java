package de.twenty11.skysail.server.ext.quartz;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.restlet.data.Form;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twenty11.skysail.server.ext.quartz.internal.MyApplication;

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

    @Override
    public TriggerDescriptor getData(Form form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SkysailResponse<?> addEntity(TriggerDescriptor entity) {
        // TODO Auto-generated method stub
        return null;
    }

}
