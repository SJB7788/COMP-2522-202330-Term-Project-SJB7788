package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.GameLoop;
import com.example.comp2522202330termprojectsjb7788.interfaces.Item;
import com.example.comp2522202330termprojectsjb7788.interfaces.SnowCharacter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SpeedItem implements Item {
    private final int itemAmount;
    private final Rectangle speedItem;
    private final ImageView speedItemImage;

    public SpeedItem(int itemAmount, int xAxis, int yAxis, int size) {
        this.itemAmount = itemAmount;
        this.speedItem = new Rectangle(xAxis, yAxis, size, size);

        speedItemImage = new ImageView(new Image(GameLoop.class.getResource("boots.png").toString()));
        speedItemImage.setFitWidth(30);
        speedItemImage.setFitHeight(30);
        speedItemImage.setX(xAxis);
        speedItemImage.setY(yAxis);
    }

    @Override
    public int getItemAmount() {
        return itemAmount;
    }

    @Override
    public void useItem(SnowCharacter character) {
        character.incSpeed();
        speedItem.setY(-50);
        speedItem.setX(-50);
        speedItemImage.setY(-50);
        speedItemImage.setX(-50);
    }

    @Override
    public void placeItem(Pane root) {
        root.getChildren().add(speedItemImage);
    }

    @Override
    public Rectangle getItemRect() {
        return speedItem;
    }
}
