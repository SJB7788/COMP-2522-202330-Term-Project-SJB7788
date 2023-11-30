package com.example.comp2522202330termprojectsjb7788;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloController {
    private GameLoop gameLoop;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onStartPlay() {
        System.out.println("Start Play");
        gameLoop.showGameScreen();
    }

    @FXML
    protected void onStartExit() {
        System.out.println("Exit");
    }

    public void setApplication(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }
}