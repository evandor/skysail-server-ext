package de.twenty11.skysail.server.ext.osgi.monitor.agent.callback.messages;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.messages.JsonMessage;

public class RuntimeExceptionMessage extends JsonMessage {

    private RuntimeException ex;

    public RuntimeExceptionMessage(String type, String method, RuntimeException ex) {
        super(type, method);
        this.ex = ex;
    }

    public RuntimeException getEx() {
        return ex;
    }

}
