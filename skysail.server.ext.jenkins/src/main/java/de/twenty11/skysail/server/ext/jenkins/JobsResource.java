package de.twenty11.skysail.server.ext.jenkins;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.jenkins.internal.MyApplication;
import de.twenty11.skysail.server.restlet.ListServerResource2;

@Presentation(preferred = PresentationStyle.TABLE)
public class JobsResource extends ListServerResource2<JobsDetails> {

    private String installationName;

    private ObjectMapper mapper = new ObjectMapper();

    public JobsResource() {
        setName("jenkins installations");
    }

    @Override
    protected void doInit() throws ResourceException {
        installationName = (String) getRequest().getAttributes().get("name");
        setDescription("list of all jobs of jenkins installation " + installationName);
    }

    @Override
    @Get("html|json|csv|pdf")
    public SkysailResponse<List<JobsDetails>> getEntities() {
        return super.getEntities("all jobs from jenkins installation " + installationName);
    }

    @Override
    protected List<JobsDetails> getData() {
        MyApplication app = (MyApplication)getApplication();
        EntityManager em = app.getEntityManager();
        Query query = em.createQuery("SELECT c FROM JenkinsDetails c WHERE c.name = :name");
        query.setParameter("name", installationName);
        JenkinsDetails jenkinsDetails = (JenkinsDetails) query.getSingleResult();
        String jenkinsLocation = jenkinsDetails.getLocation();
        String jobUrl = jenkinsLocation + "api/json";

        try {
            List<JobsDetails> results = new ArrayList<JobsDetails>();
            JsonNode jsonRootNode = mapper.readTree(new URL(jobUrl));
            JsonNode jobs = jsonRootNode.path("jobs");
            Iterator<JsonNode> ite = jobs.getElements();
            while (ite.hasNext()) {
                JsonNode next = ite.next();
                results.add(new JobsDetails(next.path("name").getTextValue(), next.path("url").getTextValue(), next
                        .path("color").getTextValue()));
            }
            return results;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
