package com.example.comp2522202330termprojectsjb7788.Elements;

import com.example.comp2522202330termprojectsjb7788.GameLoop;
import com.example.comp2522202330termprojectsjb7788.interfaces.Block;
import com.example.comp2522202330termprojectsjb7788.interfaces.Item;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BerryBlocks implements Block {
    private final Rectangle berryBlock;
    private ImageView berryBlockImage;
    private int durability = 1;
    private final Item berry;
    private final int x;
    private final int y;

    public BerryBlocks(int x, int y) {
        this.x = x;
        this.y = y;
        Rectangle berryBlock = new Rectangle(x, y, 40, 40);
        berryBlock.setFill(Color.PURPLE);
        this.berryBlock = berryBlock;

        Berry berry = new Berry(1, x + 5, y + 5, 20);
        this.berry = berry;

        berryBlockImage = new ImageView(new Image(GameLoop.class.getResource("wood.png").toString()));
        berryBlockImage.setFitWidth(40);
        berryBlockImage.setFitHeight(40);
        berryBlockImage.setX(x);
        berryBlockImage.setY(y);
    }

    public ImageView getBerryBlockImage() {
        return berryBlockImage;
    }

    public Item getBerry() {
        return berry;
    }

    public void decreaseDurability(int durability) {
        this.durability -= durability;
        if (this.durability <= 0) {
            berryBlock.setX(-50);
            berryBlock.setY(-50);
            berry.placeItem((Pane) berryBlockImage.getParent());
        }
    }

    @Override
    public Rectangle getBlock() {
        return berryBlock;
    }

    @Override
    public String getBlockType() {
        return "BerryBlock";
    }
}
