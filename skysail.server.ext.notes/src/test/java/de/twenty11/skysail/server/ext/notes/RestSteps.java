package de.twenty11.skysail.server.ext.notes;

import java.io.IOException;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import de.twenty11.skysail.server.ResourceTestWithUnguardedAppication;

public class RestSteps extends ScenarioSteps {

    private static final long serialVersionUID = -4407369914748239096L;

    public RestSteps(Pages pages) {
        super(pages);
    }

    public void login(String username, String credentials) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(username, credentials);
        subject.login(token);
    }

    @Step
    public void createFolder() {

    }

    @Step
    public String postFolderWithAjax(String name) {
        ClientResource cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH + "/?media=json"));
        Form form = new Form();
        form.add("folderName", name);
        return invokeAndHandleException(cr.post(form));
    }

    @Step
    public String postFolder(String folderName) {
        ClientResource cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH + "/?debug=true"));
        Form form = new Form();
        form.add("folderName", folderName);
        return invokeAndHandleException(cr.post(form));
    }

    @Step
    public String deleteFolder(Integer folderId) {
        ClientResource cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH + "/" + folderId));
        return invokeAndHandleException(cr.delete());
    }

    @Step
    public String getFolder(Integer folderId) {
        ClientResource cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH + "/" + folderId));
        return invokeAndHandleException(cr.get());
    }

    protected String requestUrlFor(String resource) {
        return "http://localhost:" + ResourceTestWithUnguardedAppication.TEST_PORT + resource;
    }

    private String invokeAndHandleException(Representation representation) {
        try {
            return representation.getText();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

}
