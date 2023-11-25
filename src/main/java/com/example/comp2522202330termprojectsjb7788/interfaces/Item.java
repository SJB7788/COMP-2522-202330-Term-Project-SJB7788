package com.example.comp2522202330termprojectsjb7788.interfaces;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public interface Item {

    int getItemAmount();
    void useItem(SnowCharacter character);
    void placeItem(Pane root);
    Rectangle getItemRect();
}
