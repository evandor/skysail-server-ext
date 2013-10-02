package de.twenty11.skysail.server.ext.notes;

import java.io.Serializable;

import net.thucydides.core.annotations.Steps;

import org.jbehave.core.annotations.Given;

import de.twenty11.skysail.server.ResourceTestWithUnguardedAppication.DummyAuthorizationService;

public class CommonSteps implements Serializable {

    private static final long serialVersionUID = 406153759886583777L;

    protected String result;
    private Integer id;

    @Steps
    private RestSteps rest;

    @Steps
    private JacksonSteps jackson;

    // === GIVEN ===

    @Given("the user $username is logged in")
    public void loginAsUser(String name) {
        DummyAuthorizationService authorizationService = AcceptanceTests.getDummyAuthorizationService();
        authorizationService.setUsernamePassword(name, name.toLowerCase());
    }

}
