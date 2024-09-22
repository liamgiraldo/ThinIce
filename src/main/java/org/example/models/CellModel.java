package org.example.models;

import java.awt.*;

public class CellModel {

    private int cellSize = 50;

    private int x;
    private int y;

    public enum CellType {
        ICE, WALL, WATER, EMPTY, END, START
    }
    private CellType type;
    public CellModel(CellType type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public boolean isIce() {
        return type == CellType.ICE;
    }

    public boolean isWall() {
        return type == CellType.WALL;
    }

    public boolean isWater() {
        return type == CellType.WATER;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void draw(Graphics g, Image texture){ {
        g.drawImage(texture, this.x * cellSize, this.y * cellSize, cellSize, cellSize, null);
    }
}
}
