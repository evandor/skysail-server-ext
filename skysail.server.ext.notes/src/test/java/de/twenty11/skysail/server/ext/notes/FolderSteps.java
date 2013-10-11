package de.twenty11.skysail.server.ext.notes;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import net.thucydides.core.Thucydides;
import net.thucydides.core.annotations.Steps;

import org.apache.commons.lang.NotImplementedException;
import org.jbehave.core.annotations.Aliases;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class FolderSteps {

    @Steps
    private CommonSteps common;

    @Steps
    private RestSteps rest;

    @Steps
    private JacksonSteps jackson;

    // === GIVEN ===

    @SuppressWarnings("unchecked")
    @Given("the user has created a folder")
    public void createFolder() {
        String result = rest.postFolder("aFolder");
        Integer id;
        try {
            id = jackson.getFromJson("pid", result);
        } catch (Exception e) {
            id = null;
        }
        Thucydides.getCurrentSession().put("id", id);
    }

    @Given("the user wants to delete this folder")
    public void setResoucePathForDelete() {
    }

    @Given("the folder $foldername was created by $username")
    public void folderWasCreatedBy(String foldername, String username) {
        common.loginAsUser(username);
        createFolder(foldername);
        // common.logout();
    }

    // === WHEN ===

    @SuppressWarnings("unchecked")
    @When("the user opens her existing folder $name")
    @Aliases(values = { "the user opens his existing folder $name",
            "the user wants to delete his existing folder $name" })
    public void createFolder(@Named("input") String input) {
        String result = rest.postFolder(input);
        Thucydides.getCurrentSession().put("result", result);
        Thucydides.getCurrentSession().put("id", jackson.getFromJson("pid", result));
    }

    @SuppressWarnings("unchecked")
    @When("the user submits the form with the foldername $name")
    public void post(@Named("input") String input) {
        Thucydides.getCurrentSession().put("result", rest.postFolder(input));
    }

    @SuppressWarnings("unchecked")
    @When("the user submits the form without foldername")
    public void post() {
        Thucydides.getCurrentSession().put("result", rest.postFolder(""));
    }

    @SuppressWarnings("unchecked")
    @When("the user submits an ajax request with the foldername $name")
    public void postWithAjax(String name) {
        Thucydides.getCurrentSession().put("result", rest.postFolderWithAjax(name));
    }

    @SuppressWarnings("unchecked")
    @When("the user submits a $method request for the folders id")
    public void request(String method) {
        Integer id = (Integer) Thucydides.getCurrentSession().get("id");
        if ("delete".equals(method.toLowerCase())) {
            rest.deleteFolder(id);
            // Thucydides.getCurrentSession().put("result", );
        } else if ("get".equals(method.toLowerCase())) {
            Thucydides.getCurrentSession().put("result", rest.getFolder(id));
        }
    }

    // === THEN ===

    @Then("the new folder should have the name $name")
    public void the_new_folder_should_have_the_name(@Named("foldername") String foldername) {
        String result = (String) Thucydides.getCurrentSession().get("result");
        assertThat(result, containsString("\"folderName\":\"" + foldername + "\""));
    }

    @Then("the request is not successful")
    public void the_request_is_not_successful() {
        String result = (String) Thucydides.getCurrentSession().get("result");
        assertThat(result, containsString("\"success\":false"));
    }

    @Then("the request has the media type $mediaType")
    public void the_request_has_mediaType(String mediaType) {
        String result = (String) Thucydides.getCurrentSession().get("result");
        if ("json".equals(mediaType.toLowerCase())) {
            jackson.assertResultIsValidJson(result);
        } else {
            throw new NotImplementedException();
        }
    }

    @Then("the folder is returned")
    public void requestFolder() {
        Integer id = (Integer) Thucydides.getCurrentSession().get("id");
        String result = (String) Thucydides.getCurrentSession().get("result");
        common.the_request_is_successful();
        assertThat(result, containsString("\"pid\":" + id));
    }

    @Then("the folder is deleted")
    public void isDeleted() {
        request("get");
        common.the_request_is_not_successful();
        String result = (String) Thucydides.getCurrentSession().get("result");
        assertThat(result, containsString("\"data\":null"));
    }

}
