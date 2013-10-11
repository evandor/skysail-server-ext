package de.twenty11.skysail.server.ext.notes;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import net.thucydides.core.Thucydides;
import net.thucydides.core.annotations.Steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;

import de.twenty11.skysail.server.ResourceTestWithUnguardedAppication.DummyAuthorizationService;

public class CommonSteps {

    protected String result;

    @Steps
    protected RestSteps rest;

    @Steps
    protected JacksonSteps jackson;

    // === GIVEN ===

    @Given("the user $username is logged in")
    public void loginAsUser(String name) {
        DummyAuthorizationService authorizationService = AcceptanceTests.getDummyAuthorizationService();
        authorizationService.setUsernamePassword(name, name.toLowerCase());
    }

    // === WHEN ===

    // === THEN ===

    @Then("the request is successful")
    public void the_request_is_successful() {
        String result = (String) Thucydides.getCurrentSession().get("result");
        assertThat(result, containsString("\"success\":true"));
    }

    @Then("the request is not successful")
    public void the_request_is_not_successful() {
        String result = (String) Thucydides.getCurrentSession().get("result");
        assertThat(result, containsString("\"success\":false"));
    }

}
