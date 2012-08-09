package de.twenty11.skysail.server.ext.spreadsheet.specs

import de.twenty11.skysail.server.ext.spreadsheet.internal.InMemoryCell

import static extension org.jnario.lib.Should.*

describe "A Cell"{
	
	fact "initial text is empty" {
		new InMemoryCell().getText() => ""
	}
	
	fact "text is something after adding something" {
		val subject = new InMemoryCell
		subject.addText("something")
		subject.getText => "something"
	}
	
	fact "text in cell is not changed when returned text is altered" {
		val subject = new InMemoryCell
		subject.addText("something")
		var content = subject.getText
		content = "changed"
		subject.getText => "something"
	}

}