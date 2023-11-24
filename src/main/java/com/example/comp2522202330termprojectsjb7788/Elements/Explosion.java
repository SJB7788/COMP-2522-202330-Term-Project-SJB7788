package com.example.comp2522202330termprojectsjb7788.Elements;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Explosion {
    private final int explosionRadius;
    private final int size;
    private final int xAxis;
    private final int yAxis;
    private final Group explosionGroup;

    public Explosion(int explosionRadius, int size, int xAxis, int yAxis) {
        this.explosionRadius = explosionRadius;
        this.size = size;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        explosionGroup = new Group();
    }

    public void makeExplosionArea() {
        Rectangle initialExplosion = new Rectangle(xAxis, yAxis, size, size);
        explosionGroup.getChildren().add(initialExplosion);
        for (int xIndex = 1; xIndex < explosionRadius / 2; xIndex++) {
            // left side of the explosion
            Rectangle explosionXLEFT = new Rectangle(xAxis + (40 * xIndex), yAxis,
                    size, size);
            // right side of the explosion
            Rectangle explosionXRIGHT = new Rectangle(xAxis - (40 * xIndex), yAxis,
                    size, size);
            explosionGroup.getChildren().addAll(explosionXLEFT, explosionXRIGHT);
        }

        // add explosion for the X axis explosions
        for (int yIndex = 1; yIndex < explosionRadius / 2; yIndex++) {
            // top side of the explosion
            Rectangle explosionYDOWN = new Rectangle(xAxis, yAxis + (40 * yIndex),
                    size, size);
            // bottom side of the explosion
            Rectangle explosionYUP = new Rectangle(xAxis, yAxis - (40 * yIndex),
                    size, size);
            explosionGroup.getChildren().addAll(explosionYDOWN, explosionYUP);
        }
    }

    public void checkCollision(ArrayList<Rectangle> snowBlocks) {
        for (Rectangle snowBlock : snowBlocks) {
            if (explosionGroup.getBoundsInParent().intersects(snowBlock.getBoundsInParent())) {
                System.out.println("Collision detected");
                snowBlock.setY(-50);
                snowBlock.setX(-50);
            }
        }
    }

    public Group getExplosionGroup() {
        return explosionGroup;
    }

    public void detectCollision() {
    }
}
