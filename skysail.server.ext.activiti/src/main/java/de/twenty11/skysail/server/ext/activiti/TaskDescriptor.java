package de.twenty11.skysail.server.ext.activiti;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.codehaus.jackson.annotate.JsonIgnore;

import de.twenty11.skysail.common.Presentable;
import de.twenty11.skysail.common.PresentableHeader;

public class TaskDescriptor implements Presentable {

    private String name;

    public TaskDescriptor(Task task) {
        name = task.getName();
    }

    @Override
    @JsonIgnore
    public PresentableHeader getHeader() {
        return new PresentableHeader.Builder(name).build();
    }

    @Override
    @JsonIgnore
    public Map<String, Object> getContent() {
        Map<String, Object> results = new HashMap<String, Object>();
        return results;
    }

}
