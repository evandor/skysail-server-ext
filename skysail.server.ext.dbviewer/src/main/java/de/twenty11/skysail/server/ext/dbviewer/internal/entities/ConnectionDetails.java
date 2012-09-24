package de.twenty11.skysail.server.ext.dbviewer.internal.entities;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ConnectionDetails {

    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String DRIVERNAME = "drivername";
    public static final String URL = "url";

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

    @NotNull(message = "field '" + USERNAME + "' is mandatory")
    @Size(min = 1)
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @NotNull(message = "field '" + DRIVERNAME + "' is mandatory")
    public String getDriverName() {
        return driverName;
    }

    @NotNull(message = "field '" + URL + "' is mandatory")
    public String getUrl() {
        return url;
    }

    public Map<String, String> toMap() {
        Map<String, String> result = new HashMap<String, String>();
        result.put(ID, id);
        result.put(USERNAME, username);
        result.put(PASSWORD, password);
        result.put(URL, url);
        result.put(DRIVERNAME, driverName);
        return result;
    }

}
