package de.twenty11.skysail.server.ext.notes;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
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
import static org.junit.Assert.fail;

public class FolderSteps extends AcceptanceTests {

    private Form form;
    private ClientResource cr;
    private String result;
    private ObjectMapper mapper = new ObjectMapper();
    private Integer id;

    @Override
    @BeforeScenario
    public void setUp() {
        super.setUp();
    }

    // === GIVEN ===

    @Given("the user wants to add a new Folder")
    public void setResourcePathForPost() {
    }

    @Given("the user wants to add a new Folder via ajax")
    public void setResourcePathForPostWithAjax() {
    }

    @Given("the user has created a folder")
    public void createFolder() throws Exception {
        post("aFolder");
        Map folderMap = mapper.readValue(result, Map.class);
        id = (Integer) ((Map) folderMap.get("data")).get("pid");
    }

    @Given("the user wants to delete this folder")
    public void setResoucePathForDelete() {
    }

    // === WHEN ===

    @When("the user submits the form with the foldername $name")
    public void post(@Named("input") String input) throws Exception {
        cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH));
        form = new Form();
        form.add("folderName", input);
        result = cr.post(form).getText();
    }

    @When("the user submits an ajax request with the foldername $name")
    public void postWithAjax(String name) throws Exception {
        cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH + "?media=json"));
        form = new Form();
        form.add("folderName", name);
        result = cr.post(form).getText();
    }

    @When("the user submits a $method request for the folders id")
    public void request(String method) throws Exception {
        cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH + "/" + id));
        if ("delete".equals(method.toLowerCase())) {
            result = cr.delete().getText();
        } else if ("get".equals(method.toLowerCase())) {
            result = cr.get().getText();
        }
    }

    // === THEN ===

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

    @Then("the request has the media type $mediaType")
    public void the_request_has_mediaType(String mediaType) {
        try {
            Map folderMap = mapper.readValue(result, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Pending
    @Then("the new Folder has the name $name")
    public void new_folder_has_name() {

    }

    @Then("the folder is returned")
    public void requestFolder() {
        the_request_is_successful();
        assertThat(result, containsString("\"pid\":" + id));
    }

    @Then("the folder is deleted")
    public void isDeleted() throws Exception {
        request("get");
        the_request_is_not_successful();
    }

}
