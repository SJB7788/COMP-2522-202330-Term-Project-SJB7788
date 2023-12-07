package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.GameLoop;
import com.example.comp2522202330termprojectsjb7788.interfaces.Block;
import com.example.comp2522202330termprojectsjb7788.interfaces.Item;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;

public class Snowblock implements Block {
    private int durability;
    private Item item;
    private final Rectangle block;;
    private ImageView snowblockImage;
    private AnimationTimer animationTimer;
    private final int OUTOFBOUNDS = -50;
    private int xAxis;
    private int yAxis;

    public Snowblock(int durability, int size, int xAxis, int yAxis) {
        this.durability = durability;
        this.block = new Rectangle(xAxis, yAxis, size, size);
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        block.setFill(Color.BLUE);
        snowblockImage = new ImageView(new Image(GameLoop.class.getResource("ice block.png").toString()));
        snowblockImage.setFitWidth(size);
        snowblockImage.setFitHeight(size);
        snowblockImage.setX(xAxis);
        snowblockImage.setY(yAxis);
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
            block.setX(OUTOFBOUNDS);
            block.setY(OUTOFBOUNDS);
            snowblockImage.setX(OUTOFBOUNDS);
            snowblockImage.setY(OUTOFBOUNDS);
            if (doesContainsItem()) {
                item.placeItem((Pane) snowblockImage.getParent());
            }
        }
    }

    public void setBlockImageTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                snowblockImage.setY(block.getY());
                snowblockImage.setX(block.getX());
            }
        };

        animationTimer.start();
    }

    private void stopBlockImageTimer() {
        animationTimer.stop();
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

    public ImageView getSnowblockImage() {
        return snowblockImage;
    }
}
