package de.twenty11.skysail.server.ext.quartz;

import org.restlet.resource.ResourceException;

import de.twenty11.skysail.server.ext.quartz.internal.MyApplication;
import de.twenty11.skysail.server.restlet.UniqueResultServerResource2;

public class SchedulerResource extends UniqueResultServerResource2<SchedulerDescriptor> {

    @Override
    protected void doInit() throws ResourceException {
    }

    @Override
    protected SchedulerDescriptor getData() {
        MyApplication application = (MyApplication) getApplication();
        return new SchedulerDescriptor(application.getScheduler());
    }

}
