package com.example.comp2522202330termprojectsjb7788;

import com.example.comp2522202330termprojectsjb7788.Elements.*;
import com.example.comp2522202330termprojectsjb7788.enums.Directions;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 720);
        // add player rectangle
        Rectangle player = new Rectangle(240, 240, 40, 40);
        Player player1 = new Player(player, 100, 8, 0, 0);

        Rectangle berry = new Rectangle(540, 640, 25, 25);
        berry.setFill(Color.YELLOW);
        Berry berry1 = new Berry(1, 540, 640, 25);

        Grid grid = new Grid();
        grid.setSnowblocks(root);

        new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if (player.getBoundsInParent().intersects(berry.getBoundsInParent())) {
                    System.out.println("Berry collected!");
                    player1.collectBerry();
                    root.getChildren().remove(berry);
                    berry.setY(-50);
                    berry.setX(-50);
                }
            }
        }.start();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case W -> {
                        player1.move(Directions.UP);
                    }
                    case S -> player1.move(Directions.DOWN);
                    case D -> player1.move(Directions.RIGHT);
                    case A -> player1.move(Directions.LEFT);
                    case J -> { player1.placeSnowBomb(root); }
                }
            }
        });

//        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                switch (keyEvent.getCode()) {
//                    case W -> {
//                        player1.move(Directions.UP);
//                    }
//                    case S -> player1.move(Directions.DOWN);
//                    case D -> player1.move(Directions.RIGHT);
//                    case A -> player1.move(Directions.LEFT);
//                }
//            }
//        });

        // add rectangle to root
        root.getChildren().addAll(player, berry);

        stage.setTitle("Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}