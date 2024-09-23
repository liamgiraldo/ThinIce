package org.example.models;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NavbarModel extends JPanel {
    private JButton quitButton;
    private JButton restartButton;
    private JButton backButton;

    private Runnable restartAction;

    public NavbarModel(){
        quitButton = new JButton("Quit");
        add(quitButton);

        quitButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setSize(100, 100);
        quitButton.setText("Quit");

        restartButton = new JButton("Restart");
        add(restartButton);
        restartButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartAction.run();
            }
        });
        restartButton.setText("Restart");

        setVisible(true);
    }

    public void setRestartAction(Runnable restartAction) {
        this.restartAction = restartAction;
    }
}
