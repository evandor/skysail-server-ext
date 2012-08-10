package de.twenty11.skysail.server.ext.spreadsheet;


public interface Cell {

    int getColumn();

    int getRow();

    void addText(String testtext);

    String getText();
    

}
