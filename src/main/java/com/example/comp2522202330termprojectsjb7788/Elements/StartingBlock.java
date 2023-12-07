package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.GameLoop;
import com.example.comp2522202330termprojectsjb7788.interfaces.Block;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class StartingBlock implements Block {
    Rectangle block;
    ImageView startingBlockImage;

    public StartingBlock(int x, int y) {
        this.block = new Rectangle(x, y, 40, 40);
        this.block.setFill(Color.WHITE);
        startingBlockImage = new ImageView(new Image(GameLoop.class.getResource("Blue_Glow_Block2.png").toString()));
        startingBlockImage.setFitWidth(40);
        startingBlockImage.setFitHeight(40);
        startingBlockImage.setX(x);
        startingBlockImage.setY(y);
    }

    @Override
    public Rectangle getBlock() {
        return block;
    }

    public ImageView getStartingBlockImage() {
        return startingBlockImage;
    }

    @Override
    public String getBlockType() {
        return "StartingBlock";
    }
}
