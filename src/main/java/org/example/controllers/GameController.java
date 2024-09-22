package org.example.controllers;

import org.example.models.BoardModel;
import org.example.models.CellModel;
import org.example.models.PlayerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameController implements ActionListener, KeyListener {
    private ArrayList<BoardModel> boards;
    private PlayerModel player;
    private TextureController textureController;

    private int currentBoardIndex;
    private Timer gameLoop;

    public GameController(ArrayList<BoardModel> boards, TextureController textureController) {
        // Load textures
        this.textureController = textureController;

        this.boards = boards;
        currentBoardIndex = 0;

        gameLoop = new Timer(16, this);
        gameLoop.start();

        this.player = new PlayerModel(3, textureController.getPlayerImage());
        player.setCol(getCurrentBoard().getStartingX());
        player.setRow(getCurrentBoard().getStartingY());

        System.out.println("Timer started: " + gameLoop.isRunning());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (boards.isEmpty()) {
            return;
        }


        if(hasPlayerWon()){
            currentBoardIndex++;

            if(currentBoardIndex >= boards.size()){
                System.out.println("Game Over!");
                stop();
            } else {
                player.setRow(getCurrentBoard().getStartingY());
                player.setCol(getCurrentBoard().getStartingX());
            }

//            player.setX(getCurrentBoard().getStartingX());
//            player.setY(getCurrentBoard().getStartingY());
        }

        if(isPlayerSurroundedByAnythingButIce(player.getRow(), player.getCol())){
            player.setRow(getCurrentBoard().getStartingY());
            player.setCol(getCurrentBoard().getStartingX());
            getCurrentBoard().reset();
        }
    }

    public boolean hasPlayerWon() {
        return player.getRow() == getCurrentBoard().getEndY() && player.getCol() == getCurrentBoard().getEndX();
    }

    public void stop() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }

    public void draw(Graphics graphics) {
        if (!boards.isEmpty()) {
            if(currentBoardIndex < boards.size())
                boards.get(currentBoardIndex).draw(graphics, textureController);
        }
        player.draw(graphics);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    private void iceToWater(){
        if(getCurrentBoard().getCell(player.getCol(), player.getRow()).getType() == CellModel.CellType.ICE)
            getCurrentBoard().getCell(player.getCol(), player.getRow()).setType(CellModel.CellType.WATER);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Handle key press events
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (canLegallyMove(player.getRow() - 1, player.getCol())) {
                iceToWater();
                player.moveUp();
            }
//            player.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (canLegallyMove(player.getRow() + 1, player.getCol())) {
                iceToWater();
                player.moveDown();
            }
//            player.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(canLegallyMove(player.getRow(), player.getCol() - 1)) {
                iceToWater();
                player.moveLeft();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(canLegallyMove(player.getRow(), player.getCol() + 1)) {
                iceToWater();
                player.moveRight();
            }
        }
        System.out.println("Player row: " + player.getRow() + ", col: " + player.getCol());
    }

    private boolean canLegallyMove(int row, int col) {
        //check if moving to the new position is legal
        if(!isPlayerInBounds(col, row)) {
            return false;
        }
        //if the cell is water, we can't move there
        if(getCurrentBoard().getCell(col, row).getType() == CellModel.CellType.WATER) {
            return false;
        }

        //if the cell we are moving to is a wall, we can't move there
        return getCurrentBoard().getCell(col, row).getType() != CellModel.CellType.WALL;
    }

    private boolean isPlayerInBounds(int col, int row) {
        return row >= 0 && row < getCurrentBoard().getRows() && col >= 0 && col < getCurrentBoard().getCols();
    }

    private boolean isPlayerSurroundedByWater(int row, int col) {
        //check top, left, right, bottom
        return getCurrentBoard().getCell(col, row - 1).getType() == CellModel.CellType.WATER &&
                getCurrentBoard().getCell(col - 1, row).getType() == CellModel.CellType.WATER &&
                getCurrentBoard().getCell(col + 1, row).getType() == CellModel.CellType.WATER &&
                getCurrentBoard().getCell(col, row + 1).getType() == CellModel.CellType.WATER;
    }

    private boolean isPlayerSurroundedByAnythingButIce(int row, int col) {
        //check top, left, right, bottom
        //this doesn't include tiles that are end tiles
        return getCurrentBoard().getCell(col, row - 1).getType() != CellModel.CellType.ICE &&
                getCurrentBoard().getCell(col, row - 1).getType() != CellModel.CellType.END &&
                getCurrentBoard().getCell(col - 1, row).getType() != CellModel.CellType.ICE &&
                getCurrentBoard().getCell(col - 1, row).getType() != CellModel.CellType.END &&
                getCurrentBoard().getCell(col + 1, row).getType() != CellModel.CellType.ICE &&
                getCurrentBoard().getCell(col + 1, row).getType() != CellModel.CellType.END &&
                getCurrentBoard().getCell(col, row + 1).getType() != CellModel.CellType.ICE &&
                getCurrentBoard().getCell(col, row + 1).getType() != CellModel.CellType.END;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }

    private BoardModel getCurrentBoard() {
        return boards.get(currentBoardIndex);
    }
}