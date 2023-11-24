package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.interfaces.Block;
import com.example.comp2522202330termprojectsjb7788.interfaces.Item;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Snowblock implements Block {
    private final int durability;
    private final Item containsItem;
    private final Rectangle block;

    public Snowblock(int durability, int size, int xAxis, int yAxis, Item containsItem) {
        this.durability = durability;
        this.containsItem = containsItem;
        this.block = new Rectangle(xAxis, yAxis, size, size);
        block.setFill(Color.BLUE);
    }

    public void placeBlock(Pane root) {
        root.getChildren().add(block);
    }

    @Override
    public int getDurability() {
        return durability;
    }

    public boolean doesContainsItem() {
        return containsItem != null;
    }

    public Item getItem() {
        return containsItem;
    }

    public Rectangle getBlock() {
        return block;
    }
}
