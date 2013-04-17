package de.twenty11.skysail.server.ext.mail;

import java.util.HashMap;
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
import de.twenty11.skysail.common.forms.IgnoreValuesProvider;

@Form(name = "AccountForm")
@Entity
public class AccountDescriptor implements Presentable {

    @Id
    @GeneratedValue
    @JsonIgnore
    private int pid;// primary key for db

    public int getPid() {
        return this.pid;
    }

    @NotNull(message = "Name is mandatory")
    @Size(min = 1, message = "name must not be empty")
    @Field(valuesProvider = IgnoreValuesProvider.class)
    private String name;

    public AccountDescriptor(String name) {
        this.name = name;
    }

    public AccountDescriptor() {
    }

    @Override
    @JsonIgnore
    public PresentableHeader getHeader() {
        return new PresentableHeader.Builder("name").setLink("accounts/" + name).build();
    }

    @Override
    @JsonIgnore
    public Map<String, Object> getContent() {
        Map<String, Object> results = new HashMap<String, Object>();

        return results;
    }

}
