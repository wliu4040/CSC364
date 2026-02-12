package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class GridPanel extends JPanel{
    public Cell[][] cells;
    public GridPanel(int size) {
        GridData.refreshBlackboard(size);
        setLayout(new GridLayout(size, size));
        this.cells = new Cell[size][size];

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                Cell cell = new Cell(r, c);
                add(cell);
                cells[r][c] = cell;
                CellData cellData = GridData.getCell(r,c);
                cellData.addPropertyChangeListener(cell);
            }
        }
        addMouseListener(new MouseNanny());
        addMouseMotionListener(new MouseNanny());
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
                CellType cellTypeEnum = CellType.valueOf(View.getInstance().getUserClickType());
                int row = cell.getRow();
                int col = cell.getCol();
                GridData.setCell(row,col, cellTypeEnum);
            }
        }
    }
}

