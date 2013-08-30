package de.twenty11.skysail.server.ext.notes.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.Validate;

import de.twenty11.skysail.common.forms.Field;
import de.twenty11.skysail.common.forms.Form;
import de.twenty11.skysail.server.um.domain.SkysailUser;

/**
 * 
 */
@Entity
@Form(name = "folderform")
public class Folder extends Component implements Comparable<Folder> {

    private List<Component> components = new ArrayList<Component>();

    private Folder parent;

    @ManyToOne
    private SkysailUser owner;

    @Id
    @GeneratedValue
    protected int pid;// primary key for db

    public int getPid() {
        return this.pid;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Folder other = (Folder) obj;
        if (pid != other.getPid())
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + pid;
        return result;
    }

    @Field
    @NotNull(message = "Name is mandatory")
    @Size(min = 1, message = "name  must not be empty")
    private String folderName;

    public static Folder createRoot(String folderName) {
        return new Folder(null, folderName);
    }

    public Folder() {
        // needed for EclipseLink
    }

    public Folder(Folder parent, String folderName) {
        Validate.notNull(folderName, "A folder needs a name");
        this.parent = parent;
        this.folderName = folderName;
        if (parent != null) {
            parent.add(this);
        }
    }

    public static boolean isRootFolder(Folder folder) {
        return folder.parent == null;
    }

    private void add(Component component) {
        this.components.add(component);
    }

    public List<Component> getChildren() {
        return Collections.unmodifiableList(components);
    }

    public Folder getParent() {
        return parent;
    }

    public String getFolderName() {
        return folderName;
    }

    @Override
    public int compareTo(Folder other) {
        return folderName.compareTo(other.getFolderName());
    }

    public SkysailUser getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return folderName;
    }
}
