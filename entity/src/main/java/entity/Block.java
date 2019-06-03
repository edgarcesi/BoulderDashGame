package entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static entity.BlockType.ROCK;

/**
 * <p>Block class.</p>
 *
 * @author tamed
 * @version $Id: $Id
 */
public class Block extends Map{
    private int posX, posY, frameIndex;
    private BlockType type;
    private boolean falling;

    private Image[] diamondSprites;
    private Image[] rockSprites;
    private Image[] endSprites;
    private Image[] emptySprite;
    private Image[] starSprite;
    private Image wallSprite;
    private Image[] dirtSprite;


    /**
     * <p>Constructor for Block.</p>
     *
     * @param x a int.
     * @param y a int.
     * @param t a {@link entity.BlockType} object.
     */
    public Block(int x, int y, BlockType t, int MapID){
        MapID--;
        this.posX = x;
        this.posY = y;
        this.type = t;

        frameIndex = 0;
        // MapID*64 permet de sauter au groupe de texture suivant
        rockSprites = new Image[4];
        rockSprites[0] = getSpriteSheet().getSubimage(3 * 16,MapID*64 + 0 * 16, 16, 16);
        rockSprites[1] = getSpriteSheet().getSubimage(3 * 16,MapID*64 + 1 * 16, 16, 16);
        rockSprites[2] = getSpriteSheet().getSubimage(3 * 16,MapID*64 + 2 * 16, 16, 16);
        rockSprites[3] = getSpriteSheet().getSubimage(3 * 16,MapID*64 + 3 * 16, 16, 16);

        diamondSprites = new Image[4];
        diamondSprites[0] = getSpriteSheet().getSubimage(4 * 16,MapID*64 + 0 * 16, 16, 16);
        diamondSprites[1] = getSpriteSheet().getSubimage(4 * 16,MapID*64 + 1 * 16, 16, 16);
        diamondSprites[2] = getSpriteSheet().getSubimage(4 * 16,MapID*64 + 2 * 16, 16, 16);
        diamondSprites[3] = getSpriteSheet().getSubimage(4 * 16,MapID*64 + 3 * 16, 16, 16);

        endSprites = new Image[4];
        endSprites[0] = getSpriteSheet().getSubimage(7 * 16,MapID*64 + 0 * 16, 16, 16);
        endSprites[1] = getSpriteSheet().getSubimage(7 * 16,MapID*64 + 1 * 16, 16, 16);
        endSprites[2] = getSpriteSheet().getSubimage(7 * 16,MapID*64 + 2 * 16, 16, 16);
        endSprites[3] = getSpriteSheet().getSubimage(7 * 16,MapID*64 + 3 * 16, 16, 16);

        wallSprite = getSpriteSheet().getSubimage(0 * 16, MapID*64 + 0*16, 16, 16);

        starSprite = new Image[4];
        starSprite[0] = getSpriteSheet().getSubimage(11 * 16, MapID*64 + 0*16, 16, 16);
        starSprite[1] = getSpriteSheet().getSubimage(11 * 16, MapID*64 + 1*16, 16, 16);
        starSprite[2] = getSpriteSheet().getSubimage(11 * 16, MapID*64 + 2*16, 16, 16);
        starSprite[3] = getSpriteSheet().getSubimage(11 * 16, MapID*64 + 3*16, 16, 16);

        emptySprite = new Image[4];
        emptySprite[0] = getSpriteSheet().getSubimage(2 * 16, MapID*64 + 0*16, 16, 16);
        emptySprite[1] = getSpriteSheet().getSubimage(2 * 16, MapID*64 + 1*16, 16, 16);
        emptySprite[2] = getSpriteSheet().getSubimage(2 * 16, MapID*64 + 2*16, 16, 16);
        emptySprite[3] = getSpriteSheet().getSubimage(2 * 16, MapID*64 + 3*16, 16, 16);

        dirtSprite = new Image[4];
        dirtSprite[0] = getSpriteSheet().getSubimage(1 * 16, MapID*64 + 0*16, 16, 16);
        dirtSprite[1] = getSpriteSheet().getSubimage(1 * 16, MapID*64 + 1*16, 16, 16);
        dirtSprite[2] = getSpriteSheet().getSubimage(1 * 16, MapID*64 + 2*16, 16, 16);
        dirtSprite[3] = getSpriteSheet().getSubimage(1 * 16, MapID*64 + 3*16, 16, 16);
    }

    /**
     * <p>Getter for the field <code>posX</code>.</p>
     *
     * @return a int.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * <p>Setter for the field <code>posX</code>.</p>
     *
     * @param posX a int.
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * <p>Getter for the field <code>posY</code>.</p>
     *
     * @return a int.
     */
    public int getPosY() {
        return posY;
    }

    /**
     * <p>Setter for the field <code>posY</code>.</p>
     *
     * @param posY a int.
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * <p>Getter for the field <code>type</code>.</p>
     *
     * @return a {@link entity.BlockType} object.
     */
    public BlockType getType() {
        return type;
    }

    /**
     * <p>Setter for the field <code>type</code>.</p>
     *
     * @param type a {@link entity.BlockType} object.
     */
    public void setType(BlockType type) {
        this.type = type;
    }

    /**
     * <p>isFalling.</p>
     *
     * @return a boolean.
     */
    public boolean isFalling() {
        return falling;
    }

    /**
     * <p>Setter for the field <code>falling</code>.</p>
     *
     * @param falling a boolean.
     */
    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    /**
     * <p>getSprites.</p>
     *
     * @return a {@link java.awt.Image} object.
     */
    public Image getSprites() {
        frameIndex++;
        switch (this.type){
            case WALL:
                return wallSprite;
            case DIRT:
                if(frameIndex>rockSprites.length-1) frameIndex = 0;
                return dirtSprite[frameIndex];
            case EMPTY:
                if(frameIndex>rockSprites.length-1) frameIndex = 0;
                return emptySprite[frameIndex];
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
                if(frameIndex>rockSprites.length-1) frameIndex = 0;
                return starSprite[frameIndex];
            default:
                return wallSprite;
        }
    }
}
