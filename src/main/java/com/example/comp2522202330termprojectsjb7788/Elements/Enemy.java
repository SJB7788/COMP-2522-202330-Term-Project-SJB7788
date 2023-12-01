package com.example.comp2522202330termprojectsjb7788.Elements;


import com.example.comp2522202330termprojectsjb7788.enums.Directions;
import com.example.comp2522202330termprojectsjb7788.interfaces.Block;
import com.example.comp2522202330termprojectsjb7788.interfaces.EnemyCharacter;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;
import java.util.Random;

public class Enemy implements EnemyCharacter {
    private int SPEED = 5;
    private final int damage;
    private final int healthPoint;
    private final Rectangle body;
    private Directions initialDirection;

    public Enemy(int damage, int healthPoint, int x, int y) {
        this.damage = damage;
        this.healthPoint = healthPoint;
        Rectangle enemyRectangle = new Rectangle(x, y, 40, 40);
        enemyRectangle.setFill(Color.GREEN);
        this.body = enemyRectangle;

        Random random = new Random();
        int randomDirection = random.nextInt(4) + 1;
        switch (randomDirection) {
            case 1 -> this.initialDirection = Directions.UP;
            case 2 -> this.initialDirection = Directions.DOWN;
            case 3 -> this.initialDirection = Directions.LEFT;
            case 4 -> this.initialDirection = Directions.RIGHT;
        }
    }
    @Override
    public void move(Directions direction) {
        if (!detectCollision()) {
            body.setX(body.getX() + SPEED);
        }
    }

    public boolean detectCollision() {
        for (Block snowBlock : Grid.blocks) {
            // left collision x1 = 40, x2 = 0, y1 = 40, y2 = -40
            // right collision x1 = 0, x2 = -40, y1 = 40, y2 = -40
            // top collision x1 = 40, x2 = -40, y1 = 40, y2 = 0
            // bottom collision x1 = 40, x2 = -40, y1 = 0, y2 = -40
            if (!Objects.equals(snowBlock.getBlockType(), "Finishblock")
                    && snowBlock.getBlock().getX() - body.getX() < 40 && snowBlock.getBlock().getX() - body.getX() > 0
                    && snowBlock.getBlock().getY() - body.getY() < 40 && snowBlock.getBlock().getY() - body.getY() > -40) {
                SPEED *= -1;
                body.setX(body.getX() + SPEED);
                return true;
            }
        }
        return false;
    }

    public Rectangle getBody() {
        return body;
    }
}
