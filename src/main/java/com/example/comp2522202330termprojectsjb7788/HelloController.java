package com.example.comp2522202330termprojectsjb7788;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.File;

public class HelloController {
    private GameLoop gameLoop;
    @FXML
    private Label welcomeText;
    @FXML
    private Pane startMapChoicePane;
    @FXML
    private ChoiceBox<String> startMapChoiceBox;
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
        onStartMapChoice();
        startMapChoicePane.setVisible(true);
    }

    @FXML
    protected void onStartMapChoice() {
        ObservableList<String> mapList = FXCollections.observableArrayList();

        File mapDirectory = new File("./maps");
        File[] mapFiles = mapDirectory.listFiles();
        if (mapFiles != null) {
            for (File mapFile : mapFiles) {
                if (mapFile.isFile()) {
                    mapList.add(mapFile.getName());
                }
            }
        }
        else {
            System.out.println("No maps found");
        }

        startMapChoiceBox.setItems(mapList);
    }

    @FXML
    protected void onStartMapChoiceSubmit() {
        String selectedMap = startMapChoiceBox.getSelectionModel().getSelectedItem();
        if (selectedMap == null) {
            return;
        }
        gameLoop.setMapFile(selectedMap);
        startMapChoicePane.setVisible(false);
        gameLoop.showGameScreen();
    }

    @FXML
    protected void onStartMapChoiceClose() {
        startMapChoicePane.setVisible(false);
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