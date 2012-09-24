package de.twenty11.skysail.server.ext.dbviewer.internal.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ConnectionDetails {

    @NotNull(message = "Id is mandatory")
    @Size(min = 1, message = "Id must not be empty")
    private String id;

    private String username;
    private String password;
    private String driverName;
    private String url;

    public ConnectionDetails(String id, String username, String password, String url, String driverClassName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.url = url;
        this.driverName = driverClassName;
    }

    public String getId() {
        return id;
    }

    @NotNull(message = "field 'username' is mandatory")
    @Size(min = 1)
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @NotNull(message = "field 'driverName' is mandatory")
    public String getDriverName() {
        return driverName;
    }

    @NotNull(message = "field 'url' is mandatory")
    public String getUrl() {
        return url;
    }

}
