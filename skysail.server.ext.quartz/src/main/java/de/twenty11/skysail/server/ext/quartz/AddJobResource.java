package de.twenty11.skysail.server.ext.quartz;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.restlet.data.Form;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.common.responses.ConstraintViolationsResponse;
import de.twenty11.skysail.common.responses.FailureResponse;
import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.responses.SuccessResponse;
import de.twenty11.skysail.server.ext.quartz.internal.MyApplication;
import de.twenty11.skysail.server.restlet.UniqueResultServerResource2;

@Presentation(preferred = PresentationStyle.EDIT)
public class AddJobResource extends UniqueResultServerResource2<JobDescriptor> {

    /** slf4j based logger implementation */
    private static Logger logger = LoggerFactory.getLogger(AddJobResource.class);

    private class SysoutJob implements Job {

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("hier");
        }

    }

    @Override
    protected void doInit() throws ResourceException {
    }

    @Get("html")
    public FormResponse<JobDescriptor> getJob() {
        JobDescriptor jobDescriptor = new JobDescriptor();
        setMessage("Adding new Job");
        return new FormResponse<JobDescriptor>(jobDescriptor, "../jobs/");
    }

    @Post("x-www-form-urlencoded:html")
    public SkysailResponse<JobDescriptor> addConnection(Form form) {
        logger.info("trying to persist connection");
        JobDescriptor details = new JobDescriptor(form.getFirstValue("name"));
        Set<ConstraintViolation<JobDescriptor>> violations = getValidator().validate(details);
        if (violations.size() > 0) {
            // if (constraintViolations.getMsg() != null) {
            logger.warn("contraint violations found on {}: {}", details, violations);
            // return new FailureResponse<ConstraintViolations<T>>(constraintViolations);
            return new ConstraintViolationsResponse<JobDescriptor>(details, violations);
        }
        MyApplication application = (MyApplication) getApplication();
        Scheduler scheduler = application.getScheduler();

        JobDetail jobDetail = new JobDetail(details.getName(), "skysail", SysoutJob.class, true, true, true);
        try {
            scheduler.addJob(jobDetail, false);
        } catch (SchedulerException e) {
            return new FailureResponse<JobDescriptor>(e);
        }
        return new SuccessResponse<JobDescriptor>();
    }

    @Override
    protected JobDescriptor getData() {
        // TODO Auto-generated method stub
        return null;
    }

}
