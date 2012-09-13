package de.twenty11.skysail.server.ext.dbviewer.internal.entities;

import javax.validation.constraints.NotNull;

public class ConnectionDetails {

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

    @NotNull(message="Id is mandatory")
    public String getId() {
        return id;
    }

    @NotNull(message="Username is mandatory")
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @NotNull(message="Drivername is mandatory")
    public String getDriverName() {
        return driverName;
    }

    @NotNull(message="Url is mandatory")
    public String getUrl() {
        return url;
    }
    
}
