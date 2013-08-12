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

import org.junit.Test;
import org.restlet.data.Form;
import org.restlet.resource.ClientResource;

import de.twenty11.skysail.server.ext.notes.NotesApplication;

import static org.hamcrest.Matchers.containsString;

import static org.junit.Assert.assertThat;

public class FoldersResourceIT extends IntegrationTestBase {

    @Test
    public void should_successfully_retrieve_the_folders_with_get_request() throws Exception {
        ClientResource cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH));

        String json = cr.get().getText();

        assertThat(json, containsString("\"success\":true"));
        // assertThat(json, containsString("\"data\":[]"));
    }

    @Test
    public void should_add_new_folder_with_post() throws Exception {
        ClientResource cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH));
        Form form = new Form();
        form.add("folderName", "foldername");

        cr.post(form);

        cr = new ClientResource(requestUrlFor(NotesApplication.FOLDERS_PATH));
        String json = cr.get().getText();
        assertThat(json, containsString("\"data\":[{\"parent\":null,\"folderName\":\"foldername\",\"children\":[]}]"));
    }

}
