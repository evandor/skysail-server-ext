package de.twenty11.skysail.server.ext.jenkins;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PersistenceUnit;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;

import de.twenty11.skysail.common.forms.Field;
import de.twenty11.skysail.common.forms.Form;

@Form(name = "JenkinsForm")
@Entity
@PersistenceUnit(name = "JenkinsPU")
public class JenkinsDetails {

    @Id
    @GeneratedValue
    @JsonIgnore
    private int pid;// primary key for db

    public int getPid() {
        return this.pid;
    }

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
