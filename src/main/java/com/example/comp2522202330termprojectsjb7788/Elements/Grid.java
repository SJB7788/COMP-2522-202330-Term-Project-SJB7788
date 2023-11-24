package com.example.comp2522202330termprojectsjb7788.Elements;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Grid {
    public static int GRID_WIDTH = 40;
    public static int GRID_HEIGHT = 40;
    public static final ArrayList<Rectangle> snowBlocks = new ArrayList<>();

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

    public void setSnowblocks(Pane pane) {
        Snowblock snowblock = new Snowblock(1, GRID_WIDTH, 200, 200, null);
        snowBlocks.add(snowblock.getBlock());
        pane.getChildren().add(snowblock.getBlock());
    }



    public static int getGridWidth() {
        return GRID_WIDTH;
    }

    public static int getGridHeight() {
        return GRID_HEIGHT;
    }
}
