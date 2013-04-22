package de.twenty11.skysail.server.ext.jgit;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.jgit.internal.MyApplication;
import de.twenty11.skysail.server.restlet.ListServerResource2;

public class ListDirResource extends ListServerResource2<DirectoryDescriptor> {

    private LocalRepositoryDescriptor repositoryDescriptor;
    private String filepath;

    @Override
    protected void doInit() throws ResourceException {
        String id = (String) getRequest().getAttributes().get("id");
        repositoryDescriptor = ((MyApplication) getApplication()).getRepository().getLocalRepositoryDescriptor(id);
        String rootPath = repositoryDescriptor.getPath(); // /tmp/test
        filepath = rootPath + "/" + getReference().getRemainingPart();
    }

    @Override
    @Get("html|json|csv")
    public SkysailResponse<List<DirectoryDescriptor>> getEntities() {
        return getEntities("contents for directory " + filepath);
    }

    @Override
    protected List<DirectoryDescriptor> getData() {
        List<DirectoryDescriptor> result = new ArrayList<DirectoryDescriptor>();
        File file = new File(filepath);
        String[] contents = file.list();
        if (contents == null) {
            return Collections.emptyList();
        }
        boolean pomFileFound = false;
        for (String filename : contents) {
            if (filename.equals("pom.xml")) {
                pomFileFound = true;
            }
            String link = getReference().getRemainingPart();
            boolean isFile = new File(filepath + "/" + filename).isFile();
            if (isFile) {
                link = getReference().getBaseRef() + "../showfile/" + link + "/" + filename;
            } else {
                link = getReference().getBaseRef() + link + "/" + filename;
            }
            result.add(new DirectoryDescriptor(filename, link));
        }
        if (pomFileFound) {
            addLinkedPageForMavenExecution();
        }
        return result;
    }

    private void addLinkedPageForMavenExecution() {
        // if (cloneCommand.applicable()) {
        registerLinkedPage(new ExecuteMavenPage(repositoryDescriptor));
        // }

    }

}
