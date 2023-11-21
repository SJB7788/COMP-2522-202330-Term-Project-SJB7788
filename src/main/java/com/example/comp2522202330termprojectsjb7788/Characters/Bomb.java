package com.example.comp2522202330termprojectsjb7788.Characters;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class Bomb {
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
        System.out.println(bombX);
        System.out.println(bombY);
        bomb = new Rectangle(bombX, bombY, 40, 40);
        bomb.setFill(Color.RED);
        root.getChildren().add(bomb);
    }

    public void explode() {
        Rectangle explosion = new Rectangle(bomb.getX(), bomb.getY(), explosionRadius, explosionRadius);
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
                    root.getChildren().add(explosion);
                    root.getChildren().remove(bomb);
                    bomb.setY(-50);
                    bomb.setX(-50);
                }
                if (timerCount == 3) {
                    root.getChildren().remove(explosion);
                    explosion.setY(-50);
                    explosion.setX(-50);
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
