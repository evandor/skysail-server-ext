package de.twenty11.skysail.server.ext.bookmarks.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;

import de.twenty11.skysail.common.forms.Field;
import de.twenty11.skysail.common.forms.Form;

@Form(name = "BookmarkForm")
@Entity
public class Bookmark {

    @Id
    @GeneratedValue
    @JsonIgnore
    protected int pid;// primary key for db

    @NotNull(message = "Name is mandatory")
    @Size(min = 1, message = "name  must not be empty")
    @Field
    protected String name;

    public Bookmark() {
        // TODO Auto-generated constructor stub
    }

    public Bookmark(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public int getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    @Field
    private String url;

}
