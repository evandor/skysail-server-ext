package de.twenty11.skysail.server.ext.spreadsheet.internal;

import de.twenty11.skysail.server.ext.spreadsheet.Cell;

public class InMemoryCell implements Cell {

    private String text = "";

    @Override
    public void addText(String given) {
        if (given == null) {
            this.text = "";        
        } else {
            this.text = given;
        }
    }

    @Override
    public String getText() {
        return text;
    }

}
