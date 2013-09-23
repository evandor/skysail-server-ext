package de.twenty11.skysail.server.ext.notes.domain.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.twenty11.skysail.server.ext.notes.domain.Folder;

public class FolderTest {

    private Folder root;

    @Before
    public void setUp() throws Exception {
        root = Folder.createRoot("rootfolder");
    }

    @After
    public void tearDown() throws Exception {
    }

    // @Test
    // public void should_provide_its_name_in_html_output() {
    // assertThat(root.getHtml(), containsString("rootfolder"));
    // }

    @Test
    public void subfolder_should_link_to_right_parent() {
        Folder subFolder = new Folder(root, "subfolder");
        assertThat(subFolder.getParent(), is(root));
    }

    @Test
    @Ignore
    public void folders_are_equal_if_they_have_same_id() {
        Folder subFolder1 = new Folder(root, "subfolder");
        Folder subFolder2 = new Folder(root, "subfolder");
        assertThat(subFolder1, is(equalTo(subFolder2)));
    }

}
