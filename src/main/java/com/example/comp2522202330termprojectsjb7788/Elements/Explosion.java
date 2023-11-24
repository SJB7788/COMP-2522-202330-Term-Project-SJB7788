package com.example.comp2522202330termprojectsjb7788.Elements;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Explosion {
    private final int explosionRadius;
    private final int size;
    private final int xAxis;
    private final int yAxis;
    private final ArrayList<Rectangle> explosionArray;

    public Explosion(int explosionRadius, int size, int xAxis, int yAxis) {
        this.explosionRadius = explosionRadius;
        this.size = size;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.explosionArray = new ArrayList<>();
    }

    public void makeExplosionArea() {
        Rectangle initialExplosion = new Rectangle(xAxis, yAxis, size, size);
        explosionArray.add(initialExplosion);
        for (int xIndex = 1; xIndex < explosionRadius / 2; xIndex++) {
            // left side of the explosion
            Rectangle explosionXLEFT = new Rectangle(xAxis + (40 * xIndex), yAxis,
                    size, size);
            // right side of the explosion
            Rectangle explosionXRIGHT = new Rectangle(xAxis - (40 * xIndex), yAxis,
                    size, size);
            explosionArray.add(explosionXLEFT);
            explosionArray.add(explosionXRIGHT);
        }

        // add explosion for the X axis explosions
        for (int yIndex = 1; yIndex < explosionRadius / 2; yIndex++) {
            // top side of the explosion
            Rectangle explosionYDOWN = new Rectangle(xAxis, yAxis + (40 * yIndex),
                    size, size);
            // bottom side of the explosion
            Rectangle explosionYUP = new Rectangle(xAxis, yAxis - (40 * yIndex),
                    size, size);
            explosionArray.add(explosionYDOWN);
            explosionArray.add(explosionYUP);
        }
    }

    public ArrayList<Rectangle> getExplosionArray() {
        return explosionArray;
    }

    public void detectCollision() {
    }
}
