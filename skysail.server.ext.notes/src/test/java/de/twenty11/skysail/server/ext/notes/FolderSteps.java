package de.twenty11.skysail.server.ext.notes;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
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
        form = new Form();
        cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH));

    }

    @Given("the user wants to add a new Folder")
    public void aaa() {

    }

    @When("the user adds a new folder")
    public void bbb() {

    }

    @Then("the folder should have been added")
    public void ccc() {

    }

    @When("the user submits the form with the foldername $name")
    public void post(String name) throws Exception {
        form.add("folderName", name);
        result = cr.post(form).getText();
    }

    @Then("the request is successful")
    public void the_request_is_successful() {
        assertThat(result, containsString("\"data\":[{\"parent\":null,\"folderName\":\"foldername\",\"children\":[]}]"));
    }

}
