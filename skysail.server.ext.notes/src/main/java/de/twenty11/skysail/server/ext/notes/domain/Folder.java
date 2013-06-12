package de.twenty11.skysail.server.ext.notes.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang.Validate;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.stringtemplate.v4.ST;

import de.twenty11.skysail.common.Presentable2;

/**
 * 
 */
@Entity
public class Folder extends Component implements Presentable2 {

    @Id
    @GeneratedValue
    @JsonIgnore
    private int pid;// primary key for db

    private String folderName;

    private List<Component> components = new ArrayList<Component>();

    private Folder parent;

    public int getPid() {
        return this.pid;
    }

    public static Folder createRoot(String folderName) {
        return new Folder(null, folderName);
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

    @Override
    @JsonIgnore
    public String getHtml() {
        ST html = new ST("<foldername>");
        html.add("foldername", folderName);
        return html.render();
    }

    public Folder getParent() {
        return parent;
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
        if (pid != other.pid)
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

}
