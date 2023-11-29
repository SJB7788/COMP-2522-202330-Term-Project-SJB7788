package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.interfaces.Block;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Finishblock implements Block {
    private final Rectangle block;
    private boolean isShown;
    private final int xAxis;
    private final int yAxis;

    Finishblock(int size, int xAxis, int yAxis) {
        this.block = new Rectangle(xAxis, yAxis, size, size);
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.isShown = false;
        block.setFill(Color.ANTIQUEWHITE);
    }

    @Override
    public Rectangle getBlock() {
        return block;
    }

    @Override
    public String getBlockType() {
        return "Finishblock";
    }

    public void showFinishBlock() {
        block.setFill(Color.GOLD);
        this.isShown = true;
    }

    public boolean isShown() {
        return isShown;
    }

    public int getXAxis() {
        return xAxis;
    }

    public int getYAxis() {
        return yAxis;
    }
}
