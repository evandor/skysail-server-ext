package de.twenty11.skysail.server.ext.activiti;

import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;

import de.twenty11.skysail.common.commands.Command;

public class StartCommand implements Command {

    private ProcessDefinition processDefinition;
    private RuntimeService runtimeService;

    public StartCommand(RuntimeService runtimeService, ProcessDefinition processDefinition) {
        this.processDefinition = processDefinition;
        this.runtimeService = runtimeService;
    }

    @Override
    public String getName() {
        return "Start Process";
    }

    @Override
    public String getDescription() {
        return "start a new activiti process for " + processDefinition.getName();
    }

    @Override
    public boolean applicable() {
        return true;
    }

    @Override
    public void execute() {
        runtimeService.startProcessInstanceById(processDefinition.getId());
    }

    @Override
    public List<String> executionMessages() {
        // TODO Auto-generated method stub
        return null;
    }

}
