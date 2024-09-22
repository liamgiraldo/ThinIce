package org.example.models;

import org.example.controllers.TextureController;

import java.awt.*;

public class BoardModel {
    //a game board is a grid of cells
    //each cell can be ice, contain a player, or be a wall
    private CellModel[][] cells;
    private CellModel[][] originalCells;

    private int startingX;
    private int startingY;

    private int endX;
    private int endY;

    public BoardModel(CellModel[][] cells, int startingX, int startingY) {
        this.cells = cells;
        this.originalCells = new CellModel[cells.length][cells[0].length];
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                originalCells[i][j] = new CellModel(cells[i][j].getType(), j, i);
            }
        }
        this.startingX = startingX;
        this.startingY = startingY;

        findEnd();

        System.out.println(this);
    }

    public void draw(Graphics g, TextureController textureController){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
//                if(cells[i][j] == null){
//                    continue;
//                }
                CellModel.CellType type = cells[i][j].getType();
                Image image = textureController.getImageWithCellType(type);
                cells[i][j].draw(g, image);
            }
        }
    }

    public CellModel getCell(int x, int y){
        return cells[y][x];
    }

    public void setCell(int x, int y, CellModel cell){
        cells[y][x] = cell;
    }

    public int getStartingX(){
        return startingX;
    }

    public int getStartingY(){
        return startingY;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CellModel[] row : cells) {
            for (CellModel cell : row) {
                sb.append(cell.getType().name().charAt(0));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void findEnd(){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                if(cells[i][j].getType() == CellModel.CellType.END){
                    this.endX = j;
                    this.endY = i;
                    return;
                }
            }
        }
    }

    public int getEndX(){
        return endX;
    }

    public int getEndY(){
        return endY;
    }

    public int getRows(){
        return cells.length;
    }

    public int getCols(){
        return cells[0].length;
    }

    public void reset(){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                cells[i][j] = new CellModel(originalCells[i][j].getType(), j, i);
            }
        }
    }
}
