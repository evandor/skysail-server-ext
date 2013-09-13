package de.twenty11.skysail.server.ext.jgit.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.restlet.data.Form;

import de.twenty11.skysail.common.commands.Command;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.ListServerResource2;

public class ExecutedCommandsResource extends ListServerResource2<ExecutionContext> {

    @Override
    protected List<ExecutionContext> getData() {
        List<ExecutionContext> result = new ArrayList<ExecutionContext>();
        Map<Long, Command> executed = (Map<Long, Command>) getContext().getAttributes().get("skysail.executedCommands");
        if (executed == null) {
            return Collections.emptyList();
        }
        for (Long key : executed.keySet()) {
            result.add(new ExecutionContext(key));
        }
        return result;
    }

    @Override
    public ExecutionContext getData(Form form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SkysailResponse<?> addEntity(ExecutionContext entity) {
        // TODO Auto-generated method stub
        return null;
    }
}
