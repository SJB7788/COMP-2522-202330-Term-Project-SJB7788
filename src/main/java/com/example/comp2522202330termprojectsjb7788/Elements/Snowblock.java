package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.interfaces.Block;
import com.example.comp2522202330termprojectsjb7788.interfaces.Item;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Snowblock implements Block {
    private int durability;
    private Item item;
    private final Rectangle block;
    private int xAxis;
    private int yAxis;

    public Snowblock(int durability, int size, int xAxis, int yAxis) {
        this.durability = durability;
        this.block = new Rectangle(xAxis, yAxis, size, size);
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        block.setFill(Color.BLUE);
    }
    public boolean doesContainsItem() {
        return item != null;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public Rectangle getBlock() {
        return block;
    }

    @Override
    public String getBlockType() {
        return "Snowblock";
    }

    public void decreaseDurability(int durability) {
        this.durability -= durability;
        if (this.durability <= 0) {
            block.setX(-50);
            block.setY(-50);
            if (doesContainsItem()) {
                item.placeItem((Pane) block.getParent());
            }
        }
    }

    public void addItem(Item item) {
        this.item = item;
    }

    public int getXAxis() {
        return xAxis;
    }

    public int getYAxis() {
        return yAxis;
    }

    public int getDurability() {
        return durability;
    }
}
