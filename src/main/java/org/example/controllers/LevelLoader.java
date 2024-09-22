package org.example.controllers;

import org.example.models.BoardModel;
import org.example.models.CellModel;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LevelLoader {
    private ArrayList<BoardModel> boards;
    private Yaml yaml;
    private String levelPath = "/levels/";

    public LevelLoader() {
        boards = new ArrayList<>();
        yaml = new Yaml();
    }

    public ArrayList<BoardModel> loadLevels() {
        String[] levelFiles = {"level1.yml", "level2.yml", "level3.yml"}; // List files explicitly

        for (String fileName : levelFiles) {
            // Load the file as a BoardModel
            try (InputStream inputStream = getClass().getResourceAsStream(levelPath + fileName)) {
                if (inputStream == null) {
                    throw new RuntimeException("Level file not found: " + fileName);
                }

                // Load YAML content into a Map
                Map<String, Object> yamlData = yaml.load(inputStream);

                // Get the author (optional)
                String author = (String) yamlData.get("author");
                System.out.println("Author: " + author);

                // Get the level (which is a 2D List of Integers)
                List<List<Integer>> level = (List<List<Integer>>) yamlData.get("level");

                // Print out the level
                System.out.println("Level Data:");
                for (List<Integer> row : level) {
                    System.out.println(row);
                }

                // Create and add the BoardModel to the list
                BoardModel board = convertToBoardModel(level);
                boards.add(board);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return boards;
    }

    private BoardModel convertToBoardModel(List<List<Integer>> level) {
        int numRows = level.size();
        int numCols = level.get(0).size();
        CellModel[][] cells = new CellModel[numRows][numCols];
        int startingX = 0;
        int startingY = 0;

        for (int y = 0; y < numRows; y++) {
            List<Integer> row = level.get(y);
            for (int x = 0; x < numCols; x++) {
                int cellValue = row.get(x);
                CellModel.CellType cellType;

                switch (cellValue) {
                    case 0:
                        cellType = CellModel.CellType.EMPTY;
                        break;
                    case 1:
                        cellType = CellModel.CellType.ICE;
                        break;
                    case 2:
                        cellType = CellModel.CellType.WALL;
                        break;
                    case 3:
                        cellType = CellModel.CellType.WATER;
                        break;
                    case 4:
                        cellType = CellModel.CellType.END;
                        break;
                    case 5:
                        cellType = CellModel.CellType.START;
                        break;
                    case 6:
                        cellType = CellModel.CellType.ICE;
                        startingX = x;
                        startingY = y;
                        break;
                    default:
                        cellType = CellModel.CellType.EMPTY;
                        break;
                }

                cells[y][x] = new CellModel(cellType, x, y);
            }
        }

        return new BoardModel(cells, startingX, startingY);
    }
}