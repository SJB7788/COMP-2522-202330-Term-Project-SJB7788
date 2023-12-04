package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.interfaces.Character;
import com.example.comp2522202330termprojectsjb7788.interfaces.Item;
import com.example.comp2522202330termprojectsjb7788.interfaces.SnowCharacter;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Berry implements Item {
    private final int itemAmount;
    private final Rectangle berry;
    private int xAxis;
    private int yAxis;

    public Berry(int itemAmount, int xAxis, int yAxis, int size) {
        this.itemAmount = itemAmount;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.berry = new Rectangle(-150, -150, size, size);
        berry.setFill(Color.YELLOW);
    }

    private void setBerryLocation() {
        berry.setX(xAxis);
        berry.setY(yAxis);
    }

    @Override
    public void placeItem(Pane root) {
        setBerryLocation();
        root.getChildren().add(berry);
    }

    @Override
    public void useItem(SnowCharacter character) {
        character.collectBerry();
        berry.setY(-50);
        berry.setX(-50);
    }

    @Override
    public int getItemAmount() {
        return itemAmount;
    }

    @Override
    public Rectangle getItemRect() {
        return berry;
    }

}
