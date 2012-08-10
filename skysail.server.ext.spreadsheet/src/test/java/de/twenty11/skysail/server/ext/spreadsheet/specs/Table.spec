package de.twenty11.skysail.server.ext.spreadsheet.specs

import de.twenty11.skysail.server.ext.spreadsheet.internal.InMemoryTable

describe "A Table, i.e. a matrix of cells"{
	
	fact "initial table with 5 columns and 4 rows has cell at position 5,4" {
		new InMemoryTable(5,4).getCell(5,4) => ""
	}
	
	
}