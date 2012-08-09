package de.twenty11.skysail.server.ext.spreadsheet.test;

import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.twenty11.skysail.server.ext.spreadsheet.Cell;
import de.twenty11.skysail.server.ext.spreadsheet.internal.InMemoryCell;

/**
 * @author carsten
 * 
 */
public class SpreadsheetTest {

    Cell cell = new InMemoryCell();
    
    @Before
    public void setUp() throws Exception {
        
    }

    @Test
    public void testConstructor() {
        String testtext = "testtext";
        cell.addText(testtext);
        Assert.assertThat(testtext, is(cell.getText()));
    }

}
