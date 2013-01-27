package de.twenty11.skysail.ext.osgimonitor.jwebunit;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.Before;
import org.junit.Test;

public class LoginTest {

    private WebTester tester;
    private String username = "admin";
    private String password = "skysail";

    @Before
    public void setup() {
        Properties prop = new Properties();

        try {
            // load a properties file
            prop.load(new FileInputStream("/home/carsten/jwebunit/passwd.txt"));
            username  = prop.getProperty("user");
            password = prop.getProperty("pass");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        tester = new WebTester();
        tester.setBaseUrl("http://localhost:2011/osgimonitor/");
        tester.getTestContext().setAuthorization(username, password);
    }

    @Test
    public void test1() {
        tester.beginAt(""); // Open the browser on http://localhost:8080/test/home.xhtml
        // tester.clickLink("login");
        // tester.assertTitleEquals("Login");
        // tester.setTextField("username", "test");
        // tester.setTextField("password", "test123");
        // tester.submit();
        tester.assertTitleEquals("");
    }
}
