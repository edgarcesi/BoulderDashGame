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
            frameIndex = 0;
            spriteSheet = ImageIO.read(new File("src/player.png"));
            idleSprites = new Image[2];
            idleSprites[0] = spriteSheet.getSubimage(0*16, 0 * 16, 16, 16);
            idleSprites[1] = spriteSheet.getSubimage(3*16, 0 * 16, 16, 16);
            leftSprites = new Image[3];
            leftSprites[0] = spriteSheet.getSubimage(0*16, 1*16, 16,16);
            leftSprites[1] = spriteSheet.getSubimage(1*16, 1*16, 16,16);
            leftSprites[2] = spriteSheet.getSubimage(2*16, 1*16, 16,16);
            rightSprites = new Image[3];
            rightSprites[0] = spriteSheet.getSubimage(0*16, 3*16, 16,16);
            rightSprites[1] = spriteSheet.getSubimage(1*16, 3*16, 16,16);
            rightSprites[2] = spriteSheet.getSubimage(2*16, 3*16, 16,16);
            upSprites = new Image[4];
            upSprites[0] = spriteSheet.getSubimage(0*16,2*16,16,16);
            upSprites[1] = spriteSheet.getSubimage(1*16,2*16,16,16);
            upSprites[2] = spriteSheet.getSubimage(2*16,2*16,16,16);
            upSprites[3] = spriteSheet.getSubimage(3*16,2*16,16,16);
            downSprites = new Image[4];
            downSprites[0] = spriteSheet.getSubimage(0*16, 4*16, 16, 16);
            downSprites[1] = spriteSheet.getSubimage(1*16, 4*16, 16, 16);
            downSprites[2] = spriteSheet.getSubimage(2*16, 4*16, 16, 16);
            downSprites[3] = spriteSheet.getSubimage(3*16, 4*16, 16, 16);
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
        frameIndex++;
        switch (frame){
            case IDLE:
                if(frameIndex>idleSprites.length-1) frameIndex = 0;
                return idleSprites[frameIndex];
            case LEFT:
                if(frameIndex>leftSprites.length-1) frameIndex = 0;
                return leftSprites[frameIndex];
            case RIGHT:
                if(frameIndex>rightSprites.length-1) frameIndex = 0;
                return rightSprites[frameIndex];
            case UP:
                if(frameIndex>rightSprites.length-1) frameIndex = 0;
                return upSprites[frameIndex];
            case DOWN:
                if(frameIndex>downSprites.length-1) frameIndex = 0;
                return downSprites[frameIndex];
             default:
                return idleSprites[0];
        }
    }


}
