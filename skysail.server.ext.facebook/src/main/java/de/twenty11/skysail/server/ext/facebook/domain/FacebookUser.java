package de.twenty11.skysail.server.ext.facebook.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.stringtemplate.v4.ST;

import de.twenty11.skysail.common.Presentable2;
import de.twenty11.skysail.server.structures.composite.Component;

/**
 * A facebook user in the context of the skysail.server.ext.facebook bundle is essentially a container for some
 * high-level attributes (like name and id) and a list of other, related components (playing the role of "friends",
 * "posts" and so on).
 * 
 * The provided constructor creates a new instance from a json representation
 * 
 */
public class FacebookUser implements Presentable2 {

    private Map<String, Object> attributes = new HashMap<String, Object>();

    private List<Component> components = new ArrayList<Component>();

    public FacebookUser(JsonNode jsonRootNode, String token) {
        checkForErrors(jsonRootNode);
        addAttributes(jsonRootNode);
        attributes.put("[token]", token);
    }

    private void addAttributes(JsonNode jsonRootNode) {
        Iterator<String> fieldNames = jsonRootNode.getFieldNames();
        while (fieldNames.hasNext()) {
            String key = fieldNames.next();
            attributes.put(key, jsonRootNode.get(key).asText());
        }
    }

    // TODO
    private void checkForErrors(JsonNode jsonRootNode) {
        JsonNode errors = jsonRootNode.path("errors");
    }

    public String getId() {
        return attributes.get("id") == null ? "" : attributes.get("id").toString();
    }

    @Override
    public String toString() {
        return "ID '" + getId() + "'";
    }

    @Override
    @JsonIgnore
    public String getHtml() {
        ST html = new ST("Facebook User <id>");
        html.add("id", getId());
        return html.render();
    }
}
