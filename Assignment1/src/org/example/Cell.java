package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Cell extends JPanel {
    private final int row;
    private final int col;
    private CellType cellType;

    public Cell(int row, int col, CellType type) {
        this.row = row;
        this.col = col;
        this.cellType = type;

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);
        setOpaque(true);
    }

    public CellType getCellType() {
        return cellType;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }
    public CellType getCellType(CellType cellType) {
        return cellType;
    }

    public enum CellType {
        EMPTY,
        START,
        END,
        FRONTIER,
        OBSTACLE,
        VISITED,
        PATH
    }
}
