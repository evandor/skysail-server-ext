package de.twenty11.skysail.server.ext.spreadsheet.specs

import de.twenty11.skysail.server.ext.spreadsheet.internal.InMemoryCell
import de.twenty11.skysail.server.ext.spreadsheet.Cell

import static extension org.jnario.lib.Should.*

describe InMemoryCell {
	
	//Cell mycell = new InMemoryCell
	
	context "when empty" {
		fact new InMemoryCell().getText() should be ""
	
	
		fact "text is something after adding something" {
			subject.addText("something")
			subject.getText => "something"
		}
	}
	
	context "with elements" {
    	//before subject.
    }
	
	fact "text in cell is not changed when returned text is altered" {
		subject.addText("something")
		var content = subject.getText
		content = "changed"
		subject.getText => "something"
	}
	
	fact "cells x-position is greater or equal 0" {
		subject.column => 0
	}

	fact "cells y-position is greater or equal 0" {
		subject.row => 0
	}

}