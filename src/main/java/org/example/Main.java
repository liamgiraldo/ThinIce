package org.example;

import org.example.ThinIce2;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                int tileSize = 50;
                int columns = 19;
                int rows = 15;
                int width = columns * tileSize;
                int height = rows * tileSize;

                JFrame frame = new JFrame("Thin Ice");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(width, height);
                frame.setResizable(false);

                ThinIce2 game = new ThinIce2();
                frame.add(game);
                frame.pack();
                game.requestFocus();
                frame.setVisible(true);
            }
        });
    }
}