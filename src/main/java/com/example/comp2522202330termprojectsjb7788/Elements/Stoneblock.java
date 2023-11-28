package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.interfaces.Block;
import com.example.comp2522202330termprojectsjb7788.interfaces.Item;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Stoneblock implements Block {
    private final int durability = 100;
    private final Rectangle block;
    private final int xAxis;
    private final int yAxis;

    public Stoneblock(int size, int xAxis, int yAxis) {
        this.block = new Rectangle(xAxis, yAxis, size, size);
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        block.setFill(Color.BLACK);
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public Rectangle getBlock() {
        return block;
    }
}
