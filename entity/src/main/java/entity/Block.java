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

    private Image emptySprite;
    private Image[] diamondSprites;
    private Image[] rockSprites = new Image[4];
    private Image[] endSprites;

    public Block(final int x, final int y) {
        frameIndex = 0;
        rockSprites = new Image[4];
        rockSprites[0] = getSpriteSheet().getSubimage(3 * 16,0 * 16, 16, 16);
        rockSprites[1] = getSpriteSheet().getSubimage(3 * 16,1 * 16, 16, 16);
        rockSprites[2] = getSpriteSheet().getSubimage(3 * 16,2 * 16, 16, 16);
        rockSprites[3] = getSpriteSheet().getSubimage(3 * 16,3 * 16, 16, 16);

        setPosX(x);
        setPosY(y);
}


    public Block(int x, int y, BlockType t){
        this.posX = x;
        this.posY = y;
        this.type = t;
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
                return rockSprites[0];
            case DIRT:
                return rockSprites[0];
            case EMPTY:
                return rockSprites[0];
            case ROCK:
                if(frameIndex>rockSprites.length-1) frameIndex = 0;
                return rockSprites[frameIndex];
            case DIAMOND:
                return rockSprites[0];
            case END:
                return rockSprites[0];
            case STAR:
                return rockSprites[0];
            default:
                return rockSprites[0];
        }
    }

}
