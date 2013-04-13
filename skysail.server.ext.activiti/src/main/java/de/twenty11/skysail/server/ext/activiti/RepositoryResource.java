package de.twenty11.skysail.server.ext.activiti;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.restlet.resource.Get;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.activiti.internal.MyApplication;
import de.twenty11.skysail.server.restlet.ListServerResource2;

public class RepositoryResource extends ListServerResource2<ProcessDescriptor> {

    public RepositoryResource() {
        setName("repositoryService");
        setDescription("description");
    }

    @Override
    @Get("html|json")
    public SkysailResponse<List<ProcessDescriptor>> getEntities() {
        return super.getEntities("all Entities");
    }

    @Override
    protected List<ProcessDescriptor> getData() {
        MyApplication application = (MyApplication) getApplication();
        RepositoryService repositoryService = application.getRepositoryService();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> list = processDefinitionQuery.list();
        List<ProcessDescriptor> results = new ArrayList<ProcessDescriptor>();
        for (ProcessDefinition processDefinition : list) {
            results.add(new ProcessDescriptor(processDefinition));
        }
        return results;
    }
}
