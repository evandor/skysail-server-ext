package de.twenty11.skysail.server.ext.atmosphere.tail;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import de.twenty11.skysail.server.UrlMapper;

public class BundlesUrlMapper implements UrlMapper {

    @Override
    public Map<String, String> getUrlMapping() {
        
        Map<String, String> queue = Collections.synchronizedMap(new LinkedHashMap<String, String>());
        
        //queue.put("/pubsub/{topic}", LogFileResource.class.getName());
        
        return queue;
    }

}
