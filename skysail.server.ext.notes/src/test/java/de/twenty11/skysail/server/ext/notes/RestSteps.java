package de.twenty11.skysail.server.ext.notes;

import java.io.IOException;

import org.restlet.data.Form;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.server.ResourceTestWithUnguardedAppication;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;

public class RestSteps extends ScenarioSteps {

	private static final long serialVersionUID = -4407369914748239096L;

	public RestSteps(Pages pages) {
		super(pages);
	}
	
	@Step
	public void createFolder() {
		
	}

	@Step
	public String postFolderWithAjax(String name) throws Exception {
		ClientResource cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH + "?media=json"));
        Form form = new Form();
        form.add("folderName", name);
        return cr.post(form).getText();
	}

	@Step
	public String postFolder(String folderName) throws Exception {
        ClientResource cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH));
        Form form = new Form();
        form.add("folderName", folderName);
        form.add("debug","true");
        return cr.post(form).getText();
	}

    @Step
	public String deleteFolder(Integer folderId) throws Exception {
        ClientResource cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH + "/" + folderId));
        return cr.delete().getText();
	}

    @Step
	public String getFolder(Integer folderId) throws Exception {
        ClientResource cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH + "/" + folderId));
        return cr.get().getText();
	}

    protected String requestUrlFor(String resource) {
        return "http://localhost:" + ResourceTestWithUnguardedAppication.TEST_PORT + resource;
    }



}
