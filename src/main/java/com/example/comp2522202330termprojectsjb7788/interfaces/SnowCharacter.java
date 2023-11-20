package com.example.comp2522202330termprojectsjb7788.interfaces;

import javafx.scene.layout.Pane;

public interface SnowCharacter extends Character {
    void collectSnow();
    void throwSnow();
    void rollSnow();
    void placeSnowBomb(Pane root);
    void chargeSnow();
    void collectBerry();
}
