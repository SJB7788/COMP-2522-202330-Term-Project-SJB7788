package com.example.comp2522202330termprojectsjb7788;

import com.example.comp2522202330termprojectsjb7788.Elements.*;
import com.example.comp2522202330termprojectsjb7788.enums.Directions;
import com.example.comp2522202330termprojectsjb7788.interfaces.Item;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private boolean gameStatus = false;
    private boolean gameWon = false;
    private boolean gamePaused = false;
    private boolean isAtFinishBlock = false;
    private HelloController helloController;
    private String mapFile;
    private Stage stage;
    private Rectangle player;
    private final Pane gameRoot = new Pane();
    private final Scene gameScene = new Scene(gameRoot, 1280, 720);
    private final double centerX = gameRoot.getPrefWidth() / 2;
    private final double centerY = gameRoot.getPrefHeight() / 2;
    private final Rectangle popUpBackground = new Rectangle(centerX + 320, centerY + 180, 640, 360);
    private final Button resumeButton = makeButton("Resume", "Arial", "gray", "white", 15,
            100, 25, "subtract", "add", 200, 90);
    private final Button restartButton = makeButton("Restart", "Arial", "gray", "white", 15,
            100, 25, "subtract", "add", 50, 90);
    private final Button exitButton = makeButton("Exit", "Arial", "gray", "white", 15,
            100, 25, "add", "add", 100, 90);
    private final Text popUpText = new Text();

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        popUpBackground.setFill(Color.DARKGRAY);
        showStartScreen();
    }

    public static void main(String[] args) {
        launch();
    }

    public void showStartScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(GameLoop.class.getResource("start-menu-alpha.fxml"));
            Scene startScene = new Scene(loader.load(), 1280, 720);
            helloController = loader.getController();
            helloController.setApplication(this);
            stage.setScene(startScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showGameScreen() {
        gameScene.setFill(Color.ANTIQUEWHITE);

        Grid grid = new Grid();

        //load maps
        if (mapFile == null) {
            mapFile = "map1.txt";
        }

        Map map = new Map("./maps/" + mapFile);
        map.loadMap();

        ImageView background = new ImageView(new Image(GameLoop.class.getResource("snow.png").toString()));
        background.setFitWidth(1280);
        background.setFitHeight(720);

        grid.placeStartingBlock(gameRoot);
        grid.placeStoneBlocks(gameRoot);
        grid.placeSnowBlocks(gameRoot);
        grid.placeFinishBlocks(gameRoot);
        grid.placeBerryBlocks(gameRoot);
        grid.placeEnemyBlocks(gameRoot);


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
                    gameStatus = false;
                    playerObj.stopControllerAnimation();
                    for (Enemy enemy : Grid.enemies) {
                        enemy.stopMove();
                    }
                    showLossScreen();
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
                        } else {
                            isAtFinishBlock = false;
                        }
                    }
                }

                if (!gameStatus && gameWon) {
                    showWinScreen();
                    stop();
                }
            }
        }.start();

        resumeButton.setOnAction(e -> resumeGame(playerObj));
        restartButton.setOnAction(e -> restartGame());
        exitButton.setOnAction(e -> quitGame());

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (gameStatus) {
                    switch (keyEvent.getCode()) {
                        case W -> playerObj.move(Directions.UP);
                        case S -> playerObj.move(Directions.DOWN);
                        case D -> playerObj.move(Directions.RIGHT);
                        case A -> playerObj.move(Directions.LEFT);
                        case J -> {
                            playerObj.placeSnowBomb(gameRoot);
                        }
                        case L -> {
                            if (isAtFinishBlock) {
                                playerObj.chargeSnow(gameRoot);
                                for (Enemy enemy : Grid.enemies) {
                                    enemy.stopMove();
                                }
                                playerObj.setAllowMovement(0);
                                gameStatus = false;
                                gameWon = true;
                            }
                        }
                        case ESCAPE -> {
                            gamePaused = !gamePaused;
                            if (gamePaused) {
                                showPauseScreen(gamePaused, resumeButton, restartButton, exitButton);
                                playerObj.stopControllerAnimation();
                                for (Enemy enemy : Grid.enemies) {
                                    enemy.stopMove();
                                }
                            } else {
                                showPauseScreen(gamePaused, resumeButton, restartButton, exitButton);
                                playerObj.startControllerAnimation();
                                for (Enemy enemy : Grid.enemies) {
                                    enemy.move(Directions.RIGHT);
                                }
                            }
                        }
                    }
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
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
        gameRoot.getChildren().addAll(player, score, healthStat, damageStat, background);

        stage.setScene(gameScene);
        background.toBack();
    }

    public void setMapFile(String mapFile) {
        this.mapFile = mapFile;
    }

    private void showPauseScreen(boolean isPaused, Button resumeButton, Button restartButton, Button exitButton) {
        popUpBackground.setFill(Color.web("#9fcaf2"));
        if (isPaused) {
            popUpBackground.visibleProperty().setValue(true);
            resumeButton.visibleProperty().setValue(true);
            restartButton.visibleProperty().setValue(true);
            exitButton.visibleProperty().setValue(true);
            popUpText.visibleProperty().setValue(true);

            popUpText.setText("PAUSED");
            popUpText.setFont(Font.font("Batang", FontWeight.BOLD, 75));
            popUpText.layoutXProperty().bind(gameRoot.widthProperty().subtract(popUpText.prefWidth(-1)).divide(2));
            popUpText.layoutYProperty().bind(gameRoot.heightProperty().subtract(popUpText.prefHeight(-1)).divide(2).subtract(0));

            gameRoot.getChildren().addAll(popUpBackground, popUpText, resumeButton, restartButton, exitButton);
        } else {
            popUpText.visibleProperty().setValue(false);
            popUpBackground.visibleProperty().setValue(false);
            resumeButton.visibleProperty().setValue(false);
            restartButton.visibleProperty().setValue(false);
            exitButton.visibleProperty().setValue(false);
            gameRoot.getChildren().removeAll(popUpBackground, popUpText, resumeButton, restartButton, exitButton);
        }
    }

    private void showWinScreen() {
        popUpBackground.setFill(Color.web("#9fcaf2"));
        popUpBackground.visibleProperty().setValue(true);
        restartButton.visibleProperty().setValue(true);
        exitButton.visibleProperty().setValue(true);
        popUpText.visibleProperty().setValue(true);

        popUpText.setText("YOU WIN!");
        popUpText.setFont(Font.font("Batang", FontWeight.BOLD, 75));
        popUpText.layoutXProperty().bind(gameRoot.widthProperty().subtract(popUpText.prefWidth(-1)).divide(2));
        popUpText.layoutYProperty().bind(gameRoot.heightProperty().subtract(popUpText.prefHeight(-1)).divide(2).subtract(0));

        popUpBackground.toFront();
        popUpText.toFront();
        restartButton.toFront();
        exitButton.toFront();

        gameRoot.getChildren().addAll(popUpBackground, popUpText, restartButton, exitButton);
    }

    private void showLossScreen() {
        gameRoot.getChildren().removeAll(popUpBackground, popUpText, restartButton, exitButton);
        popUpBackground.setFill(Color.web("#9fcaf2"));
        popUpBackground.visibleProperty().setValue(true);
        restartButton.visibleProperty().setValue(true);
        exitButton.visibleProperty().setValue(true);
        popUpText.visibleProperty().setValue(true);

        popUpText.setText("YOU LOST!");
        popUpText.setFont(Font.font("Batang", FontWeight.BOLD, 75));
        popUpText.layoutXProperty().bind(gameRoot.widthProperty().subtract(popUpText.prefWidth(-1)).divide(2));
        popUpText.layoutYProperty().bind(gameRoot.heightProperty().subtract(popUpText.prefHeight(-1)).divide(2).subtract(0));

        popUpBackground.toFront();
        popUpText.toFront();
        restartButton.toFront();
        exitButton.toFront();

        gameRoot.getChildren().addAll(popUpBackground, popUpText, restartButton, exitButton);
    }

    private Button makeButton(String text, String font, String bgColour, String textColour, int size, int width,
                              int height, String operandX, String operandY, int x, int y) {
        Button button = new Button();
        button.setText(text);
        button.setFont(Font.font(font, FontWeight.SEMI_BOLD, size));
        button.styleProperty().setValue("-fx-background-color: " + bgColour + "; -fx-text-fill: " +
                textColour + "; -fx-min-width: " + width + "; -fx-min-height: " + height + "px; -fx-cursor: hand;");

        if (Objects.equals(operandX, "add")) {
            button.layoutXProperty().bind(gameRoot.widthProperty().subtract(button.prefWidth(-1)).divide(2).add(x));
        } else {
            button.layoutXProperty().bind(gameRoot.widthProperty().subtract(button.prefWidth(-1)).divide(2).subtract(x));
        }

        if (Objects.equals(operandY, "add")) {
            button.layoutYProperty().bind(gameRoot.heightProperty().subtract(button.prefHeight(-1)).divide(2).add(y));
        } else {
            button.layoutYProperty().bind(gameRoot.heightProperty().subtract(button.prefHeight(-1)).divide(2).subtract(y));
        }

        return button;
    }

    public void setStatus(boolean status) {
        gameStatus = status;
    }

    private void restartGame() {
        clearPane();
        showGameScreen();
    }

    private void resumeGame(Player player) {
        gamePaused = false;
        showPauseScreen(gamePaused, resumeButton, restartButton, exitButton);
        if (!gamePaused) {
            for (Enemy enemy : Grid.enemies) {
                enemy.move(Directions.RIGHT);
            }
            player.startControllerAnimation();
        }
    }

    private void clearPane() {
        gamePaused = false;
        gameWon = false;
        gameStatus = true;
        gameRoot.getChildren().clear();
        Grid.enemies.clear();
        Grid.snowBlocks.clear();
        Grid.stoneBlocks.clear();
        Grid.finishBlocks.clear();
        Grid.berryBlocks.clear();
        Grid.blocks.clear();
        Grid.startingBlock = null;
    }

    private void quitGame() {
        clearPane();
        setStatus(false);
        showStartScreen();
    }
}

