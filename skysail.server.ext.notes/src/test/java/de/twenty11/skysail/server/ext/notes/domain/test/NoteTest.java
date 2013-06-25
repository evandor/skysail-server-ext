package de.twenty11.skysail.server.ext.notes.domain.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.twenty11.skysail.server.ext.notes.domain.Note;

public class NoteTest {

    private Note note;

    @Before
    public void setUp() throws Exception {
        note = new Note(null, "title", "content");
    }

    @After
    public void tearDown() throws Exception {
    }

//    @Test
//    public void testSingleNote() throws Exception {
//        assertThat(note.getHtml(), containsString("title"));
//        assertThat(note.getHtml(), containsString("content"));
//    }

}
