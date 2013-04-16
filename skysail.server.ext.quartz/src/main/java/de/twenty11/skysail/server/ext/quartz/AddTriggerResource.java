package de.twenty11.skysail.server.ext.quartz;

import de.twenty11.skysail.common.responses.FailureResponse;
import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.responses.SuccessResponse;
import de.twenty11.skysail.server.ext.quartz.internal.MyApplication;
import de.twenty11.skysail.server.restlet.AddServerResource;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.restlet.data.Form;
import org.restlet.resource.ServerResource;

public class AddTriggerResource extends AddServerResource<TriggerDescriptor> {

    @Override
    protected FormResponse<TriggerDescriptor> createForm() {
        setMessage("new Trigger");
        return new FormResponse<TriggerDescriptor>(new TriggerDescriptor(), "../triggers/");
    }

    @Override
    protected TriggerDescriptor getData(Form form) {
        return new TriggerDescriptor(form.getFirstValue("name"));
    }

    @Override
    protected SkysailResponse<TriggerDescriptor> addEntity(TriggerDescriptor entity) {
        MyApplication application = (MyApplication) getApplication();
        Scheduler scheduler = application.getScheduler();

        JobDetail jobDetail = new JobDetail(entity.getName(), "skysail", SysoutJob.class, true, true, true);
        try {
            scheduler.
        } catch (SchedulerException e) {
            return new FailureResponse<JobDescriptor>(e);
        }
        return new SuccessResponse<JobDescriptor>();
    }

    @Override
    protected SkysailResponse addFromForm(Form form) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
