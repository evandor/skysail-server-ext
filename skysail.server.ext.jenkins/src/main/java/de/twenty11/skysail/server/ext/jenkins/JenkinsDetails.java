package de.twenty11.skysail.server.ext.jenkins;

import java.util.Collections;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PersistenceUnit;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.codehaus.jackson.annotate.JsonIgnore;

import de.twenty11.skysail.common.Presentable;
import de.twenty11.skysail.common.PresentableHeader;
import de.twenty11.skysail.common.forms.Field;
import de.twenty11.skysail.common.forms.Form;

@Form(name = "JenkinsForm")
@Entity
@PersistenceUnit(name = "JenkinsPU")
public class JenkinsDetails implements Presentable {

    @Id
    @GeneratedValue
    @JsonIgnore
    private int pid;// primary key for db

    public int getPid() {
        return this.pid;
    }

    @Field
    @NotNull(message = "Name is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
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

    @Override
    @JsonIgnore
    public PresentableHeader getHeader() {
        return new PresentableHeader.Builder(name).setLink("installation/" + name + "/jobs").setImage("icon-bold")
                .build();
    }

    @Override
    @JsonIgnore
    public Map<String, Object> getContent() {
        return Collections.emptyMap();
    }
}
