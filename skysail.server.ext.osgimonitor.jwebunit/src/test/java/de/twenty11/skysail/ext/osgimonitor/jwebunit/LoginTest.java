package de.twenty11.skysail.ext.osgimonitor.jwebunit;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import net.sourceforge.jwebunit.exception.TestingEngineResponseException;
import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class LoginTest {

    private WebTester tester;
    private String username = "admin";
    private String password = "skysail";
    private String url = "localhost:2011";
    private String baseUrl;

    @Before
    public void setup() {
        Properties prop = new Properties();

        if (!loadProperties(prop, "/home/carsten/jwebunit/passwd.txt")) {
            if (!loadProperties(prop, "/home/ec2-user/jwebunit/passwd.txt")) {
                loadProperties(prop, "/home/ubuntu/jwebunit/passwd.txt");
            }
        }

        tester = new WebTester();
        baseUrl = "http://" + url + "/osgimonitor/";
        tester.setBaseUrl(baseUrl);
        tester.getTestContext().setAuthorization(username, password);
        tester.setScriptingEnabled(false);
    }

    private boolean loadProperties(Properties prop, String filename) {
        try {
            prop.load(new FileInputStream(filename));
            username = prop.getProperty("user");
            password = prop.getProperty("pass");
            url = prop.getProperty("url");
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Test(expected = TestingEngineResponseException.class)
    public void gives_401_for_wrong_password() {
        tester.getTestContext().setAuthorization(username, "wrongOneForSure");
        tester.beginAt("");
    }

    @Test
    public void returns_html_startpage_when_providing_proper_credentials_and_media_type() {
        tester.beginAt("?media=html");
        tester.assertTitleEquals("Skysail - a business server");
    }

    @Test
    public void returns_json_startpage_when_providing_proper_credentials_and_media_type() {
        tester.beginAt("?media=json");
        tester.assertTitleEquals("");
    }

    @Test
    @Ignore
    public void follows_bundles_link_in_html_mode() {
        tester.beginAt("?media=html");
        tester.clickLink("bundlesLink");
        // tester.clickLinkWithExactText(getLinkText("bundles"));
        tester.assertTextPresent("skysail.server");
    }

    @Test
    @Ignore
    public void follows_asGraph_link_in_html_mode() {
        tester.beginAt("?media=html");
        tester.clickLinkWithExactText(getLinkText("bundles/asGraph"));
        tester.assertTextPresent("de.twenty11.skysail.common.ext.osgimonitor.BundleDetails");
    }

    private String getLinkText(String part) {
        return "\"" + baseUrl + part + "\"";
    }

}
