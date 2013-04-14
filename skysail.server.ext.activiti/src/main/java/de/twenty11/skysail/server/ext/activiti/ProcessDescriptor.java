package de.twenty11.skysail.server.ext.activiti;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.repository.ProcessDefinition;
import org.codehaus.jackson.annotate.JsonIgnore;

import de.twenty11.skysail.common.Presentable;
import de.twenty11.skysail.common.PresentableHeader;

public class ProcessDescriptor implements Presentable {

    private String id;
    private String name;
    private String deploymentId;
    private String category;
    private String description;
    private String key;
    private String resourceName;
    private int version;

    public ProcessDescriptor(ProcessDefinition processDefinition) {
        id = processDefinition.getId();
        name = processDefinition.getName();
        deploymentId = processDefinition.getDeploymentId();
        category = processDefinition.getCategory();
        description = processDefinition.getDescription();
        key = processDefinition.getKey();
        resourceName = processDefinition.getResourceName();
        version = processDefinition.getVersion();
    }

    @Override
    @JsonIgnore
    public PresentableHeader getHeader() {
        return new PresentableHeader.Builder(name).setLink("/activiti/repository/" + id).setImage("icon-th-large")
                .build();
    }

    @Override
    @JsonIgnore
    public Map<String, Object> getContent() {
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("id", id);
        results.put("deploymentId", deploymentId);
        results.put("category", category);
        results.put("description", description);
        results.put("key", key);
        results.put("resourceName", resourceName);
        results.put("version", version);
        return results;
    }

}
