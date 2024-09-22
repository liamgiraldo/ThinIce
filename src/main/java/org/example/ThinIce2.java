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
        setBackground(Color.BLACK);

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameController.draw(g);
    }
}