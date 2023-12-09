package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.GameLoop;
import com.example.comp2522202330termprojectsjb7788.interfaces.Block;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Finishblock implements Block {
    private final Rectangle block;
    private final ImageView finishblockImage;
    private boolean isShown;
    private final int xAxis;
    private final int yAxis;

    Finishblock(int size, int xAxis, int yAxis) {
        this.block = new Rectangle(xAxis, yAxis, size, size);
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.isShown = false;
        block.setFill(Color.ANTIQUEWHITE);

        finishblockImage = new ImageView(new Image(GameLoop.class.getResource("Gold_Block.png").toString()));
        finishblockImage.setFitWidth(size);
        finishblockImage.setFitHeight(size);
        finishblockImage.setX(-50);
        finishblockImage.setY(-50);
    }

    @Override
    public Rectangle getBlock() {
        return block;
    }

    public ImageView getFinishblockImage() {
        return finishblockImage;
    }

    @Override
    public String getBlockType() {
        return "Finishblock";
    }

    public void showFinishBlock() {
        block.setFill(Color.GOLD);
        finishblockImage.setX(xAxis);
        finishblockImage.setY(yAxis);
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
