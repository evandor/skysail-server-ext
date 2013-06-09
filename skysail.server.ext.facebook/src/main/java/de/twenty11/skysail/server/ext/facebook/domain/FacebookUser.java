package de.twenty11.skysail.server.ext.facebook.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonIgnore;

import de.twenty11.skysail.common.Presentable;
import de.twenty11.skysail.common.PresentableHeader;
import de.twenty11.skysail.server.structures.composite.Component;
import de.twenty11.skysail.server.structures.composite.Composite;

/**
 * A facebook user in the context of the skysail.server.ext.facebook bundle is essentially a container for some
 * high-level attributes (like name and id) and a list of other, related components (playing the role of "friends",
 * "posts" and so on).
 * 
 * The provided constructor creates a new instance from a json representation
 * 
 */
public class FacebookUser implements Composite, Presentable {

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

    @Override
    public Component visit() {
        return this;
    }

    @Override
    public void add(Component component) {
        this.components.add(component);
    }

    @Override
    public void remove(Component component) {
        this.components.remove(component);
    }

    @Override
    public List<Component> getChildren() {
        return Collections.unmodifiableList(components);
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
    public PresentableHeader getHeader() {
        return new PresentableHeader.Builder("Friends").setLink("me/friends").build();
    }

    @Override
    @JsonIgnore
    public Map<String, Object> getContent() {
        return attributes;
    }

}
