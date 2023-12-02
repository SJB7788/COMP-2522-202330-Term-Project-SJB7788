package com.example.comp2522202330termprojectsjb7788.interfaces;

import com.example.comp2522202330termprojectsjb7788.Elements.Bomb;
import javafx.scene.layout.Pane;

public interface SnowCharacter extends Character {
    void collectSnow();
    void throwSnow();
    void rollSnow();
    Bomb placeSnowBomb(Pane root);
    int chargeSnow(Pane root);
    void collectBerry();

    void setSpeed(int speed);
    void setDamage(int damage);
    int getSpeed();
    int getDamage();
}
