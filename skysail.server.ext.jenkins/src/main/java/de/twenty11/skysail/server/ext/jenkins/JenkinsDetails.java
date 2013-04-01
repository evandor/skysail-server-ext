package de.twenty11.skysail.server.ext.jenkins;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import de.twenty11.skysail.common.forms.Field;
import de.twenty11.skysail.common.forms.Form;

@Form(name = "JenkinsForm")
@Entity
public class JenkinsDetails {

    @Field
    @NotNull(message = "Name is mandatory")
    private String name = "";

    @Field
    @NotNull(message = "Location is mandatory")
    private String location = "";

    public JenkinsDetails(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public JenkinsDetails() {
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
