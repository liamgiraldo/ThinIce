package org.example.models;

import java.awt.*;

public class PlayerModel {
    private int x = 0;
    private int y = 0;

    private int row = 0;
    private int col = 0;

    private int cellSize = 50;
    private state playerState = state.ALIVE;
    private int lives;
    private Image texture;

    private enum state {
        ALIVE, DEAD
    }

    public PlayerModel(int lives, Image texture) {
        this.lives = lives;
        this.texture = texture;
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

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void moveUp(){
        y-=cellSize;
        row = y / cellSize;
    }

    public void moveDown(){
        y+=cellSize;
        row = y / cellSize;
    }

    public void moveLeft(){
        x-=cellSize;
        col = x / cellSize;
    }

    public void moveRight(){
        x+=cellSize;
        col = x / cellSize;
    }

    public void setRow(int row) {
        this.row = row;
        y = row * cellSize;
    }

    public void setCol(int col) {
        this.col = col;
        x = col * cellSize;
    }

    //int x and y are the coordinates to return the player to on death
    //returns true if the player has ran out of lives
    public boolean die(int x, int y) {
        this.x = x;
        this.y = y;
        lives--;
        return lives == 0;
    }

    public void setTexture(Image texture) {
        this.texture = texture;
    }

    public Image getTexture() {
        return texture;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public void draw(Graphics g) {
        if (texture != null && texture.getWidth(null) != -1 && texture.getHeight(null) != -1) {
            g.drawImage(texture, this.x, this.y, cellSize, cellSize, null);
//            System.out.println("Drawing player at " + x + ", " + y);
//            System.out.println("Texture width: " + texture.getWidth(null));
//            System.out.println("Texture height: " + texture.getHeight(null));
        } else {
//            System.out.println("Texture is not loaded properly.");
        }
    }
}
