package de.twenty11.skysail.server.ext.jenkins;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;

import org.restlet.data.Form;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.jenkins.internal.MyApplication;
import de.twenty11.skysail.server.restlet.UniqueResultServerResource;

@Presentation(preferred = PresentationStyle.EDIT)
public class AddJenkinsResource extends UniqueResultServerResource<JenkinsDetails> {

    /** slf4j based logger implementation */
    private static Logger logger = LoggerFactory.getLogger(AddJenkinsResource.class);

    public AddJenkinsResource() {
        setName("new jenkins installation ");
        setDescription("Providing a form to add a new jenkins installation");
    }

    @Get("html")
    public FormResponse<JenkinsDetails> getJenkinsForm() {
        JenkinsDetails details = new JenkinsDetails();
        setMessage("Adding new Jenkins Installation");
        return new FormResponse<JenkinsDetails>(details, "../installation/");
    }

    @Post("x-www-form-urlencoded:html")
    public SkysailResponse<JenkinsDetails> addConnection(Form form) {
        logger.info("trying to persist connection");
        EntityManager em = ((MyApplication) getApplication()).getEntityManager();
        JenkinsDetails details = new JenkinsDetails(form.getFirstValue("name"), form.getFirstValue("location"));
        Set<ConstraintViolation<JenkinsDetails>> constraintViolations = getValidator().validate(details);
        return addEntity(em, details, constraintViolations);
    }


}