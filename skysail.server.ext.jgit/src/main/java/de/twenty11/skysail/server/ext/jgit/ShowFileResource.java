package de.twenty11.skysail.server.ext.jgit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.restlet.data.Form;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.UniqueResultServerResource2;
import de.twenty11.skysail.server.descriptors.FileDescriptor;
import de.twenty11.skysail.server.ext.jgit.internal.MyApplication;

@Presentation(preferred = PresentationStyle.ACE_EDITOR)
public class ShowFileResource extends UniqueResultServerResource2<FileDescriptor> {

    private File file;

    @Override
    protected void doInit() throws ResourceException {
        String id = (String) getRequest().getAttributes().get("id");
        LocalRepositoryDescriptor repositoryDescriptor = ((MyApplication) getApplication()).getRepository()
                .getLocalRepositoryDescriptor(id);
        String rootPath = repositoryDescriptor.getPath(); // /tmp/test
        String filepath = rootPath + "/" + getReference().getRemainingPart();
        file = new File(filepath);
    }

    @Override
    @Get("html|json")
    public SkysailResponse<FileDescriptor> getEntity() {
        return getEntity("Content of " + file.getName());
    }

    @Override
    protected FileDescriptor getData() {
        if (!file.isFile()) {
            throw new IllegalStateException(file.toString() + " is not a file");
        }
        int lastIndexOfDot = getReference().getRemainingPart().lastIndexOf('.');
        String extension = getReference().getRemainingPart().substring(lastIndexOfDot);
        return new FileDescriptor(readFile(file), extension);
    }

    private String readFile(File file) {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(text + "\n");
            }
        } catch (Exception ex) {
            return "Exception reading file: \n" + ex.getMessage();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
            }
        }
        return stringBuffer.toString();
    }

    @Override
    public FileDescriptor getData(Form form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SkysailResponse<?> addEntity(FileDescriptor entity) {
        // TODO Auto-generated method stub
        return null;
    }

}
