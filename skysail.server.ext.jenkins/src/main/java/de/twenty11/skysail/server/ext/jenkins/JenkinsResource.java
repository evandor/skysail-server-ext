package de.twenty11.skysail.server.ext.jenkins;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.restlet.resource.Get;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.jenkins.internal.MyApplication;
import de.twenty11.skysail.server.restlet.ListServerResource2;

public class JenkinsResource extends ListServerResource2<JenkinsDetails> {

    public JenkinsResource() {
        setName("jenkins installations");
        setDescription("list of all jenkins installations");
    }

    @Override
    @Get("html|json|csv")
    public SkysailResponse<List<JenkinsDetails>> getEntities() {
        return super.getEntities("all available jenkins installations");
    }

    @Override
    protected List<JenkinsDetails> getData() {
        EntityManager em = ((MyApplication) getApplication()).getEntityManager();
        List<JenkinsDetails> resultList = em.createQuery("SELECT c FROM JenkinsDetails c").getResultList();
        List<JenkinsDetails> filteredResults = new ArrayList<JenkinsDetails>();
        for (JenkinsDetails details : resultList) {
            if (filterMatches(details)) {
                filteredResults.add(details);
            }
        }
        return filteredResults;
    }

}