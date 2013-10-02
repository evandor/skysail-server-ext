package de.twenty11.skysail.server.ext.notes;

import net.thucydides.core.annotations.Steps;

import org.jbehave.core.annotations.Given;

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

}
