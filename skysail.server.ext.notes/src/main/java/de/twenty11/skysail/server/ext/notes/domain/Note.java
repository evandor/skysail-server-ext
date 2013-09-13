package de.twenty11.skysail.server.ext.notes.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.twenty11.skysail.common.forms.Field;
import de.twenty11.skysail.common.forms.Form;
import de.twenty11.skysail.server.um.domain.SkysailUser;

@Entity
@Form(name = "noteform")
public class Note extends Component {

    public static final String TITLE = "title";
    public static final String CONTENT = "content";

    @Id
    @TableGenerator(name = "EXT_NOTES_NOTE_TABLE_GEN", table = "SEQUENCE", pkColumnValue = "EXT_NOTES_NOTE_SEQ")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "EXT_NOTES_NOTE_TABLE_GEN")
    private int pid;

    @Field
    @NotNull(message = "Title is mandatory")
    @Size(min = 1, message = "title must not be empty")
    // TODO define business requirements
    // TODO proper handling of special characters, XSS
    private String title;

    @Field(tag = "textarea")
    // @Column(columnDefinition = "CLOB")
    @Lob
    private String content;

    private Folder parent;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @ManyToOne()
    @JoinColumn(name = "owner", nullable = false)
    private SkysailUser owner;

    public Note() {
    }

    public Note(Folder parent, String title, String content) {
        this.parent = parent;
        this.title = title;
        this.content = content;
        this.created = new Date();
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    public Date getCreated() {
        return created;
    }

    public Folder getParent() {
        return parent;
    }

    public SkysailUser getOwner() {
        return owner;
    }

    public void setOwner(SkysailUser owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return getTitle();
    }

}
