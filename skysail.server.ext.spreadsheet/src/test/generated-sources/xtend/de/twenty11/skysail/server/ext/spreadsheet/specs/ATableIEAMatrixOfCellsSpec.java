package de.twenty11.skysail.server.ext.spreadsheet.specs;

import de.twenty11.skysail.server.ext.spreadsheet.Cell;
import de.twenty11.skysail.server.ext.spreadsheet.internal.InMemoryTable;
import org.hamcrest.StringDescription;
import org.jnario.lib.Should;
import org.jnario.runner.ExampleGroupRunner;
import org.jnario.runner.Named;
import org.jnario.runner.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("all")
@RunWith(ExampleGroupRunner.class)
@Named("A Table, i.e. a matrix of cells")
public class ATableIEAMatrixOfCellsSpec {
  @Test
  @Named("initial table with 5 columns and 4 rows has cell at position 5,4")
  @Order(99)
  public void initialTableWith5ColumnsAnd4RowsHasCellAtPosition54() throws Exception {
    InMemoryTable _inMemoryTable = new InMemoryTable(5, 4);
    Cell _cell = _inMemoryTable.getCell(5, 4);
    boolean _doubleArrow = Should.operator_doubleArrow(_cell, "");
    Assert.assertTrue("\nExpected new InMemoryTable(5,4).getCell(5,4) => \"\" but"
     + "\n     new InMemoryTable(5,4).getCell(5,4) is " + new StringDescription().appendValue(_cell).toString()
     + "\n     new InMemoryTable(5,4) is " + new StringDescription().appendValue(_inMemoryTable).toString() + "\n", _doubleArrow);
    
  }
}
