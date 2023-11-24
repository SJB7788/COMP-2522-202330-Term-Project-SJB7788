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
    private final int xAxis;
    private final int yAxis;
    private final int size;

    public Berry(int itemAmount, int xAxis, int yAxis, int size) {
        this.itemAmount = itemAmount;
        this.berry = new Rectangle(xAxis, yAxis, size, size);
        berry.setFill(Color.YELLOW);
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.size = size;
    }

    public void placeBerry(Pane root) {
        root.getChildren().add(berry);
    }

    @Override
    public void useItem(SnowCharacter character) {
        character.collectBerry();
    }

    @Override
    public int getItemAmount() {
        return itemAmount;
    }


}
