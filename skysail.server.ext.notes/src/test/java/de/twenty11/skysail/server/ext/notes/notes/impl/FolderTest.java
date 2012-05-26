package de.twenty11.skysail.server.ext.notes.notes.impl;

import java.util.Date;

import de.twenty11.skysail.server.ext.notes.notes.Note;
import de.twenty11.skysail.server.ext.notes.notes.NotesFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FolderTest {

    private NotesFactory notesFactory;

    @Before
    public void setUp() throws Exception {
        NotesPackageImpl.init();
        notesFactory = NotesFactoryImpl.eINSTANCE;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        Note note = notesFactory.createNote();
        note.setChanged(new Date());
        System.out.println(note);
    }

}
