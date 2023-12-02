package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.interfaces.Item;
import com.example.comp2522202330termprojectsjb7788.interfaces.SnowCharacter;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DamageItem implements Item {
    private final int itemAmount;

    private final Rectangle damageItem;

    public DamageItem(int itemAmount, int xAxis, int yAxis, int size) {
        this.itemAmount = itemAmount;
        this.damageItem = new Rectangle(xAxis, yAxis, size, size);
        damageItem.setFill(Color.SILVER);
    }

    @Override
    public int getItemAmount() {
        return itemAmount;
    }

    @Override
    public void useItem(SnowCharacter character) {
        character.setDamage(character.getDamage() + 4);
        damageItem.setY(-50);
        damageItem.setX(-50);
    }

    @Override
    public void placeItem(Pane root) {
        root.getChildren().add(damageItem);
    }

    @Override
    public Rectangle getItemRect() {
        return damageItem;
    }
}
