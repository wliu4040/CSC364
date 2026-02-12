package org.example;

import java.awt.*;

public class GridData{
    private static GridData gridData;
    private static CellData[][] grid;
    private static CellData start;
    private static CellData end;

    private GridData(int size) {
        grid = new CellData[size][size];
    }

    public static GridData getBlackboard() {
        if(gridData == null) {
            gridData = new GridData(10);
        }
        return gridData;
    }

    public static GridData refreshBlackboard(int size) {
        gridData = new GridData(size);
        grid = new CellData[size][size];
        for(int row=0;row<size;row++) {
            for(int col=0;col<size;col++) {
                grid[row][col] = new CellData(row,col,CellType.EMPTY);
            }
        }
        return gridData;
    }
    public CellData getCell(int row, int col) {
        return grid[row][col];
    }
    public void setCell(int row, int col, CellType type) {
        CellData cell = grid[row][col];
        switch(type) {
            case START:
                if (start != null) {
                    start.setCellType(CellType.EMPTY);
                }
                start = cell;
                break;
            case END:
                if (end != null) {
                    end.setCellType(CellType.EMPTY);
                }
                end = cell;
                break;
        }
        if(type != cell.getCellType()) {
            grid[row][col].setCellType(type);
        }
    }

}
