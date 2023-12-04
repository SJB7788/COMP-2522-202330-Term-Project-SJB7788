package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.enums.Directions;
import com.example.comp2522202330termprojectsjb7788.interfaces.Block;
import com.example.comp2522202330termprojectsjb7788.interfaces.Item;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Grid {
    public static int GRID_WIDTH = 40;
    public static int GRID_HEIGHT = 40;
    public static final ArrayList<Block> blocks = new ArrayList<>();
    public static final ArrayList<Snowblock> snowBlocks = new ArrayList<>();
    public static final ArrayList<Stoneblock> stoneBlocks = new ArrayList<>();
    public static final ArrayList<Finishblock> finishBlocks = new ArrayList<>();
    public static final ArrayList<BerryBlocks> berryBlocks = new ArrayList<>();
    public static final ArrayList<Enemy> enemies = new ArrayList<>();
    public static final ArrayList<Map> maps = new ArrayList<>();
    public static StartingBlock startingBlock;

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

    public static void setSnowBlocks(int xAxis, int yAxis) {
        Snowblock snowblock = new Snowblock(1, GRID_WIDTH, xAxis, yAxis);

        // randomly place items
        Random random = new Random();
        int randomNumber = random.nextInt(4) + 1;

        // 25% chance of item in a block
        if (randomNumber == 1) {
            Item item = random.nextInt(2) + 1 == 1 ? new DamageItem(1, snowblock.getXAxis() + ((GRID_WIDTH - 30) / 2),
                    snowblock.getYAxis() + ((GRID_WIDTH - 30) / 2), 30) : new SpeedItem(1, snowblock.getXAxis() + ((GRID_WIDTH - 30) / 2),
                    snowblock.getYAxis() + ((GRID_WIDTH - 30) / 2), 30);
            snowblock.addItem(item);
        }
        snowBlocks.add(snowblock);
    }

    public void placeSnowBlocks(Pane pane) {
        for (Snowblock snowblock : snowBlocks) {
            pane.getChildren().add(snowblock.getBlock());
        }
    }

    public static void setStoneBlocks(int xAxis, int yAxis) {
        Stoneblock stoneblock = new Stoneblock(GRID_WIDTH, xAxis, yAxis);
        stoneBlocks.add(stoneblock);
    }

    public void placeStoneBlocks(Pane pane) {
        for (Stoneblock stoneblock : stoneBlocks) {
            pane.getChildren().add(stoneblock.getBlock());
        }
    }

    public static void setFinishBlocks(int xAxis, int yAxis) {
        Finishblock finishblock = new Finishblock(GRID_WIDTH, xAxis, yAxis);
        finishBlocks.add(finishblock);
    }

    public void placeFinishBlocks(Pane pane) {
        for (Finishblock finishblock : finishBlocks) {
            pane.getChildren().add(finishblock.getBlock());
        }
    }

    public static void setEnemyBlocks(int xAxis, int yAxis) {
        Enemy enemy = new Enemy(5 , 10, xAxis, yAxis);
        enemies.add(enemy);
    }

    public void placeEnemyBlocks(Pane pane) {
        Random random = new Random();
        for (Enemy enemy : enemies) {
            Directions enemyDirection = (random.nextInt(2) + 1 == 1) ? Directions.RIGHT : Directions.LEFT;
            pane.getChildren().add(enemy.getBody());
            enemy.move(enemyDirection);
        }
    }

    public static void setStartingBlock(int xAxis, int yAxis) {
        startingBlock = new StartingBlock(xAxis, yAxis);
    }

    public void placeStartingBlock(Pane pane) {
        if (startingBlock == null) {
            throw new NullPointerException("Starting block is null");
        }
        pane.getChildren().add(startingBlock.getBlock());
    }

    public static void setBerryBlocks(int xAxis, int yAxis) {
        BerryBlocks berryBlock = new BerryBlocks(xAxis, yAxis);
        berryBlocks.add(berryBlock);
    }

    public void placeBerryBlocks(Pane pane) {
        for (BerryBlocks berryBlock : berryBlocks) {
            pane.getChildren().add(berryBlock.getBlock());
        }
    }

    public static int getGridWidth() {
        return GRID_WIDTH;
    }

    public static int getGridHeight() {
        return GRID_HEIGHT;
    }
}
