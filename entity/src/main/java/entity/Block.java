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
    private Image[] rockSprites;
    private Image[] endSprites;
    private Image emptySprite;
    private Image starSprite;
    private Image wallSprite;
    private Image dirtSprite;


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

        wallSprite = getSpriteSheet().getSubimage(0 * 16, 0*16, 16, 16);

        starSprite = getSpriteSheet().getSubimage(11 * 16, 0*16, 16, 16);

        emptySprite = getSpriteSheet().getSubimage(2 * 16, 0*16, 16, 16);

        dirtSprite = getSpriteSheet().getSubimage(1 * 16, 0*16, 16, 16);
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
                return wallSprite;
            case DIRT:
                return dirtSprite;
            case EMPTY:
                return emptySprite;
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
                return starSprite;
            default:
                return wallSprite;
        }
    }
}
