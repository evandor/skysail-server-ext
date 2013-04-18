package de.twenty11.skysail.server.ext.mail;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "name must only contain letters or digits.")
    private String name;

    @NotNull(message = "Host is mandatory")
    @Size(min = 1, message = "host must not be empty")
    @Field(valuesProvider = IgnoreValuesProvider.class)
    private String host;

    @NotNull(message = "Username is mandatory")
    @Size(min = 1, message = "username must not be empty")
    @Field(valuesProvider = IgnoreValuesProvider.class)
    private String username;

    @Field(valuesProvider = IgnoreValuesProvider.class)
    private String password;

    public AccountDescriptor() {
    }

    @Override
    @JsonIgnore
    public PresentableHeader getHeader() {
        return new PresentableHeader.Builder(name).setLink("accounts/" + name).build();
    }

    @Override
    @JsonIgnore
    public Map<String, Object> getContent() {
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("host", host);
        results.put("username", username);
        return results;
    }

    public AccountDescriptor(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
}
