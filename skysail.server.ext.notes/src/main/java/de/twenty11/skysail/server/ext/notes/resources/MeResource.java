//package de.twenty11.skysail.server.ext.notes.resources;
//
//import org.codehaus.jackson.map.ObjectMapper;
//import org.restlet.data.Form;
//import org.restlet.resource.Get;
//import org.restlet.resource.ResourceException;
//
//import de.twenty11.skysail.common.responses.SkysailResponse;
//import de.twenty11.skysail.server.core.restlet.UniqueResultServerResource2;
//import de.twenty11.skysail.server.ext.notes.NotesApplication;
//import de.twenty11.skysail.server.ext.notes.domain.Note;
//
///**
// * A resource representing "me" as a facebook user.
// * 
// * Handles login to facebook as well.
// * 
// */
//public class MeResource extends UniqueResultServerResource2<Note> {
//
//    private ObjectMapper mapper = new ObjectMapper();
//    private String meOnFacebookUrl;
//    private NotesApplication facebookApp;
//    private String currentUser;
//
//    @Override
//    protected void doInit() throws ResourceException {
//        facebookApp = (NotesApplication) getApplication();
//        currentUser = getRequest().getChallengeResponse().getIdentifier();
//        meOnFacebookUrl = "https://graph.facebook.com/1395451850";
//    }
//
//    @Override
//    @Get("html|json")
//    public SkysailResponse<Note> getEntity() {
//        // registerLinkedPage(new LoginLink(facebookApp, currentUser));
//        // registerLinkedPage(new LogoutLink(facebookApp, currentUser));
//        return getEntity("Me on Facebook");
//    }
//
//    @Override
//    protected Note getData() {
//        return null;
//    }
//
//    @Override
//    public Note getData(Form form) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public SkysailResponse<?> addEntity(Note entity) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
// }
