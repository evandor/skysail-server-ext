package de.twenty11.skysail.server.ext.spreadsheet.specs

import de.twenty11.skysail.server.ext.spreadsheet.internal.InMemoryCell
import de.twenty11.skysail.server.ext.spreadsheet.Cell

import static extension org.jnario.lib.Should.*

describe "A Cell" {
	
	Cell mycell = new InMemoryCell
	
	fact new InMemoryCell().getText() should be ""
	
	fact "text is something after adding something" {
		mycell.addText("something")
		mycell.getText => "something"
	}
	
	fact "text in cell is not changed when returned text is altered" {
		mycell.addText("something")
		var content = mycell.getText
		content = "changed"
		mycell.getText => "something"
	}
	
	fact "cells x-position is greater or equal 0" {
		mycell.column => 0
	}

	fact "cells y-position is greater or equal 0" {
		mycell.row => 0
	}

}