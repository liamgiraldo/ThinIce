package org.example.models;

import org.example.controllers.TextureController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class NavbarModel extends JPanel {
    private ArrayList<JButton> mainMenuButtons;
    private ArrayList<JButton> gameButtons;
    private ArrayList<JButton> levelEditorButtons;

    private TexturedButton xButton;
    private TexturedButton fileButton;
    private TexturedButton backArrowButton;
    private TexturedButton frontArrowButton;
    private TexturedButton eraserButton;
    private TexturedButton checkButton;
    private TexturedButton skullButton;
    private TexturedButton emptyButton;
    private TexturedButton puffleButton;
    private TexturedButton flagButton;
    private TexturedButton iceButton;
    private TexturedButton wallButton;
    private TexturedButton thickIceButton;
    private TexturedButton pushBlockButton;
    private TexturedButton moneyButton;
    private TexturedButton doorButton;
    private TexturedButton keyButton;
    private TexturedButton endButton;
    private TexturedButton saveButton;

    private Runnable restartAction;

    private int buttonSize = 20;
    private int rows = 2;
    private int columns = 10;

    private int xOffset = 0;
    private int yOffset = 0;

    private Timer repaintTimer;

    private TextureController textureController;

    public NavbarModel(TextureController textureController) {
        this.textureController = textureController;

        mainMenuButtons = new ArrayList<>();
        gameButtons = new ArrayList<>();
        levelEditorButtons = new ArrayList<>();

        xButton = new TexturedButton(textureController.getXButton());
        fileButton = new TexturedButton(textureController.getLoadButton());
        backArrowButton = new TexturedButton(textureController.getBackButton());
        frontArrowButton = new TexturedButton(textureController.getFrontButton());
        eraserButton = new TexturedButton(textureController.getEraserButton());
        checkButton = new TexturedButton(textureController.getCheckButton());
        skullButton = new TexturedButton(textureController.getSkullButton());
        emptyButton = new TexturedButton(textureController.getEmptyButton());
        puffleButton = new TexturedButton(textureController.getPuffleButton());
        flagButton = new TexturedButton(textureController.getFlagButton());
        iceButton = new TexturedButton(textureController.getIceButton());
        wallButton = new TexturedButton(textureController.getWallButton());
        thickIceButton = new TexturedButton(textureController.getThickIceButton());
        pushBlockButton = new TexturedButton(textureController.getPushBlockButton());
        moneyButton = new TexturedButton(textureController.getMoneyButton());
        doorButton = new TexturedButton(textureController.getDoorButton());
        keyButton = new TexturedButton(textureController.getKeyButton());
        endButton = new TexturedButton(textureController.getEndButton());
        saveButton = new TexturedButton(textureController.getSaveButton());

        addButton(xButton, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        addButton(skullButton, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartAction.run();
            }
        });

        addButton(fileButton, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Load a level
            }
        });

        addButton(backArrowButton, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back a level
            }
        });

        addButton(frontArrowButton, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go forward a level
            }
        });

        addButton(eraserButton, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Erase a cell
            }
        });

        addButton(checkButton, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the level is valid
            }
        });

        addButton(emptyButton, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the cell to empty
            }
        });

        addButton(puffleButton, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the cell to puffle
            }
        });

        addButton(flagButton, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the cell to flag
            }
        });

        addButton(iceButton, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the cell to ice
            }
        });

        addButton(wallButton, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the cell to wall
            }
        });

        addButton(thickIceButton, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the cell to thick ice
            }
        });

        addButton(pushBlockButton, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the cell to push block
            }
        });

        addButton(moneyButton, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the cell to money
            }
        });

        addButton(doorButton, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the cell to door
            }
        });

        addButton(keyButton, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the cell to key
            }
        });

        addButton(endButton, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the cell to end
            }
        });

        addButton(saveButton, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save the level
            }
        });


        GridLayout layout = new GridLayout(rows, columns);
        setPreferredSize(new Dimension(columns * buttonSize, rows * buttonSize));
//        layout.setHgap(-10);
//        layout.setVgap(-10);
        setLayout(layout);

        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setBackground(new Color(178, 218, 255));

        setVisible(true);
        repaintTimer = new Timer(16, e -> repaint());
        repaintTimer.start();

    }

    private void addButton(TexturedButton button, AbstractAction action) {
        button.setAction(action);
//        button.setBorder(null);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        add(button);
        mainMenuButtons.add(button);
    }

    public void setRestartAction(Runnable restartAction) {
        this.restartAction = restartAction;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}