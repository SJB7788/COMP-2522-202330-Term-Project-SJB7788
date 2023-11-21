package com.example.comp2522202330termprojectsjb7788.Elements;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class Bomb {
    private final static int BOMB_SIZE = 40;
    private final Pane root;
    private final Rectangle player;
    private Rectangle bomb;
    private final int damage;
    private final int explosionRadius;

    public Bomb(Pane root, Rectangle player, int damage, int explosionRadius) {
        this.damage = damage;
        this.explosionRadius = explosionRadius;
        this.root = root;
        this.player = player;
    }

    public void placeBomb() {
        int bombX = Grid.getGridPlacementX((int) player.getX());
        int bombY = Grid.getGridPlacementY((int) player.getY());
        bomb = new Rectangle(bombX, bombY, 40, 40);
        bomb.setFill(Color.RED);
        root.getChildren().add(bomb);
    }

    public ArrayList<Rectangle> getExplosionArea() {
        ArrayList<Rectangle> explosionArea = new ArrayList<>();
        Rectangle initialExplosion = new Rectangle(bomb.getX(), bomb.getY(), BOMB_SIZE, BOMB_SIZE);
        explosionArea.add(initialExplosion);

        // add explosion for the Y axis explosions
        for (int xIndex = 1; xIndex < explosionRadius / 2; xIndex++) {
            // left side of the explosion
            Rectangle explosionXLEFT = new Rectangle(bomb.getX() + (40 * xIndex), bomb.getY(),
                    BOMB_SIZE, BOMB_SIZE);
            // right side of the explosion
            Rectangle explosionXRIGHT = new Rectangle(bomb.getX() - (40 * xIndex), bomb.getY(),
                    BOMB_SIZE, BOMB_SIZE);
            explosionArea.add(explosionXLEFT);
            explosionArea.add(explosionXRIGHT);
        }

        // add explosion for the X axis explosions
        for (int yIndex = 1; yIndex < explosionRadius / 2; yIndex++) {
            // top side of the explosion
            Rectangle explosionYDOWN = new Rectangle(bomb.getX(), bomb.getY() + (40 * yIndex),
                    BOMB_SIZE, BOMB_SIZE);
            // bottom side of the explosion
            Rectangle explosionYUP = new Rectangle(bomb.getX(), bomb.getY() - (40 * yIndex),
                    BOMB_SIZE, BOMB_SIZE);
            explosionArea.add(explosionYDOWN);
            explosionArea.add(explosionYUP);
        }
        return explosionArea;
    }

    public void explode() {
        ArrayList<Rectangle> explosionList = getExplosionArea();
        Rectangle explosion = new Rectangle(bomb.getX(), bomb.getY(), BOMB_SIZE, BOMB_SIZE);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        new EventHandler() {
            int timerCount = 0;
            @Override
            public void handle(Event event) {
                timerCount++;
                if (timerCount == 2) {
                    for (Rectangle element : explosionList) {
                        root.getChildren().add(element);
                    }
                    root.getChildren().remove(bomb);
                    bomb.setY(-50);
                    bomb.setX(-50);
                }
                if (timerCount == 3) {
                    for (Rectangle element : explosionList) {
                        root.getChildren().remove(element);
                        element.setX(-50);
                        element.setY(-50);
                    }
                    timeline.stop();
                }
            }
        }));
        timeline.playFromStart();
    }

    public int getDamage() {
        return damage;
    }

    public int getExplosionRadius() {
        return explosionRadius;
    }
}
