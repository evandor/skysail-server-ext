package de.twenty11.skysail.server.ext.facebook.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.codehaus.jackson.JsonNode;

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
public class FacebookUser implements Composite {

    private String name;

    private String id;

    private List<Component> components = new ArrayList<Component>();

    public FacebookUser(JsonNode jsonRootNode) {
        checkForErrors(jsonRootNode);
        if (jsonRootNode.get("id") != null) {
            this.id = jsonRootNode.get("id").asText();
        }
    }

    // TODO
    private void checkForErrors(JsonNode jsonRootNode) {
        JsonNode errors = jsonRootNode.path("errors");
        // Iterator<JsonNode> ite = jobs.getElements();
        // while (ite.hasNext()) {
        // JsonNode next = ite.next();
        // results.add(new JobsDetails(next.path("name").getTextValue(), next.path("url").getTextValue(), next
        // .path("color").getTextValue()));
        // }
        //
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
        return id;
    }

    @Override
    public String toString() {
        return "ID '" + this.id + "'";
    }
}
