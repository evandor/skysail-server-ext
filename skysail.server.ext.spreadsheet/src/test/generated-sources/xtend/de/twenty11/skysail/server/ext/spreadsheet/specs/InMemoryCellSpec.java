package de.twenty11.skysail.server.ext.spreadsheet.specs;

import de.twenty11.skysail.server.ext.spreadsheet.internal.InMemoryCell;
import de.twenty11.skysail.server.ext.spreadsheet.specs.InMemoryCellWhenEmptySpec;
import de.twenty11.skysail.server.ext.spreadsheet.specs.InMemoryCellWithElementsSpec;
import org.hamcrest.StringDescription;
import org.jnario.lib.Should;
import org.jnario.runner.Contains;
import org.jnario.runner.ExampleGroupRunner;
import org.jnario.runner.Named;
import org.jnario.runner.Order;
import org.jnario.runner.Subject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@Contains({ InMemoryCellWhenEmptySpec.class, InMemoryCellWithElementsSpec.class })
@SuppressWarnings("all")
@RunWith(ExampleGroupRunner.class)
@Named("InMemoryCell")
public class InMemoryCellSpec {
  @Subject
  public InMemoryCell subject;
  
  @Test
  @Named("text in cell is not changed when returned text is altered")
  @Order(99)
  public void textInCellIsNotChangedWhenReturnedTextIsAltered() throws Exception {
    this.subject.addText("something");
    String content = this.subject.getText();
    content = "changed";
    String _text = this.subject.getText();
    boolean _doubleArrow = Should.operator_doubleArrow(_text, "something");
    Assert.assertTrue("\nExpected subject.getText => \"something\" but"
     + "\n     subject.getText is " + new StringDescription().appendValue(_text).toString()
     + "\n     subject is " + new StringDescription().appendValue(this.subject).toString() + "\n", _doubleArrow);
    
  }
  
  @Test
  @Named("cells x-position is greater or equal 0")
  @Order(99)
  public void cellsXPositionIsGreaterOrEqual0() throws Exception {
    int _column = this.subject.getColumn();
    boolean _doubleArrow = Should.operator_doubleArrow(Integer.valueOf(_column), Integer.valueOf(0));
    Assert.assertTrue("\nExpected subject.column => 0 but"
     + "\n     subject.column is " + new StringDescription().appendValue(Integer.valueOf(_column)).toString()
     + "\n     subject is " + new StringDescription().appendValue(this.subject).toString() + "\n", _doubleArrow);
    
  }
  
  @Test
  @Named("cells y-position is greater or equal 0")
  @Order(99)
  public void cellsYPositionIsGreaterOrEqual0() throws Exception {
    int _row = this.subject.getRow();
    boolean _doubleArrow = Should.operator_doubleArrow(Integer.valueOf(_row), Integer.valueOf(0));
    Assert.assertTrue("\nExpected subject.row => 0 but"
     + "\n     subject.row is " + new StringDescription().appendValue(Integer.valueOf(_row)).toString()
     + "\n     subject is " + new StringDescription().appendValue(this.subject).toString() + "\n", _doubleArrow);
    
  }
}
