package com.example.comp2522202330termprojectsjb7788;

import com.example.comp2522202330termprojectsjb7788.Elements.*;
import com.example.comp2522202330termprojectsjb7788.enums.Directions;
import com.example.comp2522202330termprojectsjb7788.interfaces.Item;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 720);
        scene.setFill(Color.ANTIQUEWHITE);
        // add player rectangle
        Rectangle player = new Rectangle(40, 40, 40, 40);
        player.setFill(Color.YELLOW);
        Player playerObj = new Player(player, 10, 4, 0, 0);

        Grid grid = new Grid();

        //load maps
        Map map = new Map("./maps/map 1.txt");
        map.loadMap();

        grid.placeStoneBlocks(root);
        grid.placeSnowBlocks(root);

        Text score = new Text();
        score.setX(10);
        score.setY(25);
        score.setText("Berries Collected: 0");
        score.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        new AnimationTimer() {
            int scoreCount = 0;
            @Override
            public void handle(long timestamp) {
                for (Snowblock snowblock : Grid.snowBlocks) {
                    if (snowblock.doesContainsItem()) {
                        Item item = snowblock.getItem();
                        if (player.getBoundsInParent().intersects(item.getItemRect().getBoundsInParent())) {
                            item.useItem(playerObj);
                            scoreCount = playerObj.getBerryAmount();
                            score.setText("Berries Collected: " + scoreCount);
                        }
                    }
                }

                if (playerObj.getBerryAmount() >= 6) {
                    Text winner = new Text();
                    winner.setText("YOU WIN!");
                    winner.setFont(Font.font("Arial", FontWeight.BOLD, 100));
                    winner.setX(root.getWidth() / 2 - 100 * 2);
                    winner.setY(root.getHeight() / 2);
                    root.getChildren().add(winner);
                    this.stop();
                }
            }
        }.start();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case W -> {
                        playerObj.move(Directions.UP);
                    }
                    case S -> playerObj.move(Directions.DOWN);
                    case D -> playerObj.move(Directions.RIGHT);
                    case A -> playerObj.move(Directions.LEFT);
                    case J -> { playerObj.placeSnowBomb(root); }
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
        root.getChildren().addAll(player, score);

        stage.setTitle("Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}