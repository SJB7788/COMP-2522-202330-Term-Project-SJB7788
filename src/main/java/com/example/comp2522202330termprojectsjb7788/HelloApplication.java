package com.example.comp2522202330termprojectsjb7788;

import com.example.comp2522202330termprojectsjb7788.Characters.Player;
import com.example.comp2522202330termprojectsjb7788.enums.Directions;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 720);
        // add player rectangle
        Rectangle player = new Rectangle(250, 250, 50, 50);
        Rectangle berry = new Rectangle(550, 650, 25, 25);
        berry.setFill(Color.YELLOW);
        Player player1 = new Player(player, 100, 10, 0, 0);

        scene.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case W:
                        player1.move(Directions.UP);
                        break;
                    case S:
                        player1.move(Directions.DOWN);
                        break;
                    case D:
                        player1.move(Directions.RIGHT);
                        break;
                    case A:
                        player1.move(Directions.LEFT);
                        break;
                    case J:
                        player1.placeSnowBomb(root);
                        break;
                }
        });

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (player.getBoundsInParent().intersects(berry.getBoundsInParent())) {
                    System.out.println("Berry collected!");
                    player1.collectBerry();
                    root.getChildren().remove(berry);
                    berry.setY(-50);
                    berry.setX(-50);
                }
            }
        }.start();

        // add rectangle to root
        root.getChildren().addAll(player, berry);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}