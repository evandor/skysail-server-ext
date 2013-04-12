package de.twenty11.skysail.server.ext.activiti;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;

import de.twenty11.skysail.server.restlet.ListServerResource2;

public class RepositoryResource extends ListServerResource2<ProcessDescriptor> {

    private RepositoryService repositoryService;

    public RepositoryResource() {
        setName("repositoryService");

        ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
                .buildProcessEngine();
        repositoryService = processEngine.getRepositoryService();
    }

    @Override
    protected List<ProcessDescriptor> getData() {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> list = processDefinitionQuery.list();
        List<ProcessDescriptor> results = new ArrayList<ProcessDescriptor>();
        for (ProcessDefinition processDefinition : list) {
            results.add(new ProcessDescriptor(processDefinition));
        }
        return results;
    }

}
