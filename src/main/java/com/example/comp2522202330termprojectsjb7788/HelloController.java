package com.example.comp2522202330termprojectsjb7788;

import com.example.comp2522202330termprojectsjb7788.Elements.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    private GameLoop gameLoop;

    @FXML
    private Label welcomeText;

    @FXML
    private Pane tutorialOnePane;
    @FXML
    private Pane tutorialTwoPane;
    @FXML
    private Pane tutorialThreePane;
    @FXML
    private Pane tutorialFourPane;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onStartPlay() {
        gameLoop.setStatus(true);
        gameLoop.showGameScreen();
    }

    @FXML
    protected void onStartExit() {
        Platform.exit();
        System.exit(0);
    }
    @FXML
    protected void onStartHelp() {
        tutorialOnePane.setVisible(true);
    }
    @FXML
    protected void onTutorialOneNext() {
        tutorialOnePane.setVisible(false);
        tutorialTwoPane.setVisible(true);
    }
    @FXML
    protected void onTutorialTwoNext() {
        tutorialTwoPane.setVisible(false);
        tutorialThreePane.setVisible(true);
    }
    @FXML
    protected void onTutorialThreeNext() {
        tutorialThreePane.setVisible(false);
        tutorialFourPane.setVisible(true);
    }
    @FXML
    protected void onTutorialTwoPrevious() {
        tutorialTwoPane.setVisible(false);
        tutorialOnePane.setVisible(true);
    }
    @FXML
    protected void onTutorialThreePrevious() {
        tutorialThreePane.setVisible(false);
        tutorialTwoPane.setVisible(true);
    }
    @FXML
    protected void onTutorialFourPrevious() {
        tutorialFourPane.setVisible(false);
        tutorialThreePane.setVisible(true);
    }
    @FXML
    protected void onTutorialClose() {
        tutorialOnePane.setVisible(false);
        tutorialTwoPane.setVisible(false);
        tutorialThreePane.setVisible(false);
        tutorialFourPane.setVisible(false);
    }

    public void setApplication(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }
}