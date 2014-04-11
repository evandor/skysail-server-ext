package de.twenty11.skysail.server.ext.osgi.monitor.agent.callback;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Callbacks {

    Map<String, CallbackDefinition> callbacksMap = new HashMap<String, CallbackDefinition>();

    public Set<String> packages() {
        Set<String> result = new HashSet<String>();
        for (CallbackDefinition cb : callbacksMap.values()) {
            // result.add(cb.getClass().getPackage().getName());
            result.add(cb.getPackageName());
        }
        return result;
    }

    public void add(CallbackDefinition callbackDefinition) {
        callbacksMap.put(callbackDefinition.getName(), callbackDefinition);
    }

    public CallbackDefinition get(String name) {
        return callbacksMap.get(name);
    }
}
