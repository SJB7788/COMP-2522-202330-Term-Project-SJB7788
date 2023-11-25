package com.example.comp2522202330termprojectsjb7788.Elements;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Grid {
    public static int GRID_WIDTH = 40;
    public static int GRID_HEIGHT = 40;
    public static final ArrayList<Snowblock> snowBlocks = new ArrayList<>();

    public Grid() {
    }

    public static int getGridPlacementX(int x) {
        if (x + GRID_WIDTH >= 1280) {
            return 1240;
        }
        int remainderX = x % GRID_WIDTH;
        if (remainderX <= 20) {
            return x - remainderX; // return the lower bound of the grid
        }
        return x + (GRID_WIDTH - remainderX); // return the higher bound of the grid
    }

    public static int getGridPlacementY(int y) {
        if (y + GRID_HEIGHT >= 720) {
            return 680;
        }
        int remainderY = y % GRID_HEIGHT;
        if (remainderY <= 20) {
            return y - remainderY; // return the lower bound of the grid
        }
        return y + (GRID_HEIGHT - remainderY); // return the higher bound of the grid
    }

    public void setSnowblocks() {
        Random random = new Random();

        for (int xAxis = 0; xAxis < 10; xAxis++) {
            // randomly place items
            int randomNumber = random.nextInt(1) + 1;

            Snowblock snowblock = new Snowblock(1, GRID_WIDTH, 200 + (GRID_WIDTH * xAxis), 200);
            // 25% chance of item in a block
            if (randomNumber == 1) {
                // will make a list of items instead of just berries
                // berries will most likely be in another method where it will randomly choose blocks with no items
                // and place them there instead of this like to make sure that the amount of berries in the game
                // is the amount needed to win
                Berry berry = new Berry(1, snowblock.getxAxis() + ((GRID_WIDTH - 30) / 2),
                        snowblock.getyAxis() + ((GRID_WIDTH - 30) / 2), 30);
                snowblock.addItem(berry);
                System.out.println("added item");
            }
            snowBlocks.add(snowblock);
        }
    }

    public void placeSnowBlocks(Pane pane) {
        for (Snowblock snowblock: snowBlocks) {
            pane.getChildren().add(snowblock.getBlock());
        }
    }


    public static int getGridWidth() {
        return GRID_WIDTH;
    }

    public static int getGridHeight() {
        return GRID_HEIGHT;
    }
}
