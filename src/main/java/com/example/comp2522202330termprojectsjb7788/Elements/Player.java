package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.Controller;
import com.example.comp2522202330termprojectsjb7788.enums.Directions;
import com.example.comp2522202330termprojectsjb7788.interfaces.SnowCharacter;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Player implements SnowCharacter {
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
        Controller controller = new Controller(player);
        switch (direction) {
            case UP -> controller.moveUp();
            case DOWN -> controller.moveDown();
            case LEFT -> controller.moveLeft();
            case RIGHT -> controller.moveRight();
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

    public void chargeSnow() {

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
