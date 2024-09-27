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
    private Image keyImage;
    private Image doorImage;
    private Image moneyImage;
    private Image pushBlockImage;
    private Image thickIceImage;

    private Image xbutton;
    private Image savebutton;
    private Image loadbutton;
    private Image backbutton;
    private Image frontbutton;
    private Image eraserbutton;
    private Image checkbutton;
    private Image skullbutton;
    private Image emptybutton;
    private Image pufflebutton;
    private Image flagbutton;
    private Image icebutton;
    private Image wallbutton;
    private Image thickicebutton;
    private Image pushblockbutton;
    private Image moneybutton;
    private Image doorbutton;
    private Image keybutton;
    private Image endbutton;

    public TextureController() {
        playerImage = loadImage("puffle.png");
        wallImage = loadImage("wall.png");
        iceImage = loadImage("ice.png");
        waterImage = loadImage("water.png");
        emptyImage = loadImage("empty.png");
        endImage = loadImage("end.png");
        keyImage = loadImage("key.png");
        doorImage = loadImage("keyhole.png");
        moneyImage = loadImage("money.png");
        pushBlockImage = loadImage("pushblock.png");
        thickIceImage = loadImage("thickice.png");

        xbutton = loadImage("xbutton.png");
        savebutton = loadImage("savebutton.png");
        loadbutton = loadImage("folderbutton.png");
        backbutton = loadImage("backarrowbutton.png");
        frontbutton = loadImage("frontarrowbutton.png");
        eraserbutton = loadImage("eraserbutton.png");
        checkbutton = loadImage("greencheckbutton.png");
        skullbutton = loadImage("skullbutton.png");
        emptybutton = loadImage("keycap.png");
        pufflebutton = loadImage("pufflebutton.png");
        flagbutton = loadImage("flagbutton.png");
        icebutton = loadImage("icebutton.png");
        wallbutton = loadImage("wallbutton.png");
        thickicebutton = loadImage("thickicebutton.png");
        pushblockbutton = loadImage("pushblockbutton.png");
        moneybutton = loadImage("moneybutton.png");
        doorbutton = loadImage("keyholebutton.png");
        keybutton = loadImage("keybutton.png");
        endbutton = loadImage("endbutton.png");

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


    public Image getKeyImage() {
        return keyImage;
    }

    public Image getDoorImage() {
        return doorImage;
    }

    public Image getMoneyImage() {
        return moneyImage;
    }

    public Image getPushBlockImage() {
        return pushBlockImage;
    }

    public Image getThickIceImage() {
        return thickIceImage;
    }

    public Image getXButton() {
        return xbutton;
    }

    public Image getSaveButton() {
        return savebutton;
    }

    public Image getLoadButton() {
        return loadbutton;
    }

    public Image getBackButton() {
        return backbutton;
    }

    public Image getFrontButton() {
        return frontbutton;
    }

    public Image getEraserButton() {
        return eraserbutton;
    }

    public Image getCheckButton() {
        return checkbutton;
    }

    public Image getSkullButton() {
        return skullbutton;
    }

    public Image getEmptyButton() {
        return emptybutton;
    }

    public Image getPuffleButton() {
        return pufflebutton;
    }

    public Image getFlagButton() {
        return flagbutton;
    }

    public Image getIceButton() {
        return icebutton;
    }

    public Image getWallButton() {
        return wallbutton;
    }

    public Image getThickIceButton() {
        return thickicebutton;
    }

    public Image getPushBlockButton() {
        return pushblockbutton;
    }

    public Image getMoneyButton() {
        return moneybutton;
    }

    public Image getDoorButton() {
        return doorbutton;
    }

    public Image getKeyButton() {
        return keybutton;
    }

    public Image getEndButton() {
        return endbutton;
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
            case KEY:
                return keyImage;
            case DOOR:
                return doorImage;
            case MONEY:
                return moneyImage;
            case PUSHBLOCK:
                return pushBlockImage;
            case THICKICE:
                return thickIceImage;
            default:
                return null;
        }
    }
}
