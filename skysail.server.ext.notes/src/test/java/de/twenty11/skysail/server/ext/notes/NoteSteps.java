package de.twenty11.skysail.server.ext.notes;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import net.thucydides.core.Thucydides;
import net.thucydides.core.annotations.Steps;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class NoteSteps {

    @Steps
    private RestSteps rest;

    @Steps
    private CommonSteps commonSteps;

    @Steps
    private JacksonSteps jackson;

    // === GIVEN === see CommonSteps

    // === WHEN ===

    @SuppressWarnings("unchecked")
    @When("the user submits the form with the title $titleIn and the content $contentIn")
    public void post(@Named("titleIn") String titleIn, @Named("contentIn") String contentIn) {
        String result = rest.postNote(titleIn, contentIn);
        Thucydides.getCurrentSession().put("result", result);
    }

    @SuppressWarnings("unchecked")
    @When("the user submits an ajax request with the title $title and the content $content")
    public void postWithAjax(String title, String content) {
        Thucydides.getCurrentSession().put("result", rest.postNoteWithAjax(title, content));
    }

    @SuppressWarnings("unchecked")
    @When("the user submits the form without title")
    public void post() {
        Thucydides.getCurrentSession().put("result", rest.postNote("", "something"));
    }

    @SuppressWarnings("unchecked")
    @When("the user opens the existing note $name")
    @Alias("the user wants to delete his existing note $name")
    public void createFolder(@Named("input") String input) {
        String result = rest.postNote(input, "somecontent");
        Thucydides.getCurrentSession().put("result", result);
        Thucydides.getCurrentSession().put("id", jackson.getFromJson("pid", result));
    }

    @SuppressWarnings("unchecked")
    @When("the user submits a $method request for the notes id")
    public void request(String method) {
        Integer id = (Integer) Thucydides.getCurrentSession().get("id");
        if ("delete".equals(method.toLowerCase())) {
            rest.deleteNote(id);
        } else if ("get".equals(method.toLowerCase())) {
            Thucydides.getCurrentSession().put("result", rest.getNote(id));
        }
    }

    // === THEN ===

    @Then("the new note should have the title $title and the content $content")
    public void the_new_folder_should_have_the_name(@Named("title") String title, @Named("content") String content) {
        String result = (String) Thucydides.getCurrentSession().get("result");
        assertThat(result, containsString("\"title\":\"" + title + "\""));
        assertThat(result, containsString("\"content\":\"" + content + "\""));
    }

    @Then("the new note should have the title $name")
    public void the_new_folder_should_have_the_name(@Named("title") String title) {
        String result = (String) Thucydides.getCurrentSession().get("result");
        assertThat(result, containsString("\"title\":\"" + title + "\""));
    }

    @Then("the note is deleted")
    public void isDeleted() {
        request("get");
        commonSteps.the_request_is_successful();
        String result = (String) Thucydides.getCurrentSession().get("result");
        assertThat(result, containsString("\"data\":null"));
    }

}
