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
    protected ArrayList<BoardModel> boards;
    private PlayerModel player;
    private TextureController textureController;

    private int scoreBeforeLevel = 0;
    private int score = 0;

    /**
     * TODO list:
     * 4. Add push blocks
     * */


    protected int currentBoardIndex;
    protected Timer gameLoop;

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

    public GameController(TextureController textureController) {
        this.textureController = textureController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (boards.isEmpty()) {
            return;
        }


        if(hasPlayerWon()){
            currentBoardIndex++;

            score+= 100;
            scoreBeforeLevel = score;

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

        if(isPlayerSurroundedByWaterOrWalls(player.getRow(), player.getCol())){
            restartLevel();
        }
    }

    public void restartLevel(){
        player.setRow(getCurrentBoard().getStartingY());
        player.setCol(getCurrentBoard().getStartingX());
        getCurrentBoard().reset();
        player.resetKeys();
        score = scoreBeforeLevel;
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
        if(getCurrentBoard().getCell(player.getCol(), player.getRow()).getType() == CellModel.CellType.ICE) {
            getCurrentBoard().getCell(player.getCol(), player.getRow()).setType(CellModel.CellType.WATER);
            score++;
        }
    }

    private void thickIcetoIce(){
        if(getCurrentBoard().getCell(player.getCol(), player.getRow()).getType() == CellModel.CellType.THICKICE) {
            getCurrentBoard().getCell(player.getCol(), player.getRow()).setType(CellModel.CellType.ICE);
            score++;
        }
    }

    private void moneyToIce(){
        if(getCurrentBoard().getCell(player.getCol(), player.getRow()).getType() == CellModel.CellType.MONEY) {
            getCurrentBoard().getCell(player.getCol(), player.getRow()).setType(CellModel.CellType.ICE);
            score+=50;
        }
    }

    private void keyToIce(){
        if(getCurrentBoard().getCell(player.getCol(), player.getRow()).getType() == CellModel.CellType.KEY) {
            getCurrentBoard().getCell(player.getCol(), player.getRow()).setType(CellModel.CellType.ICE);
            //maybe give score im not sure
            player.addKey();
        }
    }

    private void doorToIce(){
        if(getCurrentBoard().getCell(player.getCol(), player.getRow()).getType() == CellModel.CellType.DOOR && player.hasKey()) {
            getCurrentBoard().getCell(player.getCol(), player.getRow()).setType(CellModel.CellType.ICE);
            //maybe give score im not sure
            player.removeKey();
        }
    }

    private void pushIceBlock(int previousRow, int previousCol){
        //previousRow and previousCol are where the player was before they stepped on the push block.
        //we need this info to determine the direction of which to push the block
        if(getCurrentBoard().getCell(player.getCol(), player.getRow()).getType() == CellModel.CellType.PUSHBLOCK){
            CellModel pushblock = getCurrentBoard().getCell(player.getCol(), player.getRow());
            int pushblockRow = player.getRow();
            int pushblockCol = player.getCol();
            int pushblockRowDirection = (pushblockRow - previousRow);
            int pushblockColDirection = (pushblockCol - previousCol);

            //recursively go down that direction until we hit a wall, door, or water
            while(getCurrentBoard().getCell(pushblockCol + pushblockColDirection, pushblockRow + pushblockRowDirection).getType() == CellModel.CellType.ICE){
                pushblockRow += pushblockRowDirection;
                pushblockCol += pushblockColDirection;
            }
            //place the block in the cell before that wall or water
            getCurrentBoard().getCell(pushblockCol, pushblockRow).setType(CellModel.CellType.PUSHBLOCK);
            //set the current cell to ice
            pushblock.setType(CellModel.CellType.ICE);

            //put the player back in the cell they were in before they pushed the block
            player.setRow(previousRow);
            player.setCol(previousCol);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Handle key press events
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (canLegallyMove(player.getRow() - 1, player.getCol())) {
                int previousRow = player.getRow();
                int previousCol = player.getCol();
                iceToWater();
                thickIcetoIce();
                player.moveUp();
                moneyToIce();
                keyToIce();
                doorToIce();
                pushIceBlock(previousRow, previousCol);
            }
//            player.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (canLegallyMove(player.getRow() + 1, player.getCol())) {
                int previousRow = player.getRow();
                int previousCol = player.getCol();
                iceToWater();
                thickIcetoIce();
                player.moveDown();
                moneyToIce();
                keyToIce();
                doorToIce();
                pushIceBlock(previousRow, previousCol);
            }
//            player.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(canLegallyMove(player.getRow(), player.getCol() - 1)) {
                int previousRow = player.getRow();
                int previousCol = player.getCol();
                iceToWater();
                thickIcetoIce();
                player.moveLeft();
                moneyToIce();
                keyToIce();
                doorToIce();
                pushIceBlock(previousRow, previousCol);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(canLegallyMove(player.getRow(), player.getCol() + 1)) {
                int previousRow = player.getRow();
                int previousCol = player.getCol();
                iceToWater();
                thickIcetoIce();
                player.moveRight();
                moneyToIce();
                keyToIce();
                doorToIce();
                pushIceBlock(previousRow, previousCol);
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
        //if the cell we are moving into is a door, and we do not have a key, we can't move into it
        if(getCurrentBoard().getCell(col, row).getType() == CellModel.CellType.DOOR && !player.hasKey()) {
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

    private boolean isPlayerSurroundedByWaterOrWalls(int row, int col){
        //if the player is surrounded by water or walls on all sides, return true
        return (getCurrentBoard().getCell(col, row - 1).getType() == CellModel.CellType.WATER ||
                getCurrentBoard().getCell(col, row - 1).getType() == CellModel.CellType.WALL) &&
                (getCurrentBoard().getCell(col - 1, row).getType() == CellModel.CellType.WATER ||
                getCurrentBoard().getCell(col - 1, row).getType() == CellModel.CellType.WALL) &&
                (getCurrentBoard().getCell(col + 1, row).getType() == CellModel.CellType.WATER ||
                getCurrentBoard().getCell(col + 1, row).getType() == CellModel.CellType.WALL) &&
                (getCurrentBoard().getCell(col, row + 1).getType() == CellModel.CellType.WATER ||
                getCurrentBoard().getCell(col, row + 1).getType() == CellModel.CellType.WALL);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }

    protected BoardModel getCurrentBoard() {
        return boards.get(currentBoardIndex);
    }

    public void resizeTiles(int newTileSize) {
        for (BoardModel board : boards) {
            board.resizeTiles(newTileSize);
        }
        // Resize player
        player.resize(newTileSize);
    }

    public void setOffset(int xOffset, int yOffset) {
        player.setOffset(xOffset, yOffset);
        // Offset the board
        getCurrentBoard().setOffset(xOffset, yOffset);
    }
}