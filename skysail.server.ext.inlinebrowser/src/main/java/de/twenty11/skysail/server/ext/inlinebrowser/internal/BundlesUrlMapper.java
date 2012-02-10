package de.twenty11.skysail.server.ext.inlinebrowser.internal;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import de.twenty11.skysail.server.ext.inlinebrowser.BrowserResource;
import de.twenty11.skysail.server.servicedefinitions.UrlMapper;

/**
 * Maps urls to resource handlers.
 * 
 * @author carsten
 * 
 */
public class BundlesUrlMapper implements UrlMapper {

    /**
     * It is really easier reading this if I do _not_ use constants.
     * 
     * @see de.twenty11.skysail.server.servicedefinitions.UrlMapper#getUrlMapping()
     */
    @Override
    public Map<String, String> getUrlMapping() {
        Map<String, String> queue = Collections.synchronizedMap(new LinkedHashMap<String, String>());

        // @formatter:off
        queue.put("/browser/",  BrowserResource.class.getName());
        // @formatter:on
        return queue;
    }

}
