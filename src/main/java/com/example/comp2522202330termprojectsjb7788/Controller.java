package com.example.comp2522202330termprojectsjb7788;

import com.example.comp2522202330termprojectsjb7788.Elements.Grid;
import com.example.comp2522202330termprojectsjb7788.enums.Directions;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.shape.Rectangle;

public class Controller {
    private final Rectangle player;
    private final int SPEED = 10;
    final double minX = 0;
    final double maxX = 800;
    final DoubleProperty rectangleVelocity = new SimpleDoubleProperty();
    final LongProperty lastUpdateTime = new SimpleLongProperty();

    public Controller(Rectangle player) {
        this.player = player;
    }


    public void moveUp() {
        player.setY(player.getY() - SPEED);
        detectCollision(40, -40, 0, -40, Directions.UP);
    }

    public void moveDown() {
        player.setY(player.getY() + SPEED);
        detectCollision(40, -40, 40, 0, Directions.DOWN);
    }

    public void moveLeft() {
        player.setX(player.getX() - SPEED);
        detectCollision(0, -40, 40, -40, Directions.LEFT);
    }

    public void moveRight() {
        player.setX(player.getX() + SPEED);
        detectCollision(40, 0, 40, -40, Directions.RIGHT);
    }

    public void detectCollision(int x1, int x2, int y1, int y2, Directions direction) {
        for (Rectangle snowBlock : Grid.snowBlocks) {
            // left collision x1 = 40, x2 = 0, y1 = 40, y2 = -40
            // right collision x1 = 0, x2 = -40, y1 = 40, y2 = -40
            // top collision x1 = 40, x2 = -40, y1 = 40, y2 = 0
            // bottom collision x1 = 40, x2 = -40, y1 = 0, y2 = -40
            if (snowBlock.getX() - player.getX() < x1 && snowBlock.getX() - player.getX() > x2
                    && snowBlock.getY() - player.getY() < y1 && snowBlock.getY() - player.getY() > y2) {
                switch (direction) {
                    case UP -> player.setY(player.getY() + SPEED);
                    case DOWN -> player.setY(player.getY() - SPEED);
                    case LEFT -> player.setX(player.getX() + SPEED);
                    case RIGHT -> player.setX(player.getX() - SPEED);
                }
                return;
            }
        }
    }
}
