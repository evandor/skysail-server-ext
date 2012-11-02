package de.twenty11.skysail.server.ext.spreadsheet.specs;

import de.twenty11.skysail.server.ext.spreadsheet.internal.InMemoryCell;
import de.twenty11.skysail.server.ext.spreadsheet.specs.InMemoryCellSpec;
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
@Named("when empty")
public class InMemoryCellWhenEmptySpec extends InMemoryCellSpec {
  @Test
  @Named("new InMemoryCell[].getText[] should be \\\"\\\"")
  @Order(99)
  public void newInMemoryCellGetTextShouldBe() throws Exception {
    InMemoryCell _inMemoryCell = new InMemoryCell();
    String _text = _inMemoryCell.getText();
    boolean _should_be = Should.should_be(_text, "");
    Assert.assertTrue("\nExpected new InMemoryCell().getText() should be \"\" but"
     + "\n     new InMemoryCell().getText() is " + new StringDescription().appendValue(_text).toString()
     + "\n     new InMemoryCell() is " + new StringDescription().appendValue(_inMemoryCell).toString() + "\n", _should_be);
    
  }
  
  @Test
  @Named("text is something after adding something")
  @Order(99)
  public void textIsSomethingAfterAddingSomething() throws Exception {
    this.subject.addText("something");
    String _text = this.subject.getText();
    boolean _doubleArrow = Should.operator_doubleArrow(_text, "something");
    Assert.assertTrue("\nExpected subject.getText => \"something\" but"
     + "\n     subject.getText is " + new StringDescription().appendValue(_text).toString()
     + "\n     subject is " + new StringDescription().appendValue(this.subject).toString() + "\n", _doubleArrow);
    
  }
}
