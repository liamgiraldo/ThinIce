package org.example;

import org.example.controllers.GameController;
import org.example.controllers.LevelLoader;
import org.example.controllers.TextureController;
import org.example.models.BoardModel;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class ThinIce2 extends JPanel {
    private GameController gameController;
    private Timer repaintTimer;
    private LevelLoader levelLoader;
    private TextureController textureController;

    private ArrayList<BoardModel> boards;

    private int tileSize = 50;
    private int columns = 19;
    private int rows = 15;
    private int width = columns * tileSize;
    private int height = rows * tileSize;

    public ThinIce2() {
        start();
    }

    public void start() {
        levelLoader = new LevelLoader();
        boards = levelLoader.loadLevels();
        this.textureController = new TextureController();

        setPreferredSize(new Dimension(width, height));
        Color color = new Color(178, 218, 255);
        setBackground(color);

        gameController = new GameController(boards, textureController);

        // Add the GameController as a KeyListener
        setFocusable(true);
        addKeyListener(gameController);

        // Initialize the Timer to repaint the panel every 16ms (~60 FPS)
        repaintTimer = new Timer(16, e -> repaint());
        repaintTimer.start();
    }

    public void stop() {
        if (gameController != null) {
            gameController.stop();
        }
        if (repaintTimer != null) {
            repaintTimer.stop();
        }
    }

    public void resizeTiles(int newPanelWidth, int newPanelHeight) {
        int newTileSize = Math.min(newPanelWidth / columns, newPanelHeight / rows);
        gameController.resizeTiles(newTileSize);

        //We also want to make sure that the game is centered, so we might need to offset
        int newWidth = columns * newTileSize;
        int newHeight = rows * newTileSize;
        int xOffset = (newPanelWidth - newWidth)/2;
        int yOffset = (newPanelHeight - newHeight)/2;
        gameController.setOffset(xOffset, yOffset);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameController.draw(g);
    }

    public GameController getGameController() {
        return gameController;
    }
    public TextureController getTextureController() {
        return textureController;
    }
}