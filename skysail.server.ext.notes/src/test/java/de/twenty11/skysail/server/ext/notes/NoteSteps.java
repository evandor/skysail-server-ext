package de.twenty11.skysail.server.ext.notes;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import net.thucydides.core.Thucydides;
import net.thucydides.core.annotations.Steps;

import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class NoteSteps {

    @Steps
    private RestSteps rest;

    @Steps
    private CommonSteps commonSteps;

    // === GIVEN === see CommonSteps

    // === WHEN ===

    @SuppressWarnings("unchecked")
    @When("the user submits the form with the title $titleIn and the content $contentIn")
    public void post(@Named("titleIn") String titleIn, @Named("contentIn") String contentIn) {
        String result = rest.postNote(titleIn, contentIn);
        Thucydides.getCurrentSession().put("result", result);
    }

    // === THEN ===

    @Then("the new note should have the title $title and the content $content")
    public void the_new_folder_should_have_the_name(@Named("title") String title, @Named("content") String content) {
        String result = (String) Thucydides.getCurrentSession().get("result");
        assertThat(result, containsString("\"title\":\"" + title + "\""));
        assertThat(result, containsString("\"content\":\"" + content + "\""));
    }

}
