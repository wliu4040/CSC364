package org.example;

import java.awt.*;

public class GridData{
    private static CellData[][] grid;
    private static CellData start;
    private static CellData end;

    public static void refreshBlackboard(int size) {
        grid = new CellData[size][size];
        start = null;
        end = null;
        for(int row=0;row<size;row++) {
            for(int col=0;col<size;col++) {
                grid[row][col] = new CellData(row,col,CellType.EMPTY);
            }
        }
    }
    public static CellData getCell(int row, int col) {
        if(row >= 0 && col >= 0 && row < grid.length && col < grid[0].length) {
            return grid[row][col];
        }
        else return null;

    }
    public static void setCell(int row, int col, CellType type) {
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
    public static CellData getEnd() {
        return end;
    }
    public static CellData getStart() {
        return start;
    }
}
