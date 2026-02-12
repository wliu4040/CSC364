package org.example;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import static java.util.Map.entry;

public class Cell extends JPanel implements PropertyChangeListener {
    private final int row;
    private final int col;

    private final Map<CellType, Color> colorMap =
            Map.ofEntries(
                    entry(CellType.EMPTY,new Color(255,255,255)),
                    entry(CellType.START, new Color(255,0,0)),
                    entry(CellType.OBSTACLE, new Color(128,128,128)),
                    entry(CellType.FRONTIER, new Color(144,238,144)),
                    entry(CellType.END,new Color(0,0,255)),
                    entry(CellType.VISITED,new Color(192,192,192)),
                    entry(CellType.PATH, new Color(64,224,208)));

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);
        setOpaque(true);
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    public void updateColor(CellType cellType) {
        setBackground(colorMap.get(cellType));
    }

    public CellType getCellType(CellType cellType) {
        return cellType;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("paint");
        if(evt.getNewValue() instanceof CellType cellType) {
            updateColor(cellType);
            repaint();
        }
    }
}
