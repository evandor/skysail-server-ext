package de.twenty11.skysail.server.ext.jgit;

import java.util.Collections;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.codehaus.jackson.annotate.JsonIgnore;

import de.twenty11.skysail.common.Presentable;
import de.twenty11.skysail.common.PresentableHeader;
import de.twenty11.skysail.common.forms.Field;
import de.twenty11.skysail.common.forms.Form;

@Form(name = "LocalRepositoryForm")
@Entity
public class LocalRepositoryDescriptor implements Presentable {

    @Id
    @GeneratedValue
    @JsonIgnore
    private int pid;// primary key for db

    public int getPid() {
        return this.pid;
    }

    @Field
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "name must only contain letters or digits.")
    @NotNull(message = "Name is mandatory")
    private String name;

    @Field
    @NotNull(message = "Path is mandatory")
    private String path;

    public LocalRepositoryDescriptor() {
        // To change body of created methods use File | Settings | File Templates.
    }

    public LocalRepositoryDescriptor(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    @Override
    public PresentableHeader getHeader() {
        return new PresentableHeader.Builder(name).setLink("repos/" + name).build();
    }

    @Override
    public Map<String, Object> getContent() {
        return Collections.emptyMap();
    }

}
