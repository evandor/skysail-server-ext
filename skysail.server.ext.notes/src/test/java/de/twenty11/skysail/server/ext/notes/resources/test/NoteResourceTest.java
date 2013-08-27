package de.twenty11.skysail.server.ext.notes.resources.test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.restlet.data.Form;

import de.twenty11.skysail.common.responses.EntityDetailsResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ResourceTestWithUnguardedAppication;
import de.twenty11.skysail.server.ext.notes.NotesApplication;
import de.twenty11.skysail.server.ext.notes.domain.Note;
import de.twenty11.skysail.server.ext.notes.repos.ComponentRepository;
import de.twenty11.skysail.server.ext.notes.resources.NoteResource;
import de.twenty11.skysail.server.ext.notes.resources.NotesResource;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertThat;

public class NoteResourceTest extends ResourceTestWithUnguardedAppication<NotesApplication> {

    private NoteResource noteResource;
    private NotesApplication spy;
    private final InMemoryNotesRepository notesRepository = new InMemoryNotesRepository();
    private NotesResource notesResource;

    @Before
    public void setUp() throws Exception {
        spy = setUpMockedApplication(new NotesApplication());
        noteResource = new NoteResource();
        notesResource = new NotesResource();
        setupNotesRepository();
    }

    @SuppressWarnings("rawtypes")
    private void setupNotesRepository() {
        Mockito.doAnswer(new Answer<ComponentRepository>() {
            @Override
            public ComponentRepository answer(InvocationOnMock invocation) throws Throwable {
                return notesRepository;
            }
        }).when(spy).getNotesRepository();
    }

    @Test
    @Ignore
    public void note_can_be_added_to_repository_via_form() {
        Form form = Mockito.mock(Form.class);
        Mockito.when(form.getFirstValue("title")).thenReturn("title");
        Mockito.when(form.getFirstValue("content")).thenReturn("content");
        EntityDetailsResponse response = noteResource.addEntity2(form);
        Note note = (Note) response.getEntity();

        // notesResource.getEntities();
        SkysailResponse<Note> entity = noteResource.getEntity();
    }

    @Test
    @Ignore
    public void empty_repository_returns_list_with_zero_entities() throws Exception {
        SkysailResponse<Note> entities = noteResource.getEntity();
        assertThat(entities.getSuccess(), is(true));
        // assertThat(entities.getData().size(), is(0));
    }

    // @Test
    // public void new_entity_is_returned_from_repository() throws Exception {
    //
    // SkysailResponse<List<SkysailUser>> entities = noteResource.getEntities();
    // assertThat(entities.getSuccess(), is(true));
    // assertThat(entities.getData().size(), is(0));
    // }

}
