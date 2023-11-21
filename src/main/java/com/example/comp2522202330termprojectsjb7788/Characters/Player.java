package com.example.comp2522202330termprojectsjb7788.Characters;

import com.example.comp2522202330termprojectsjb7788.enums.Directions;
import com.example.comp2522202330termprojectsjb7788.interfaces.SnowCharacter;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player implements SnowCharacter {
    private final int SPEED = 10;
    private final Rectangle player;
    private int healthPoint;
    private int damage;
    private int snowAmount;
    private int berryAmount;

    public Player(Rectangle player, int healthPoint, int damage, int snowAmount, int berryAmount) {
        this.player = player;
        this.healthPoint = healthPoint;
        this.damage = damage;
        this.snowAmount = snowAmount;
        this.berryAmount = berryAmount;
    }

    public void move(Directions direction) {
        switch (direction) {
            case UP -> player.setY(player.getY() - SPEED);
            case DOWN -> player.setY(player.getY() + SPEED);
            case LEFT -> player.setX(player.getX() - SPEED);
            case RIGHT -> player.setX(player.getX() + SPEED);
        }
    }

    public int getHealth() {
        return healthPoint;
    }

    public void collectSnow() {
    }

    public void throwSnow() {
    }

    public void rollSnow() {
    }

    public void placeSnowBomb(Pane root) {
        Bomb bomb = new Bomb(root, player,10, 40);
        bomb.placeBomb();
        bomb.explode();
    }

    public void chargeSnow() {
    }

    public void collectBerry() {
        berryAmount++;
    }
}
