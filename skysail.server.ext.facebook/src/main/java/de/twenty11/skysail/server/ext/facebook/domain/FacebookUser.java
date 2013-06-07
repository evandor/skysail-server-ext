package de.twenty11.skysail.server.ext.facebook.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.codehaus.jackson.JsonNode;

import de.twenty11.skysail.server.structures.composite.Component;
import de.twenty11.skysail.server.structures.composite.Composite;

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

    private void checkForErrors(JsonNode jsonRootNode) {
        JsonNode jobs = jsonRootNode.path("errors");
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
