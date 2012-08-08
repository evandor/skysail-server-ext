package de.twenty11.skysail.server.ext.spreadsheet.test;

import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.twenty11.skysail.server.ext.spreadsheet.Spreadsheet;
import de.twenty11.skysail.server.ext.spreadsheet.internal.InMemorySpreadsheet;

/**
 * @author carsten
 * 
 */
public class SpreadsheetTest {

    Spreadsheet spreadsheet = new InMemorySpreadsheet();
    
    @Before
    public void setUp() throws Exception {
        
    }

    @Test
    public void testConstructor() {
        String testtext = "testtext";
        spreadsheet.addText(0, 0, testtext);
        Assert.assertThat(testtext, is(spreadsheet.getText(0,0)));
    }

}
