package com.example.comp2522202330termprojectsjb7788;

import com.example.comp2522202330termprojectsjb7788.Elements.*;
import com.example.comp2522202330termprojectsjb7788.enums.Directions;
import com.example.comp2522202330termprojectsjb7788.interfaces.Block;
import com.example.comp2522202330termprojectsjb7788.interfaces.Item;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class GameLoop extends Application {

    private boolean gameWon = false;
    private boolean gameStarted = false;
    private boolean isAtFinishBlock = false;
    private HelloController controller;
    private Stage stage;
    private Rectangle player;
    boolean isMovingUp = false;
    boolean isMovingDown = false;
    boolean isMovingLeft = false;
    boolean isMovingRight = false;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        showStartScreen();
    }

    public static void main(String[] args) {
        launch();
    }

    public void showStartScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(GameLoop.class.getResource("start-menu-alpha.fxml"));
            Scene startScene = new Scene(loader.load(), 1280, 720);
            controller = loader.getController();
            controller.setApplication(this);
            stage.setScene(startScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if (gameStarted) {
                    showGameScreen();
                }
            }
        }.start();
    }

    public void showGameScreen() {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 720);
        scene.setFill(Color.ANTIQUEWHITE);

        Grid grid = new Grid();

        //load maps
        Map map = new Map("./maps/map 2.txt");
        map.loadMap();

        grid.placeStartingBlock(root);
        grid.placeStoneBlocks(root);
        grid.placeSnowBlocks(root);
        grid.placeFinishBlocks(root);
        grid.placeBerryBlocks(root);
        grid.placeEnemyBlocks(root);

        // add player rectangle
        player = new Rectangle(Grid.startingBlock.getBlock().getX(), Grid.startingBlock.getBlock().getY(), 40, 40);
        player.setFill(Color.YELLOW);
        Player playerObj = new Player(player, 50, 4, 0, 0);

        Text score = new Text();
        score.setX(10);
        score.setY(25);
        score.setText("Berries Collected: 0");
        score.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        score.setFill(Color.WHITE);

        Text healthStat = new Text();
        healthStat.setX(1000);
        healthStat.setY(25);
        healthStat.setText("Health: " + playerObj.getHealth());
        healthStat.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        healthStat.setFill(Color.WHITE);

        Text damageStat = new Text();
        damageStat.setX(1150);
        damageStat.setY(25);
        damageStat.setText("Damage: " + playerObj.getDamage());
        damageStat.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        damageStat.setFill(Color.WHITE);


        new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if (playerObj.getHealth() <= 0) {
                    Text gameOver = new Text();
                    gameOver.setText("GAME OVER");
                    gameOver.setFont(Font.font("Arial", FontWeight.BOLD, 100));
                    gameOver.setX(root.getWidth() / 2 - 100 * 2);
                    gameOver.setY(root.getHeight() / 2);
                    root.getChildren().add(gameOver);
                    for (Enemy enemy : Grid.enemies) {
                        enemy.stopMove();
                    }
                    stop();
                }

                healthStat.setText("Health: " + playerObj.getHealth());

                for (Snowblock snowblock : Grid.snowBlocks) {
                    if (snowblock.doesContainsItem()) {
                        Item item = snowblock.getItem();
                        if (player.getBoundsInParent().intersects(item.getItemRect().getBoundsInParent())) {
                            item.useItem(playerObj);
                            damageStat.setText("Damage: " + playerObj.getDamage());
                        }
                    }
                }

                for (BerryBlocks berryblock : Grid.berryBlocks) {
                    if (player.getBoundsInParent().intersects(berryblock.getBerry().getItemRect().getBoundsInParent())) {
                        playerObj.collectBerry();
                        score.setText("Berries Collected: " + playerObj.getBerryAmount());
                        berryblock.getBerry().getItemRect().setX(-50);
                        berryblock.getBerry().getItemRect().setY(-50);
                    }
                }

                for (Enemy enemy : Grid.enemies) {
                    if (player.getX() == enemy.getBody().getX()
                            && player.getY() - enemy.getBody().getY() < 40 && player.getY() - enemy.getBody().getY() > -40) {
                        playerObj.setHealth(playerObj.getHealth() - enemy.getDamage());
                    }
                }

                if (playerObj.getBerryAmount() >= 6) {
                    player.setFill(Color.GREEN);
                    for (Finishblock finishblock : Grid.finishBlocks) {
                        finishblock.showFinishBlock();
                        if (player.getBoundsInParent().intersects(finishblock.getBlock().getBoundsInParent())) {
                            isAtFinishBlock = true;
                        }
                        else {
                            isAtFinishBlock = false;
                        }
                    }
                }

                if (gameWon) {
                    Text winner = new Text();
                    winner.setText("YOU WIN!");
                    winner.setFont(Font.font("Arial", FontWeight.BOLD, 100));
                    winner.setX(root.getWidth() / 2 - 100 * 2);
                    winner.setY(root.getHeight() / 2);
                    root.getChildren().add(winner);
                }
            }
        }.start();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case W -> playerObj.move(Directions.UP);
                    case S -> playerObj.move(Directions.DOWN);
                    case D -> playerObj.move(Directions.RIGHT);
                    case A -> playerObj.move(Directions.LEFT);
                    case J -> { playerObj.placeSnowBomb(root); }
                    case L -> {
                        if (isAtFinishBlock) {
                            playerObj.chargeSnow(root);
                            gameWon = true;
                        }
                    }
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.A) {
                    playerObj.stopMove(Directions.LEFT);
                }
                if (event.getCode() == KeyCode.D) {
                    playerObj.stopMove(Directions.RIGHT);
                }
                if (event.getCode() == KeyCode.W) {
                    playerObj.stopMove(Directions.UP);
                }
                if (event.getCode() == KeyCode.S) {
                    playerObj.stopMove(Directions.DOWN);
                }
            }
        });

        // add rectangle to root
        root.getChildren().addAll(player, score, healthStat, damageStat);

        stage.setScene(scene);
    }

    private AnimationTimer getAnimationTimer() {
        Controller controller = new Controller(player, 2);
        AnimationTimer rectangleAnimation = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if (isMovingRight) {
                    controller.moveRight();
                }
                if (isMovingLeft) {
                    controller.moveLeft();
                }
                if (isMovingUp) {
                    controller.moveUp();
                }
                if (isMovingDown) {
                    controller.moveDown();
                }
            }
        };
        return rectangleAnimation;
    }
}

