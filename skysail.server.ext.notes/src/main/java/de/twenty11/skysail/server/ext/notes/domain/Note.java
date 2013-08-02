package de.twenty11.skysail.server.ext.notes.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.twenty11.skysail.common.forms.Field;
import de.twenty11.skysail.common.forms.Form;

@Entity
@Form(name = "noteform")
public class Note extends Component {

    @Id
    @GeneratedValue
    private Long pid;// primary key for db

    @Field
    @NotNull(message = "Title is mandatory")
    @Size(min = 1, message = "title must not be empty")
    private String title;

    @Field(tag = "textarea")
    @Column(columnDefinition = "CLOB")
    private String content;

    private Folder parent;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Note() {
    }

    public Note(Folder parent, String title, String content) {
        this.parent = parent;
        this.title = title;
        this.content = content;
        this.created = new Date();
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getPid() {
        return pid;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return getTitle();
    }

}
