package de.twenty11.skysail.server.ext.jgit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.restlet.resource.Get;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.common.responses.SuccessResponse;
import de.twenty11.skysail.common.selfdescription.ResourceDetails;
import de.twenty11.skysail.server.restlet.ListServerResource2;

/**
 * Restlet Root Resource for dbViewer application.
 * 
 */
public class MyRootResource<T extends ResourceDetails> extends ListServerResource2<ResourceDetails> {

    public class JGitResourceDetails extends ResourceDetails {

        public JGitResourceDetails(String path, String text, String finder, String desc) {
            super(path, text, finder, desc);
        }

        @Override
        public Map<String, Object> getContent() {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("desc", "Defines a local git repository");
            return result;
        }

    }

    public MyRootResource() {
        setAutoDescribing(false);
        setName("jgit root resource");
        setDescription("The root resource of the jgit application");
    }

    @Override
    @Get("html|json|csv")
    public SkysailResponse<List<ResourceDetails>> getEntities() {
        SkysailResponse<List<ResourceDetails>> entities = getEntities("Public API of Skysail JGit Extension");
        List<ResourceDetails> data = entities.getData();
        List<ResourceDetails> result = new ArrayList<ResourceDetails>();
        for (ResourceDetails rd : data) {
            result.add(new JGitResourceDetails(rd.getPath(), rd.getText(), rd.getFinder(), rd.getDesc()));
        }
        return new SuccessResponse<List<ResourceDetails>>(result);
    }

    @Override
    protected List<ResourceDetails> getData() {
        return allMethods();
    }

}
