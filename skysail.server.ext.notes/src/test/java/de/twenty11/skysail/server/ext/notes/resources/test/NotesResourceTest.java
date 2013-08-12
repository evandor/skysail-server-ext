//package de.twenty11.skysail.server.ext.notes.resources.test;
//
//import de.twenty11.skysail.server.ext.notes.NotesApplication;
//import de.twenty11.skysail.server.ext.notes.repos.NotesRepository;
//import de.twenty11.skysail.server.ext.notes.resources.NoteResource;
//import de.twenty11.skysail.server.ext.notes.resources.NotesResource;
//
//public class NotesResourceTest extends NotesResourceIT {
//
//    private NotesResource notesResource;
//    private NoteResource noteResource;
//    private NotesApplication spy;
//    private NotesRepository notesRepository;
//
//    @Override
//    public void setUp() {
//        // super.setUp();
//        // notesResource = new NotesResource();
//    }
//
//    // @Override
//    // @Before
//    // public void setUp() throws Exception {
//    // spy = setUpMockedApplication(new NotesApplication());
//    // notesRepository = Mockito.mock(NotesRepository.class);
//    // notesResource = new NotesResource();
//    // noteResource = new NoteResource();
//    // setupUserRepository();
//    // }
//    //
//    // private void setupUserRepository() {
//    // Mockito.doAnswer(new Answer<NotesRepository>() {
//    // @Override
//    // public NotesRepository answer(InvocationOnMock invocation) throws Throwable {
//    // return notesRepository;
//    // }
//    // }).when(spy).getNotesRepository();
//    // }
//
//    // @Test
//    // public void empty_repository_returns_list_with_zero_entities() throws Exception {
//    // SkysailResponse<List<Note>> entities = notesResource.getEntities();
//    // assertThat(entities.getSuccess(), is(true));
//    // assertThat(entities.getData().size(), is(0));
//    // }
//
//    // @Test
//    // public void new_entity_is_returned_from_repository_after_being_added() throws Exception {
//    // Form form = Mockito.mock(Form.class);
//    // Mockito.when(form.getFirstValue("title")).thenReturn("title");
//    // Mockito.when(form.getFirstValue("content")).thenReturn("content");
//    // noteResource.addEntity2(form);
//    // SkysailResponse<List<Note>> entities = notesResource.getEntities();
//    // assertThat(entities.getSuccess(), is(true));
//    // assertThat(entities.getData().size(), is(1));
//    // }
//
// }
