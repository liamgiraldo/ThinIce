package org.example.models;

import javax.swing.*;
import java.awt.*;

public class TexturedButton extends JButton {
    private Image image;
    private ImageIcon icon;
    private ImageIcon pressedIcon;
    private ImageIcon rolloverIcon;

    public TexturedButton(String path) {
        image = new ImageIcon(getClass().getResource("/images/" + path)).getImage();
        icon = new ImageIcon(getClass().getResource("/images/" + path));
        setIcon(icon);
    }

    public TexturedButton(Image image) {
        this.image = image;
        icon = new ImageIcon(image);
        //the pressed icon is just the original icon tinted slightly darker

        //setIcon in the constructor for some reason doesn't work
        super.setIcon(icon);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        setIcon(icon);
    }

    public void resize(int width, int height) {
        image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        setIcon(icon);
    }
}
