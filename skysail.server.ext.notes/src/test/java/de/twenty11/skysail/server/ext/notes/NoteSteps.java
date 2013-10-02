package de.twenty11.skysail.server.ext.notes;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import net.thucydides.core.annotations.Steps;

import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class NoteSteps extends CommonSteps {

    private static final long serialVersionUID = 406153759886583777L;

    private Integer id;

    @Steps
    private RestSteps rest;

    @Steps
    private JacksonSteps jackson;

    @Steps
    private CommonSteps commonSteps;

    // === GIVEN === see CommonSteps

    // === WHEN ===

    @When("the user submits the form with the title $titleIn and the content $contentIn")
    public void post(@Named("titleIn") String titleIn, @Named("contentIn") String contentIn) {
        result = rest.postNote(titleIn, contentIn);
    }

    // === THEN ===

    @Then("the new note should have the title $title and the content $content")
    public void the_new_folder_should_have_the_name(@Named("title") String title, @Named("content") String content) {
        assertThat(result, containsString("\"title\":\"" + title + "\""));
        assertThat(result, containsString("\"content\":\"" + content + "\""));
    }

}
