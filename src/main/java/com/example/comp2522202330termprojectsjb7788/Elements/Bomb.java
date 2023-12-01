package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.interfaces.Block;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Bomb implements Block {
    private final static int BOMB_SIZE = 40;
    private final Pane root;
    private final Player player;
    private Rectangle bomb;
    private final int damage;
    private final int explosionRadius;

    public Bomb(Pane root, Player player, int damage, int explosionRadius) {
        this.damage = damage;
        this.explosionRadius = explosionRadius;
        this.root = root;
        this.player = player;
    }

    public void placeBomb() {
        int bombX = Grid.getGridPlacementX((int) player.getPlayer().getX());
        int bombY = Grid.getGridPlacementY((int) player.getPlayer().getY());
        bomb = new Rectangle(bombX, bombY, 40, 40);
        bomb.setFill(Color.RED);
        root.getChildren().add(bomb);
        Grid.blocks.add(this);
    }

    public void explode() {
        // make explosion
        Explosion explosion = new Explosion(damage / 2, BOMB_SIZE, (int) bomb.getX(), (int) bomb.getY());
        explosion.makeExplosionArea();

        // get the explosion group
        Group explosionGroup = explosion.getExplosionGroup();

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
                    // make explosion visible and remove bomb when 2 seconds has passed
                    root.getChildren().add(explosionGroup);
                    // check for collision with snowblocks
                    explosion.checkCollisionBlock(Grid.snowBlocks);
                    explosion.checkCollisionPlayer(player);
                    root.getChildren().remove(bomb);
                    Grid.blocks.remove(Bomb.this);
                    bomb.setY(-50);
                    bomb.setX(-50);
                }
                if (timerCount == 3) {
                    // remove explosion when 3 seconds has passed
                    root.getChildren().remove(explosionGroup);
                    explosionGroup.setTranslateX(-200);
                    explosionGroup.setTranslateY(-200);
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

    @Override
    public Rectangle getBlock() {
        return bomb;
    }

    @Override
    public String getBlockType() {
        return "Bomb";
    }
}
