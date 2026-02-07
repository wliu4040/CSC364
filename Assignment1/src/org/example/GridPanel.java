package org.example;

import javax.swing.*;
import java.awt.*;

class GridPanel extends JPanel {
    public GridPanel(int rows, int cols) {
        setLayout(new GridLayout(rows, cols));

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                add(new Cell(r, c));
            }
        }
    }
}

