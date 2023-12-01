package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.interfaces.Block;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class StartingBlock implements Block {
    Rectangle block;

    public StartingBlock(int x, int y) {
        this.block = new Rectangle(x, y, 40, 40);
        this.block.setFill(Color.WHITE);
    }

    @Override
    public Rectangle getBlock() {
        return block;
    }

    @Override
    public String getBlockType() {
        return "StartingBlock";
    }
}
