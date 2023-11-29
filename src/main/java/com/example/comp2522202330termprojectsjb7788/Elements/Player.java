package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.Controller;
import com.example.comp2522202330termprojectsjb7788.enums.Directions;
import com.example.comp2522202330termprojectsjb7788.interfaces.SnowCharacter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Player implements SnowCharacter {
    private final Rectangle player;
    private int healthPoint;
    private int damage;
    private int snowAmount;
    private int berryAmount;
    private int allowMovement;

    public Player(Rectangle player, int healthPoint, int damage, int snowAmount, int berryAmount) {
        this.player = player;
        this.healthPoint = healthPoint;
        this.damage = damage;
        this.snowAmount = snowAmount;
        this.berryAmount = berryAmount;
        this.allowMovement = 1;
    }

    public void move(Directions direction) {
        Controller controller = new Controller(player);
        if (allowMovement == 1) {
            switch (direction) {
                case UP -> controller.moveUp();
                case DOWN -> controller.moveDown();
                case LEFT -> controller.moveLeft();
                case RIGHT -> controller.moveRight();
            }
        }
    }

    public void setDamage(int damage) {
        this.damage = damage;
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

    public void chargeSnow(Pane root) {
        allowMovement = 0;
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler() {
            int timerCount = 0;
            @Override
            public void handle(javafx.event.Event event) {
                timerCount++;
                Rectangle chargeSquareOne = new Rectangle(player.getX() - 20, player.getY() - 20, 80, 80);
                chargeSquareOne.setFill(Color.LIGHTCYAN);

                Rectangle chargeSquareTwo = new Rectangle(player.getX() - 140, player.getY() - 140, 320, 320);
                chargeSquareTwo.setFill(Color.LIGHTBLUE);

                Rectangle chargeSquareThree = new Rectangle(player.getX() - 300, player.getY() - 300, 640, 640);
                chargeSquareThree.setFill(Color.BLUE);

                if (timerCount == 1) {
                    System.out.println("add 1");
                    root.getChildren().add(chargeSquareOne);
                }
                if (timerCount == 2) {
                    System.out.println("add 2");
                    root.getChildren().add(chargeSquareTwo);
                }
                if (timerCount == 3) {
                    System.out.println("add 3");
                    root.getChildren().add(chargeSquareThree);
                }
                if (timerCount == 4) {
                    root.getChildren().removeAll(chargeSquareOne, chargeSquareTwo, chargeSquareThree);

                    timeline.stop();
                }
            }
        }));
        timeline.playFromStart();
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

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return healthPoint;
    }

    public void setHealth(int healthPoint) {
        this.healthPoint = healthPoint;
    }
}
