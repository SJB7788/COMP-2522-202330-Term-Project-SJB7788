package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.GameLoop;
import com.example.comp2522202330termprojectsjb7788.interfaces.Block;
import com.example.comp2522202330termprojectsjb7788.interfaces.Item;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Stoneblock implements Block {
    private final int durability = 100;
    private final Rectangle block;
    private final ImageView stoneblockImage;
    private final int xAxis;
    private final int yAxis;

    public Stoneblock(int size, int xAxis, int yAxis) {
        this.block = new Rectangle(xAxis, yAxis, size, size);
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        block.setFill(Color.BLACK);
        stoneblockImage = new ImageView(new Image(GameLoop.class.getResource("stone.png").toString()));
        stoneblockImage.setFitWidth(size);
        stoneblockImage.setFitHeight(size);
        stoneblockImage.setX(xAxis);
        stoneblockImage.setY(yAxis);
    }

    @Override
    public Rectangle getBlock() {
        return block;
    }

    public ImageView getStoneblockImage() {
        return stoneblockImage;
    }

    @Override
    public String getBlockType() {
        return "Stoneblock";
    }

    public int getXAxis() {
        return xAxis;
    }

    public int getYAxis() {
        return yAxis;
    }
}
