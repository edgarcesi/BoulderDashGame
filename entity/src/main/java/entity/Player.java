package entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {
    private int posX, posY, score;

    private PlayerSprite frame;
    private BufferedImage spriteSheet;
    private Image[] sprites;


    public Player(final int x, final int y) {
        setPosX(x);
        setPosY(y);
        setScore(0);

        try {
            spriteSheet = ImageIO.read(new File("src/player.png"));
            sprites = new Image[4];
            // Perso à l'arrêt
            sprites[0] = spriteSheet.getSubimage(0*16, 0 * 16, 16, 16);
            //mort
            sprites[1] = spriteSheet.getSubimage(5*16, 5 * 16, 16, 16);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setFrame(PlayerSprite.NORMAL);
    }


    /**
     * Instantiates a new map.
     */
    public Player() {
        this(0,0);
    }

    /**
     * Getters.
     *
     * @return the id
     */
    public Image getPlayerSprites(int index) {
        return sprites[index];
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

    public void setSprites(Image[] sprites) {
        this.sprites = sprites;
    }

    public void setSprites(int index, Image spritesImg) {
        this.sprites[index] =  spritesImg;
    }

    public void setSpriteSize(int size){
        this.sprites = new Image[size];
    }

    public Image getSprites(int index) {
        return sprites[index];
    }

}
