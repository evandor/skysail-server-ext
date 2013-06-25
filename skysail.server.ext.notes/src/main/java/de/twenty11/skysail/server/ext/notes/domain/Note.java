package de.twenty11.skysail.server.ext.notes.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;

import de.twenty11.skysail.common.forms.Field;
import de.twenty11.skysail.common.forms.Form;

@Entity
@Form(name = "noteform")
public class Note extends Component {

    @Id
    @GeneratedValue
    @JsonIgnore
    private int pid;// primary key for db

    @Field
    @NotNull(message = "Title is mandatory")
    @Size(min = 1, message = "title must not be empty")
    private String title;

    @Field
    private String content;
    
    private Folder parent;

    public Note() {
    }
    
    public Note(Folder parent, String title, String content) {
        this.parent = parent;
        this.title = title;
        this.content = content;
    }

    public int getPid() {
        return pid;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getContent() {
        return content;
    }
}
