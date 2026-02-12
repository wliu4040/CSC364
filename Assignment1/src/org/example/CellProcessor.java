package org.example;


import java.util.ArrayList;
import java.util.List;

public class CellProcessor implements Runnable{
    private final CellData end;
    private final BFS bfs;
    public CellProcessor(CellData cellData, BFS bfs) {
        this.end = cellData;
        this.bfs = bfs;
    }

    @Override
    public void run() {
        Node prevNode = null;
        while(true) {
            Node node = bfs.pop(prevNode);
            CellData cellData = node.cell();
            if(cellData == end) {
                bfs.validate(node);
                lightPath();
                return;
            }
            if(cellData.getCellType() != CellType.START) {
                cellData.setCellType(CellType.VISITED);
            }
            List<Node> neighbors = getNeighbors(node);
            for(Node neighbor : neighbors) {
                bfs.enqueue(neighbor);
            }
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            prevNode = node;
        }
    }

    public void lightPath() {
        CellData currCell = end;
        while(currCell.getPrev() != null) {
            CellType cellType = currCell.getCellType();
            if(cellType != CellType.START && cellType != CellType.END) {
                currCell.setCellType(CellType.PATH);
            }
            currCell = currCell.getPrev();
        }

    }


    public List<Node> getNeighbors(Node node) {
        CellData cellData = node.cell();
        int row = cellData.getRow();
        int col = cellData.getCol();
        int distance = node.distance();
        List<Node> returnList = new ArrayList<>();
        CellData upperNode = GridData.getCell(row, col + 1);
        CellData lowerNode = GridData.getCell(row , col - 1);
        CellData leftNode = GridData.getCell(row - 1, col);
        CellData rightNode = GridData.getCell(row + 1, col);
        processData(returnList,upperNode,cellData,distance);
        processData(returnList,lowerNode,cellData,distance);
        processData(returnList,leftNode,cellData,distance);
        processData(returnList,rightNode,cellData,distance);
        return returnList;
    }

    private void processData(List<Node> returnList, CellData cellData, CellData prev, int distance) {
        if(cellData == null) {
            return;
        }
        CellType cellType = cellData.getCellType();
        if(cellType == CellType.FRONTIER || cellType == CellType.VISITED || cellType == CellType.START || cellType == CellType.OBSTACLE) {return;}
        cellData.setPrev(prev);
        if(cellType != CellType.END) {
            cellData.setCellType(CellType.FRONTIER);
        }
        returnList.add(new Node(cellData, distance + 1));
    }
}
