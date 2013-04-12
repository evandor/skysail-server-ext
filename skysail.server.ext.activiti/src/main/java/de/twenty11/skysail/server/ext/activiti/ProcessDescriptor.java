package de.twenty11.skysail.server.ext.activiti;

import java.util.Collections;
import java.util.Map;

import org.activiti.engine.repository.ProcessDefinition;
import org.codehaus.jackson.annotate.JsonIgnore;

import de.twenty11.skysail.common.Presentable;
import de.twenty11.skysail.common.PresentableHeader;

public class ProcessDescriptor implements Presentable {

    private String name;

    public ProcessDescriptor(ProcessDefinition processDefinition) {
        this.name = processDefinition.getName();
    }

    @Override
    @JsonIgnore
    public PresentableHeader getHeader() {
        return new PresentableHeader.Builder(name).build();
    }

    @Override
    @JsonIgnore
    public Map<String, Object> getContent() {
        return Collections.emptyMap();
    }

}
