package de.twenty11.skysail.server.ext.jgit.internal;

import java.util.Map;

import org.restlet.data.Form;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.commands.Command;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.UniqueResultServerResource2;

public class ExecutedCommandResource extends UniqueResultServerResource2<String> {

    private Long timestamp;

    @Override
    protected void doInit() throws ResourceException {
        timestamp = new Long(getRequest().getAttributes().get("timestamp").toString());
    }

    @Override
    protected String getData() {
        Map<Long, Command> executed = (Map<Long, Command>) getContext().getAttributes().get("skysail.executedCommands");
        Command command = executed.get(timestamp);
        StringBuilder result = new StringBuilder();
        for (String line : command.executionMessages()) {
            result.append(line + "<br>\n");
        }
        return result.toString();
    }

    @Override
    public String getData(Form form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SkysailResponse<?> addEntity(String entity) {
        // TODO Auto-generated method stub
        return null;
    }

}
