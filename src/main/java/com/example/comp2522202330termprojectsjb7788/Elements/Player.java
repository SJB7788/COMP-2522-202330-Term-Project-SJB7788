package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.Controller;
import com.example.comp2522202330termprojectsjb7788.enums.Directions;
import com.example.comp2522202330termprojectsjb7788.interfaces.SnowCharacter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Player implements SnowCharacter {
    private final Rectangle player;
    private int healthPoint;
    private int damage;
    private int speed;
    private final int[] speedArray = {2, 4, 8, 10, 20};
    private int speedIndex;
    private int snowAmount;
    private int berryAmount;
    private int allowMovement;
    private Controller controller;

    public Player(Rectangle player, int healthPoint, int damage, int snowAmount, int berryAmount) {
        this.player = player;
        this.healthPoint = healthPoint;
        this.damage = damage;
        this.snowAmount = snowAmount;
        this.berryAmount = berryAmount;
        this.allowMovement = 1;
        speedIndex = 0;
        this.speed = speedArray[speedIndex];
        controller = new Controller(player, speed);
    }

    public void move(Directions direction) {
        if (allowMovement == 1) {
            switch (direction) {
                case UP -> controller.setMovingUp(true);
                case DOWN -> controller.setMovingDown(true);
                case LEFT -> controller.setMovingLeft(true);
                case RIGHT -> controller.setMovingRight(true);
            }
        }
    }

    public void stopMove(Directions direction) {
        switch (direction) {
            case UP -> controller.setMovingUp(false);
            case DOWN -> controller.setMovingDown(false);
            case LEFT -> controller.setMovingLeft(false);
            case RIGHT -> controller.setMovingRight(false);
        }
    }

    public void stopControllerAnimation() {
        controller.stopAnimationTimer();
    }
    public void startControllerAnimation() {
        controller.startAnimationTimer();
    }

    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }
    public void incSpeed() {
        if (speedIndex < 4) {
            speedIndex++;
            this.speed = speedArray[speedIndex];
            controller.setSpeed(speedArray[speedIndex]);
            getPlayer().setX((int) Math.round(player.getX() / speed) * speed);
            getPlayer().setY((int) Math.round(player.getY() / speed) * speed);
        }
        controller.setSpeed(getSpeed());
    }


    public void collectSnow() {
    }

    public void throwSnow() {
    }

    public void rollSnow() {
    }

    public Bomb placeSnowBomb(Pane root) {
        Bomb bomb = new Bomb(root, this, damage, getDamage());
        bomb.placeBomb();
        bomb.explode();
        return bomb;
    }

    public int chargeSnow(Pane root) {
        allowMovement = 0;
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            int timerCount = 0;
            final Rectangle chargeSquareOne = new Rectangle(player.getX() - 60, player.getY() - 20, 160, 80);
            final Rectangle chargeSquareTwo = new Rectangle(player.getX() - 300, player.getY() - 140, 640, 320);
            final Rectangle chargeSquareThree = new Rectangle(player.getX() - 620, player.getY() - 300, 1280, 640);

            @Override
            public void handle(ActionEvent event) {
                chargeSquareOne.setFill(Color.LIGHTCYAN);
                chargeSquareTwo.setFill(Color.LIGHTBLUE);
                chargeSquareThree.setFill(Color.BLUE);

                chargeSquareOne.setOpacity(0);
                chargeSquareTwo.setOpacity(0);
                chargeSquareThree.setOpacity(0);
                if (timerCount == 0) {
                    root.getChildren().addAll(chargeSquareThree, chargeSquareTwo, chargeSquareOne);
                }
                timerCount++;

                if (timerCount == 1) {
                    chargeSquareOne.setOpacity(1);
                    player.toFront();
                } else if (timerCount == 2) {
                    chargeSquareTwo.setOpacity(1);
                    player.toFront();
                } else if (timerCount == 3) {
                    chargeSquareThree.setOpacity(1);
                } else if (timerCount == 4) {
                    chargeSquareOne.setOpacity(0);
                    chargeSquareTwo.setOpacity(0);
                    chargeSquareThree.setOpacity(0);
                    root.getChildren().removeAll(chargeSquareOne, chargeSquareTwo, chargeSquareThree);
                    timeline.stop();
                }
            }
        }));

        timeline.playFromStart();
        return 1;
    }

    public void collectBerry() {
        berryAmount++;
    }

    public Rectangle getPlayer() {
        return player;
    }

    public int getBerryAmount() {
        return berryAmount;
    }

    public int getHealth() {
        return healthPoint;
    }

    public void setHealth(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public Controller getController() {
        return controller;
    }
}
