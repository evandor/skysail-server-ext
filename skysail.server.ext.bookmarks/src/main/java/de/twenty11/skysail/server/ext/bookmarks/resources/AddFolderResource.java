package de.twenty11.skysail.server.ext.bookmarks.resources;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;

import de.twenty11.skysail.common.responses.FormResponse;
import de.twenty11.skysail.server.ext.bookmarks.BookmarkApplication;
import de.twenty11.skysail.server.ext.bookmarks.domain.Folder;
import de.twenty11.skysail.server.restlet.AddServerResource2;

public class AddFolderResource extends AddServerResource2<Folder> {

    @Override
    @Get("html")
    public FormResponse<Folder> createForm() {
        FormResponse<Folder> formResponse = new FormResponse<Folder>(new Folder());
        formResponse.setMessage("Add a new folder");
        return formResponse;
    }

    @Override
    public Folder getData(Form form) {
        return new Folder(form.getFirstValue("name"), null);
    }

    @Override
    public Representation addEntity(Folder entity) {
        BookmarkApplication app = (BookmarkApplication) getApplication();
        app.getFolderRepository().add(entity);
        // getResponse().redirectTemporary(new Reference(getReferrerRef().getParentRef()));

        ClientResource cr = new ClientResource("riap://application");
        cr.setChallengeResponse(getChallengeResponse());
        Representation representation = cr.get();
        return representation;
        // getResponse().setLocationRef(new Reference(getReferrerRef().getParentRef()));
        // return new SuccessResponse<Bookmark>();
    }

}
