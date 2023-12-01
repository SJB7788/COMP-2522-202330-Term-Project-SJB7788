package com.example.comp2522202330termprojectsjb7788.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
    private final int[][] map;
    private final int HEIGHT = 18;
    private final int WIDTH = 32;
    private final File mapFile;
    private final Scanner scanner;

    public Map(String fileName) {
        this.map = new int[HEIGHT][WIDTH];
        try {
            this.mapFile = new File(fileName);
            this.scanner = new Scanner(mapFile);

            for (int i = 0; i < 18; i++) {
                for (int j = 0; j < 32; j++) {
                    map[i][j] = scanner.nextInt();
                }
            }
        }
        catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        Grid.maps.add(this);
    }

    public void printMap() {
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 32; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void loadMap() {
        System.out.println(27 * 18);
        for (int currHeight = 0; currHeight < HEIGHT; currHeight++) {
            for (int currWidth = 0; currWidth < WIDTH; currWidth++) {
                if (map[currHeight][currWidth] == 4) {
                    Grid.setEnemyBlocks(currWidth * Grid.GRID_WIDTH, currHeight * Grid.GRID_HEIGHT);
                }
                else if (map[currHeight][currWidth] == 3) {
                    Grid.setFinishBlocks(currWidth * Grid.GRID_WIDTH, currHeight * Grid.GRID_HEIGHT);
                    Grid.blocks.add(Grid.finishBlocks.get(Grid.finishBlocks.size() - 1));
                }
                else if (map[currHeight][currWidth] == 2) {
                    Grid.setSnowBlocks(currWidth * Grid.GRID_WIDTH, currHeight * Grid.GRID_HEIGHT);
                    Grid.blocks.add(Grid.snowBlocks.get(Grid.snowBlocks.size() - 1));
                }
                else if (map[currHeight][currWidth] == 1) {
                    Grid.setStoneBlocks(currWidth * Grid.GRID_WIDTH, currHeight * Grid.GRID_HEIGHT);
                    Grid.blocks.add(Grid.stoneBlocks.get(Grid.stoneBlocks.size() - 1));
                }
            }
        }
    }

    public int[][] getMap() {
        return map;
    }

}
