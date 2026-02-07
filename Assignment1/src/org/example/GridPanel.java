package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeSupport;

class GridPanel extends JPanel {
    Cell[][] cells;
    Cell start;
    Cell end;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public GridPanel(int rows, int cols) {
        setLayout(new GridLayout(rows, cols));
        this.cells = new Cell[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                add(new Cell(r, c, Cell.CellType.EMPTY));
                cells[r][c] = new Cell(r, c, Cell.CellType.EMPTY);
            }
        }
        addMouseListener(new MouseNanny() {});
    }

    public void setCell(Cell cell, Cell.CellType type) {
        if (type == Cell.CellType.START) {
            if (start != null) {
                start.setBackground(Color.WHITE);
                start.setCellType(Cell.CellType.EMPTY);
            }
            cell.setBackground(Color.RED);
            start = cell;
        }
        if (type == Cell.CellType.END) {
            if (end != null) {
                end.setBackground(Color.WHITE);
                end.setCellType(Cell.CellType.EMPTY);
            }
            cell.setBackground(Color.BLUE);
            end = cell;
        }
        if(type != cell.getCellType()) {
            pcs.firePropertyChange("cell",null, new CellChange(cell, type));
            cells[cell.getRow()][cell.getCol()].setCellType(type);
        }
    }

    class MouseNanny extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            Component clicked =
                    getComponentAt(e.getPoint());

            if (clicked instanceof Cell cell) {
                int cols = cells[0].length;
                int index = getComponentZOrder(cell);
                int row = index / cols;
                int col = index % cols;
                Cell.CellType cellTypeEnum = Cell.CellType.valueOf(View.getInstance().getUserClickType());
                setCell(cell, cellTypeEnum);
            }
        }

    }
}

