package de.twenty11.skysail.server.ext.quartz;

import org.quartz.Scheduler;
import org.restlet.data.Form;

import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.quartz.internal.MyApplication;
import de.twenty11.skysail.server.restlet.AddServerResource;

public class AddTriggerResource extends AddServerResource<TriggerDescriptor> {

    @Override
    public FormResponse<TriggerDescriptor> createForm() {
        setMessage("new Trigger");
        return new FormResponse<TriggerDescriptor>(new TriggerDescriptor(), "../triggers/");
    }

    @Override
    public TriggerDescriptor getData(Form form) {
        return new TriggerDescriptor(form.getFirstValue("name"));
    }

    @Override
    public SkysailResponse<TriggerDescriptor> addEntity(TriggerDescriptor entity) {
        MyApplication application = (MyApplication) getApplication();
        Scheduler scheduler = application.getScheduler();

        return null;
    }

    @Override
    public SkysailResponse addFromForm(Form form) {
        return null; // To change body of implemented methods use File | Settings | File Templates.
    }
}
