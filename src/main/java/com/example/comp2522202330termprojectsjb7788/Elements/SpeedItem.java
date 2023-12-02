package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.interfaces.Item;
import com.example.comp2522202330termprojectsjb7788.interfaces.SnowCharacter;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SpeedItem implements Item {
    private final int itemAmount;
    private final Rectangle speedItem;

    public SpeedItem(int itemAmount, int xAxis, int yAxis, int size) {
        this.itemAmount = itemAmount;
        this.speedItem = new Rectangle(xAxis, yAxis, size, size);
        speedItem.setFill(Color.CYAN);
    }

    @Override
    public int getItemAmount() {
        return itemAmount;
    }

    @Override
    public void useItem(SnowCharacter character) {
        character.setSpeed(character.getSpeed() + 5);
        speedItem.setY(-50);
        speedItem.setX(-50);
    }

    @Override
    public void placeItem(Pane root) {
        root.getChildren().add(speedItem);
    }

    @Override
    public Rectangle getItemRect() {
        return speedItem;
    }
}
