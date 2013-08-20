package de.twenty11.skysail.server.ext.bookmarks.domain;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;

import de.twenty11.skysail.common.Presentable;
import de.twenty11.skysail.common.PresentableHeader;
import de.twenty11.skysail.common.forms.Field;
import de.twenty11.skysail.common.forms.Form;

@Form(name = "FolderForm")
@Entity
public class Folder implements Presentable {

    @Id
    @GeneratedValue
    private int pid;

    @NotNull(message = "Name is mandatory")
    @Size(min = 1, message = "name  must not be empty")
    @Field
    private String name;

    private Folder parent;

    public Folder() {
    }

    public Folder(String name, Folder parent) {
        this.name = name;
        this.parent = parent;
    }

    public int getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public Folder getParent() {
        return parent;
    }

    @Override
    @JsonIgnore
    public PresentableHeader getHeader() {
        return new PresentableHeader.Builder(name).build();
    }

    @Override
    @JsonIgnore
    public Map<String, Object> getContent() {
        return null;
    }

}
