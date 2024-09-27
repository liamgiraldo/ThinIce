package org.example.controllers;

import org.example.models.BoardModel;
import org.example.models.CellModel;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LevelEditorController extends GameController{

    private Yaml yaml;

    public LevelEditorController(TextureController textureController) {
        super(textureController);
        //default board is a blank board
        this.boards = new ArrayList<>();
        this.boards.add(new BoardModel());
        this.currentBoardIndex = 0;

        yaml = new Yaml();
    }
    private void saveLevel() {
        // Save the current board to a file
    }

    private void loadLevel() {

    }

}
