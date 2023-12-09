package com.example.comp2522202330termprojectsjb7788.Elements;


import com.example.comp2522202330termprojectsjb7788.GameLoop;
import com.example.comp2522202330termprojectsjb7788.enums.Directions;
import com.example.comp2522202330termprojectsjb7788.interfaces.Block;
import com.example.comp2522202330termprojectsjb7788.interfaces.EnemyCharacter;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;
import java.util.Random;

public class Enemy implements EnemyCharacter {
    private int speed;
    private final int damage;
    private int healthPoint;
    private final Rectangle body;
    private final ImageView enemyImage;
    private Directions initialDirection;
    private AnimationTimer movementTimer;

    public Enemy(int damage, int healthPoint, int x, int y) {
        this.damage = damage;
        this.healthPoint = healthPoint;
        int size = 40;
        Rectangle enemyRectangle = new Rectangle(x, y, size, size);
        enemyRectangle.setFill(Color.GREEN);
        this.body = enemyRectangle;

        enemyImage = new ImageView(new Image(GameLoop.class.getResource("ice slime.png").toString()));
        enemyImage.setFitWidth(size);
        enemyImage.setFitHeight(size);
        enemyImage.setX(x);
        enemyImage.setY(y);

        Random random = new Random();
        int randomDirection = random.nextInt(4) + 1;
        switch (randomDirection) {
            case 1 -> this.initialDirection = Directions.UP;
            case 2 -> this.initialDirection = Directions.DOWN;
            case 3 -> this.initialDirection = Directions.LEFT;
            case 4 -> this.initialDirection = Directions.RIGHT;
        }

        speed = random.nextInt(3) + 1;
    }

    @Override
    public void move(Directions direction) {
        movementTimer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if (!detectCollision()) {
                    body.setX(body.getX() + speed);
                    enemyImage.setX(body.getX());
                }
                if (getHealthPoint() <= 0) {
                    body.setY(-100);
                    enemyImage.setY(-100);
                    stop();
                }
            }
        };
        movementTimer.start();
    }

    public void stopMove() {
        movementTimer.stop();
    }

    public boolean detectCollision() {
        for (Block snowBlock : Grid.blocks) {
            // left collision x1 = 40, x2 = 0, y1 = 40, y2 = -40
            // right collision x1 = 0, x2 = -40, y1 = 40, y2 = -40
            // top collision x1 = 40, x2 = -40, y1 = 40, y2 = 0
            // bottom collision x1 = 40, x2 = -40, y1 = 0, y2 = -40
            if (!Objects.equals(snowBlock.getBlockType(), "Finishblock")
                    && snowBlock.getBlock().getX() - body.getX() < 40 && snowBlock.getBlock().getX() - body.getX() > -40
                    && snowBlock.getBlock().getY() - body.getY() < 40 && snowBlock.getBlock().getY() - body.getY() > -40) {
                speed *= -1;
                body.setX(body.getX() + speed);
                return true;
            }
        }
        return false;
    }

    public Rectangle getBody() {
        return body;
    }

    public ImageView getEnemyImage() {
        return enemyImage;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }
}
