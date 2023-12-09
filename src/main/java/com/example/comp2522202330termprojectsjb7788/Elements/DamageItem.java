package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.GameLoop;
import com.example.comp2522202330termprojectsjb7788.interfaces.Item;
import com.example.comp2522202330termprojectsjb7788.interfaces.SnowCharacter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DamageItem implements Item {
    private final int itemAmount;

    private final Rectangle damageItem;
    private final ImageView damageItemImage;

    public DamageItem(int itemAmount, int xAxis, int yAxis, int size) {
        this.itemAmount = itemAmount;
        this.damageItem = new Rectangle(xAxis, yAxis, size, size);

        damageItemImage = new ImageView(new Image(GameLoop.class.getResource("potion.png").toString()));
        damageItemImage.setFitWidth(30);
        damageItemImage.setFitHeight(30);
        damageItemImage.setX(xAxis);
        damageItemImage.setY(yAxis);
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
        damageItemImage.setY(-50);
        damageItemImage.setX(-50);
    }

    @Override
    public void placeItem(Pane root) {
        root.getChildren().add(damageItemImage);
    }

    @Override
    public Rectangle getItemRect() {
        return damageItem;
    }
}
