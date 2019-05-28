package entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static entity.BlockType.ROCK;

public class Block extends Map{
    private int posX, posY, frameIndex;
    private BlockType type;


    private Image[] diamondSprites;
    private Image[] rockSprites = new Image[4];
    private Image[] endSprites;
    private Image[] emptySprites;
    private Image[] starSprites;
    private Image[] wallSprites;
    private Image[] dirtSprites;


    public Block(int x, int y, BlockType t){
        this.posX = x;
        this.posY = y;
        this.type = t;


        frameIndex = 0;
        rockSprites = new Image[4];
        rockSprites[0] = getSpriteSheet().getSubimage(3 * 16,0 * 16, 16, 16);
        rockSprites[1] = getSpriteSheet().getSubimage(3 * 16,1 * 16, 16, 16);
        rockSprites[2] = getSpriteSheet().getSubimage(3 * 16,2 * 16, 16, 16);
        rockSprites[3] = getSpriteSheet().getSubimage(3 * 16,3 * 16, 16, 16);

        diamondSprites = new Image[4];
        diamondSprites[0] = getSpriteSheet().getSubimage(4 * 16,0 * 16, 16, 16);
        diamondSprites[1] = getSpriteSheet().getSubimage(4 * 16,1 * 16, 16, 16);
        diamondSprites[2] = getSpriteSheet().getSubimage(4 * 16,2 * 16, 16, 16);
        diamondSprites[3] = getSpriteSheet().getSubimage(4 * 16,3 * 16, 16, 16);

        endSprites = new Image[4];
        endSprites[0] = getSpriteSheet().getSubimage(7 * 16,0 * 16, 16, 16);
        endSprites[1] = getSpriteSheet().getSubimage(7 * 16,1 * 16, 16, 16);
        endSprites[2] = getSpriteSheet().getSubimage(7 * 16,2 * 16, 16, 16);
        endSprites[3] = getSpriteSheet().getSubimage(7 * 16,3 * 16, 16, 16);

        wallSprites = new Image[0];

        starSprites = new Image[0];

        emptySprites = new Image[0];

        dirtSprites = new Image[0];

        setPosX(x);
        setPosY(y);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

    public Image getSprites() {
        frameIndex++;
        switch (this.type){
            case WALL:
                return wallSprites[0];
            case DIRT:
                return dirtSprites[0];
            case EMPTY:
                return emptySprites[0];
            case ROCK:
                if(frameIndex>rockSprites.length-1) frameIndex = 0;
                return rockSprites[frameIndex];
            case DIAMOND:
                if(frameIndex>rockSprites.length-1) frameIndex = 0;
                return diamondSprites[frameIndex];
            case END:
                if(frameIndex>rockSprites.length-1) frameIndex = 0;
                return endSprites[frameIndex];
            case STAR:
                return starSprites[0];
            default:
                return rockSprites[0];
        }
    }

}
