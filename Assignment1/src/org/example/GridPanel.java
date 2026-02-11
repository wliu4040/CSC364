package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
                Cell cell = new Cell(r, c, Cell.CellType.EMPTY);
                add(cell);
                cells[r][c] = cell;
            }
        }
        addMouseListener(new MouseNanny());
        addMouseMotionListener(new MouseNanny());
    }

    public void setCell(Cell cell, Cell.CellType type) {

        switch(type) {
            case START:
                if (start != null) {
                    start.setBackground(Color.WHITE);
                    start.setCellType(Cell.CellType.EMPTY);
                }
                start = cell;
                break;
            case END:
                if (end != null) {
                    end.setBackground(Color.WHITE);
                    end.setCellType(Cell.CellType.EMPTY);
                }
                end = cell;
                break;
        }
        if(type != cell.getCellType()) {
            cells[cell.getRow()][cell.getCol()].setCellType(type);
            cell.updateColor();
            pcs.firePropertyChange("cell",null, null);
        }
    }

    class MouseNanny extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            changeCell(getComponentAt(e.getPoint()));
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (View.getInstance().getUserClickType().equals("OBSTACLE")) {
                Component component = getComponentAt(e.getPoint());
                if(component instanceof Cell) {
                    changeCell(component);
                }
            }
        }

        public void changeCell(Component comp) {
            if(comp instanceof Cell cell) {
                Cell.CellType cellTypeEnum = Cell.CellType.valueOf(View.getInstance().getUserClickType());
                setCell(cell, cellTypeEnum);
            }
        }




    }
}

