package de.twenty11.skysail.server.ext.notes;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.restlet.data.Form;
import org.restlet.resource.ClientResource;

import static org.hamcrest.Matchers.containsString;

import static org.junit.Assert.assertThat;

public class FolderSteps extends AcceptanceTests {

    private Form form;
    private ClientResource cr;
    private String result;

    @Override
    @BeforeScenario
    public void setUp() {
        super.setUp();

    }

    @Given("the user wants to add a new Folder")
    public void user_wants_to_add_a_new_folder() {
        // nothing to do
    }

    @When("the user submits the form with the foldername $name")
    public void post(@Named("input") String input) throws Exception {
        cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH));
        form = new Form();
        form.add("folderName", input);
        result = cr.post(form).getText();
    }

    @Then("the request is successful")
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

    @When("the user submits an ajax request with the foldername 'foldername'")
    public void postWithAjax(String name) throws Exception {
        cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH + "?media=json"));
        form = new Form();
        form.add("folderName", name);
        result = cr.post(form).getText();
    }

    @Then("the request has the media type $mediaType")
    public void the_request_has_mediaType(String mediaType) {
    }

    @Pending
    @Then("the new Folder has the name $name")
    public void new_folder_has_name() {

    }

}
