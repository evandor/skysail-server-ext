package de.twenty11.skysail.server.ext.notes;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;

import org.apache.commons.lang.NotImplementedException;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import de.twenty11.skysail.server.ResourceTestWithUnguardedAppication.DummyAuthorizationService;

public class FolderSteps extends ScenarioSteps {

    private static final long serialVersionUID = 406153759886583777L;

    private String result;
    private Integer id;

    public FolderSteps(Pages pages) {
        super(pages);
    }

    @Steps
    private RestSteps rest;

    @Steps
    private JacksonSteps jackson;

    // === GIVEN ===

    @Given("the testuser $username wants to add a new Folder")
    public void userLogsIn(String name) {
        DummyAuthorizationService authorizationService = AcceptanceTests.getDummyAuthorizationService();
        authorizationService.setUsernamePassword(name, name.toLowerCase());
    }

    @Given("the testuser $username wants to change a folder")
    public void userWantsToChangeFolder(String name) {
        DummyAuthorizationService authorizationService = AcceptanceTests.getDummyAuthorizationService();
        authorizationService.setUsernamePassword(name, name.toLowerCase());
    }

    @Given("the testuser $username wants to add a new Folder via ajax")
    public void setResourcePathForPostWithAjax(String name) {
        DummyAuthorizationService authorizationService = AcceptanceTests.getDummyAuthorizationService();
        authorizationService.setUsernamePassword(name, name.toLowerCase());
    }

    @Given("the user has created a folder")
    public void createFolder() {
        result = rest.postFolder("aFolder");
        try {
            id = jackson.getFromJson("pid", result);
        } catch (Exception e) {
            id = null;
        }
    }

    @Given("the user wants to delete this folder")
    public void setResoucePathForDelete() {
    }

    // === WHEN ===

    @When("the user opens the existing folder $name")
    public void createFolder(@Named("input") String input) {
        result = rest.postFolder(input);
        id = jackson.getFromJson("pid", result);
    }

    @When("the user submits the form with the foldername $name")
    public void post(@Named("input") String input) {
        result = rest.postFolder(input);
    }

    @When("the user submits the form without foldername")
    public void post() {
        result = rest.postFolder("");
    }

    @When("the user submits an ajax request with the foldername $name")
    public void postWithAjax(String name) {
        result = rest.postFolderWithAjax(name);
    }

    @When("the user submits a $method request for the folders id")
    public void request(String method) {
        if ("delete".equals(method.toLowerCase())) {
            result = rest.deleteFolder(id);
        } else if ("get".equals(method.toLowerCase())) {
            result = rest.getFolder(id);
        }
    }

    // === THEN ===

    @Then("the folder request is successful")
    public void the_request_is_successful() {
        assertThat(result, containsString("\"success\":true"));
    }

    @Then("the new folder should have the name $name")
    public void the_new_folder_should_have_the_name(@Named("foldername") String foldername) {
        assertThat(result, containsString("\"folderName\":\"" + foldername + "\""));
    }

    @Then("the request is not successful")
    public void the_request_is_not_successful() {
        assertThat(result, containsString("\"success\":false"));
    }

    @Then("the request has the media type $mediaType")
    public void the_request_has_mediaType(String mediaType) {
        if ("json".equals(mediaType.toLowerCase())) {
            jackson.assertResultIsValidJson(result);
        } else {
            throw new NotImplementedException();
        }
    }

    @Then("the folder is returned")
    public void requestFolder() {
        the_request_is_successful();
        assertThat(result, containsString("\"pid\":" + id));
    }

    @Then("the folder is deleted")
    public void isDeleted() {
        request("get");
        the_request_is_not_successful();
    }

}
