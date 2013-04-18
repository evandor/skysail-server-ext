package de.twenty11.skysail.server.ext.jgit;

import de.twenty11.skysail.common.Presentable;
import de.twenty11.skysail.common.PresentableHeader;
import de.twenty11.skysail.common.forms.Field;
import de.twenty11.skysail.common.forms.Form;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Map;

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
        //To change body of created methods use File | Settings | File Templates.
    }

    public LocalRepositoryDescriptor(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    @Override
    public PresentableHeader getHeader() {
        return new PresentableHeader.Builder(name).build();
    }

    @Override
    public Map<String, Object> getContent() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
