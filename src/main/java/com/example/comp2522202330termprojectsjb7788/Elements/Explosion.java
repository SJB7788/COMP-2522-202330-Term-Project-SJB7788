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
    private final int damage;

    public Explosion(int explosionRadius, int bombSize, int xAxis, int yAxis) {
        this.explosionRadius = explosionRadius;
        this.size = bombSize - 10; // make explosion smaller than bomb
        this.xAxis = (xAxis + bombSize / 2) - size / 2;
        this.yAxis = (yAxis + bombSize / 2) - size / 2;
        this.explosionGroup = new Group();
        this.damage = explosionRadius * 2;
    }

    public void makeExplosionArea() {
        Rectangle initialExplosion = new Rectangle(xAxis, yAxis, size, size);
        explosionGroup.getChildren().add(initialExplosion);

        // left side of the explosion
        Rectangle explosionXRIGHT = new Rectangle(xAxis + size, yAxis,
                ((size + 10) * ((double) explosionRadius)), size);

        // right side of the explosion
        Rectangle explosionXLEFT = new Rectangle(xAxis - ((size + 10) * ((double) explosionRadius)), yAxis,
                ((size + 10) * ((double) explosionRadius)), size);
        explosionGroup.getChildren().addAll(explosionXLEFT, explosionXRIGHT);

        // top side of the explosion
        Rectangle explosionYDOWN = new Rectangle(xAxis, yAxis + size,
                size, ((size + 10) * ((double) explosionRadius)));

        // bottom side of the explosion
        Rectangle explosionYUP = new Rectangle(xAxis, yAxis - ((size + 10) * ((double) explosionRadius)),
                size, ((size + 10) * ((double) explosionRadius)));
        explosionGroup.getChildren().addAll(explosionYDOWN, explosionYUP);

    }

    public void checkCollisionBlock(ArrayList<Snowblock> snowBlocks) {
        for (Snowblock snowBlock : snowBlocks) {
            for (Node explosion : explosionGroup.getChildren()) {
                if (explosion.getBoundsInParent().intersects(snowBlock.getBlock().getBoundsInParent())) {
                    snowBlock.decreaseDurability(damage);
                }
            }
        }
    }

    public void checkCollisionPlayer(Player player) {
        for (Node explosion : explosionGroup.getChildren()) {
            if (explosion.getBoundsInParent().intersects(player.getPlayer().getBoundsInParent())) {
                player.setHealth(player.getHealth() - damage);
            }
        }
    }

    public void checkCollisionEnemy(ArrayList<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            for (Node explosion : explosionGroup.getChildren()) {
                if (explosion.getBoundsInParent().intersects(enemy.getBody().getBoundsInParent())) {
                    enemy.setHealthPoint(enemy.getHealthPoint() - damage);
                }
            }
        }
    }

    public Group getExplosionGroup() {
        return explosionGroup;
    }

    public void detectCollision() {
    }
}
