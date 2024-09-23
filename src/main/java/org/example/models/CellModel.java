package org.example.models;

import java.awt.*;

public class CellModel {

    private static int cellSize = 50;

    //these are cell coordinates
    private int x;
    private int y;

    public enum CellType {
        ICE, WALL, WATER, EMPTY, END, START, KEY, DOOR, MONEY, PUSHBLOCK, THICKICE
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics g, Image texture, int xOffset, int yOffset) {
            g.drawImage(texture, this.x * cellSize + xOffset, this.y * cellSize + yOffset, cellSize, cellSize, null);
        }


        public void resize ( int newTileSize){
            cellSize = newTileSize;
        }

    }
