package com.example.comp2522202330termprojectsjb7788;

import com.example.comp2522202330termprojectsjb7788.Elements.Grid;
import javafx.scene.shape.Rectangle;

public class Controller {
    private final Rectangle player;
    private final int SPEED = 10;

    public Controller(Rectangle player) {
        this.player = player;
    }
    public void moveUp() {
        player.setY(player.getY() - SPEED);
        detectCollisionBottom();
    }

    public void moveDown() {
        player.setY(player.getY() + SPEED);
        detectCollisionTop();
    }

    public void moveLeft() {
        player.setX(player.getX() - SPEED);
        detectCollisionRight();
    }

    public void moveRight() {
        player.setX(player.getX() + SPEED);
        detectCollisionLeft();
    }

    public void detectCollisionLeft() {
        for (Rectangle snowBlock : Grid.snowBlocks) {
            // left collision
            if (snowBlock.getX() - player.getX() < 40 && snowBlock.getX() - player.getX() > 0
                    && snowBlock.getY() - player.getY() < 40 && snowBlock.getY() - player.getY() > -40) {
                player.setX(player.getX() - SPEED);
                System.out.println("Left collision");
                return;
            }
        }
    }

    public void detectCollisionRight() {
        for (Rectangle snowBlock : Grid.snowBlocks) {
            // right collision
            if (snowBlock.getX() - player.getX() < 0 && snowBlock.getX() - player.getX() > -40
                    && snowBlock.getY() - player.getY() < 40 && snowBlock.getY() - player.getY() > -40) {
                player.setX(player.getX() + SPEED);
                System.out.println("Right collision");
                return;
            }
        }
    }

    public void detectCollisionTop() {
        for (Rectangle snowBlock : Grid.snowBlocks) {
            // top collision
            if (snowBlock.getX() - player.getX() < 40 && snowBlock.getX() - player.getX() > -40
                    && snowBlock.getY() - player.getY() < 40 && snowBlock.getY() - player.getY() > 0) {
                player.setY(player.getY() - SPEED);
                System.out.println("Top collision");
                return;
            }
        }
    }

    public void detectCollisionBottom() {
        for (Rectangle snowBlock : Grid.snowBlocks) {
            // bottom collision
            if (snowBlock.getX() - player.getX() < 40 && snowBlock.getX() - player.getX() > -40
                    && snowBlock.getY() - player.getY() < 0 && snowBlock.getY() - player.getY() > -40) {
                player.setY(player.getY() + SPEED);
                System.out.println("Bottom collision");
                return;
            }
        }
    }

//    public void detectCollision() {
//        for (Rectangle snowBlock : Grid.snowBlocks) {
//            // left collision
//            if (snowBlock.getX() - player.getX() < 40 && snowBlock.getX() - player.getX() > 0
//                && snowBlock.getY() - player.getY() < 30 && snowBlock.getY() - player.getY() > -30) {
//                player.setX(player.getX() - SPEED);
//                System.out.println("Left collision");
//                return;
//            }
//            // right collision
//            if (snowBlock.getX() - player.getX() < 0 && snowBlock.getX() - player.getX() > -40
//                    && snowBlock.getY() - player.getY() < 30 && snowBlock.getY() - player.getY() > -30) {
//                player.setX(player.getX() + SPEED);
//                System.out.println("Right collision");
//                return;
//            }
//            // top collision
//            if (snowBlock.getX() - player.getX() < 30 && snowBlock.getX() - player.getX() > -30
//                    && snowBlock.getY() - player.getY() < 40 && snowBlock.getY() - player.getY() > 0) {
//                player.setY(player.getY() - SPEED);
//                System.out.println("Top collision");
//                return;
//            }
//            // bottom collision
//            if (snowBlock.getX() - player.getX() < 30 && snowBlock.getX() - player.getX() > -30
//                    && snowBlock.getY() - player.getY() < 0 && snowBlock.getY() - player.getY() > -40) {
//                player.setY(player.getY() + SPEED);
//                System.out.println("Bottom collision");
//                return;
//            }
//
//        }
//    }
}
