package org.example;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CellData{
    private CellType cellType;
    private final int row;
    private final int col;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);


    public CellData(int row, int col,CellType cellType) {
        this.cellType = cellType;
        this.row = row;
        this.col = col;
    }
    public CellType getCellType() {
        return cellType;
    }
    public void setCellType(CellType cellType) {
        pcs.firePropertyChange("cellType",this.cellType,cellType);
        this.cellType = cellType;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
}
