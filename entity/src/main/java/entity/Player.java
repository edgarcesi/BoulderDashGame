package entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {
    private int posX, posY, score, frameIndex;

    private PlayerSprite frame;
    private BufferedImage spriteSheet;

    private Image[] idleSprites;
    private Image[] leftSprites;
    private Image[] rightSprites;
    private Image[] upSprites;
    private Image[] downSprites;


    public Player(final int x, final int y) {
        try {
            spriteSheet = ImageIO.read(new File("src/player.png"));

            idleSprites = new Image[4];
            idleSprites[0] = spriteSheet.getSubimage(0*16, 0 * 16, 16, 16);
            leftSprites = new Image[1];
            leftSprites[0] = spriteSheet.getSubimage(1*16, 0*16, 16,16);
            rightSprites = new Image[1];
            rightSprites[0] = spriteSheet.getSubimage(3*16, 0*16, 16,16);
            upSprites = new Image[1];
            upSprites[0] = spriteSheet.getSubimage(5*16,2*16,16,16);
            downSprites = new Image[1];
            downSprites[0] = spriteSheet.getSubimage(4*16, 1*16, 16, 16);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setFrame(PlayerSprite.IDLE);

        setPosX(x);
        setPosY(y);
        setScore(0);
    }


    /**
     * Instantiates a new map.
     */
    public Player() {
        this(0,0);
    }


    public PlayerSprite getFrame() {
        return frame;
    }

    public void setFrame(PlayerSprite frame) {
        this.frame = frame;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void IncrementScore(int amount){
        setScore(getScore() + amount);
    }

    public Image getSprites() {
        switch (frame){
            case IDLE:
                if(idleSprites.length<=frameIndex) frameIndex = 0;
                return idleSprites[frameIndex];
            case LEFT:
                if(leftSprites.length<=frameIndex) frameIndex = 0;
                return leftSprites[frameIndex];
            case RIGHT:
                if(rightSprites.length<=frameIndex) frameIndex = 0;
                return rightSprites[frameIndex];
            case UP:
                if(upSprites.length<=frameIndex) frameIndex = 0;
                return upSprites[frameIndex];
            case DOWN:
                if(downSprites.length<=frameIndex) frameIndex = 0;
                return downSprites[frameIndex];
        }
        return idleSprites[0];
    }


}
