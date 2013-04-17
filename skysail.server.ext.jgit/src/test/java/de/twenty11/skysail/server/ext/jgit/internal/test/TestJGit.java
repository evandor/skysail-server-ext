package de.twenty11.skysail.server.ext.jgit.internal.test;

import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TestJGit {

    private String localPath, remotePath;
    private Repository localRepo;
    private Git git;

    @Before
    public void init() throws IOException {
        localPath = "C:\\tmp\\gittest";
        remotePath = "H:\\git\\infinitest";//git://github.com/evandor/skysail-build.git";
        localRepo = new FileRepository(localPath + "/.git");
        git = new Git(localRepo);
    }

    @Test
    public void testCreate() throws IOException {
        Repository newRepo = new FileRepository(localPath + ".git");
        newRepo.create();
    }

    @Test
    public void testClone() throws Exception {
        Git.cloneRepository()
                .setURI(remotePath)
                .setDirectory(new File(localPath))
                .call();
    }

    @Test
    public void testAdd() throws Exception {
        File myfile = new File(localPath + "/myfile");
        myfile.createNewFile();
        git.add()
                .addFilepattern("myfile")
                .call();
    }

    @Test
    public void testCommit() throws Exception {
        git.commit()
                .setMessage("Added myfile")
                .call();
    }

    @Test
    public void testPush() throws Exception {
        git.push()
                .call();
    }

    @Test
    @Ignore
    public void testTrackMaster() throws Exception {
        git.branchCreate()
                .setName("master")
                .setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.SET_UPSTREAM)
                .setStartPoint("origin/master")
                .setForce(true)
                .call();
    }

    @Test
    public void testPull() throws Exception {
        git.pull()
                .call();
    }
}
