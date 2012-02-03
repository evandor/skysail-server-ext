/**
 *  Copyright 2011 Carsten Gräf
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * 
 */

/**
 * 
 */
package de.twenty11.skysail.server.ext.freemarker.devConfig.internal;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.twenty11.skysail.server.ext.freemarker.devConfig.internal.WorkspaceUrlTemplateLoader;

/**
 * @author carsten
 *
 */
public class WorkspaceUrlTemplateLoaderTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private WorkspaceUrlTemplateLoader loader;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        loader = new WorkspaceUrlTemplateLoader();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link de.twenty11.skysail.server.ext.freemarker.devConfig.internal.WorkspaceUrlTemplateLoader#getURL(java.lang.String)}.
     */
    @Test
    public void testGetURLStringWithNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("provided identifier is empty");
        loader.getURL(null);
    }

    @Test
    public void testGetURLStringWithEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("provided identifier is empty");
        loader.getURL(" ");
    }

    @Test
    public void testGetURLStringWithMissingDoublePoint() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("provided identifier is not of the form '*:c'");
        loader.getURL("a");
    }

    @Test
    public void testGetURLStringWithMissingFirstValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("provided identifier is not of the form '*:c'");
        loader.getURL(":a");
    }

    @Test
    public void testGetURLStringWithMissingLastValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("provided identifier is not of the form '*:c'");
        loader.getURL("a:");
    }

    @Test
    public void testGetURLStringWithWrongBundleName() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("provided identifier is not of the form 'a.b*:c'");
        loader.getURL("a:c");
    }

}
