package de.twenty11.skysail.server.ext.dbviewer.internal.entities;

public class ConnectionDetails {

    private String username;
    private String password;
    private String driverName;
    private String url;

    public ConnectionDetails(String username, String password, String url, String driverClassName) {
        this.username = username;
        this.password = password;
        this.url = url;
        this.driverName = driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getUrl() {
        return url;
    }

}
