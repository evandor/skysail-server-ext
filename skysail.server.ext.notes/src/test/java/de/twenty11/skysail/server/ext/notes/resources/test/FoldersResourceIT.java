/*
 * Copyright 2013 GRAEFCA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.twenty11.skysail.server.ext.notes.resources.test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import de.twenty11.skysail.server.ext.notes.NotesApplication;

import static org.hamcrest.Matchers.containsString;

import static org.junit.Assert.assertThat;

public class FoldersResourceIT extends IntegrationTestBase {

    private Form form;
    private ClientResource cr;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        form = new Form();
        cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH));
    }

    @Test
    public void should_successfully_retrieve_the_folders_with_get_request() throws Exception {
        String json = cr.get().getText();

        assertThat(json, containsString("\"success\":true"));
    }

    @Test
    public void should_add_new_folder_with_post() throws Exception {
        form.add("folderName", "foldername");

        cr.post(form);

        cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH));
        String json = cr.get().getText();
        assertThat(json, containsString("\"data\":[{\"parent\":null,\"folderName\":\"foldername\",\"children\":[]}]"));
    }

    @Test
    public void should_prevent_folder_without_name_from_being_added() throws Exception {
        form.add("folderName", "");

        Representation post = cr.post(form, MediaType.APPLICATION_JSON);

        assertThat(
                post.getText(),
                containsString("\"violations\":[{\"propertyPath\":\"folderName\",\"message\":\"name  must not be empty\"}]"));
    }

    @Test
    public void should_return_xml_when_mediaType_is_set_to_xml() throws Exception {
        form.add("folderName", "");

        String resultFromPost = cr.post(form, MediaType.APPLICATION_XML).getText();

        assertThat(resultFromPost, containsString("<success>false</success>"));
        assertThat(resultFromPost, containsString("<message>name  must not be empty</message>"));
    }

    @Test
    @Ignore
    public void should_trim_foldername_when_posting() throws Exception {
        form.add("folderName", " foldername ");

        cr.post(form);

        cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH));
        String json = cr.get().getText();
        assertThat(json, containsString("\"data\":[{\"parent\":null,\"folderName\":\"foldername\",\"children\":[]}]"));
    }

    @Test
    @Ignore
    public void should_sanitize_user_input() throws Exception {
        form.add("folderName", " foldername<script></script> ");

        String resultFromPost = cr.post(form, MediaType.APPLICATION_JSON).getText();

        assertThat(resultFromPost, containsString("<success>false</success>"));
    }

}
