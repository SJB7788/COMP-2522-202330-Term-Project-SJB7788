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

    public void explode() {
        Explosion explosion = new Explosion(damage / 2, BOMB_SIZE, (int) bomb.getX(), (int) bomb.getY());
        explosion.makeExplosionArea();
        ArrayList<Rectangle> explosionArray = explosion.getExplosionArray();

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
                    for (Rectangle element : explosionArray) {
                        root.getChildren().add(element);
                    }
                    root.getChildren().remove(bomb);
                    bomb.setY(-50);
                    bomb.setX(-50);
                }
                if (timerCount == 3) {
                    for (Rectangle element : explosionArray) {
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
