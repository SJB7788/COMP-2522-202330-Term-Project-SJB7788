package com.example.comp2522202330termprojectsjb7788;

import javafx.scene.shape.Rectangle;

public class Controller {
    private final Rectangle player;
    private final int SPEED = 25;

    public Controller(Rectangle player) {
        this.player = player;
    }
    public void moveUp() {
        player.setY(player.getY() - SPEED);
    }

    public void moveDown() {
        player.setY(player.getY() + SPEED);
    }

    public void moveLeft() {
        player.setX(player.getX() - SPEED);
    }

    public void moveRight() {
        player.setX(player.getX() + SPEED);
    }

    public void detectCollision() {

    }
}
