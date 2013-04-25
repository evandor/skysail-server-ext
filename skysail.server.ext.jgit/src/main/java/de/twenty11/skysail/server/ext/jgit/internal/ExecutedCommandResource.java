package de.twenty11.skysail.server.ext.jgit.internal;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import de.twenty11.skysail.common.commands.Command;
import de.twenty11.skysail.server.restlet.ListServerResource2;

public class ExecutedCommandResource extends ListServerResource2<Long> {

    @Override
    protected List<Long> getData() {
        List<Long> result = Collections.emptyList();
        Map<Long, Command> executed = (Map<Long, Command>) getContext().getAttributes().get("skysail.executedCommands");
        if (executed == null) {
            return Collections.emptyList();
        }
        result.addAll(executed.keySet());
        return result;
    }
}
