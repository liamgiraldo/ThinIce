package org.example;

import org.example.models.NavbarModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
                frame.setResizable(true);

                ThinIce2 game = new ThinIce2();
                frame.setLocationRelativeTo(null);


                frame.add(game);
                frame.pack();
                game.requestFocus();

                NavbarModel navbar = new NavbarModel(game.getTextureController());
                frame.add(navbar, BorderLayout.NORTH);

                navbar.setRestartAction(()->{
                    game.getGameController().restartLevel();
                    game.requestFocus();
                });


                frame.setVisible(true);
                // Set minimum size to prevent collapsing
//                frame.setMinimumSize(new Dimension(400, 400));

                frame.addComponentListener(new ComponentAdapter() {
                    public void componentResized(ComponentEvent componentEvent) {
                        Dimension newSize = frame.getContentPane().getSize();
                        int newWidth = newSize.width;
                        int newHeight = newSize.height;
//                        frame.setSize(new Dimension(newWidth, newHeight));
                        game.resizeTiles(newWidth, newHeight);
                    }
                });
            }
        });
    }
}