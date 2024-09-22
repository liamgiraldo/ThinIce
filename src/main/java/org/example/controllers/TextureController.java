package org.example.controllers;

import org.example.models.CellModel;

import javax.swing.*;
import java.awt.*;

public class TextureController {
    private Image playerImage;
    private Image wallImage;
    private Image iceImage;
    private Image waterImage;
    private Image emptyImage;
    private Image endImage;

    public TextureController() {
        playerImage = loadImage("puffle.png");
        wallImage = loadImage("wall.png");
        iceImage = loadImage("ice.png");
        waterImage = loadImage("water.png");
        emptyImage = loadImage("empty.png");
        endImage = loadImage("end.png");
    }

    private Image loadImage(String path) {
        Image image = new ImageIcon(getClass().getResource("/images/" + path)).getImage();
        if (image.getWidth(null) == -1 || image.getHeight(null) == -1) {
            System.out.println("Failed to load image: " + path);
        } else {
            System.out.println("Successfully loaded image: " + path);
        }
        return image;
    }

    public Image getPlayerImage() {
        return playerImage;
    }

    public Image getWallImage() {
        return wallImage;
    }

    public Image getIceImage() {
        return iceImage;
    }

    public Image getWaterImage() {
        return waterImage;
    }

    public Image getEmptyImage() {
        return emptyImage;
    }

    public Image getEndImage() {
        return endImage;
    }

    public Image getImageWithCellType(CellModel.CellType type) {
        switch (type) {
            case WALL:
                return wallImage;
            case ICE:
                return iceImage;
            case WATER:
                return waterImage;
            case EMPTY:
                return emptyImage;
            case END:
                return endImage;
            default:
                return null;
        }
    }
}
