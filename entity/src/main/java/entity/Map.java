package entity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Map extends Entity {

    private int	id;
    private int nline;
    private String[] schema;
    private BufferedImage spriteSheet;
    private Image[] sprites;

    private Block[][] blocks;

    /**
     * Instantiates a new hello map.
     *
     * @param id
     *          the id
     */
    public Map(final int id, final int n_line) {
        this.setId(id);
        this.nline = n_line;
        schema = new String[n_line];

        /** ? Entity or Model ? **/
        try {
            spriteSheet = ImageIO.read(new File("src/level.png"));
            sprites = new Image[4];
            // Wall
            sprites[0] = spriteSheet.getSubimage(0*16, 0 * 16, 16, 16);
            // Dirt
            sprites[1] = spriteSheet.getSubimage(1*16, 0 * 16, 32, 16);
            // Empty
            sprites[2] = spriteSheet.getSubimage(2*16, 0 * 16, 16, 16);
            // Rock
            sprites[3] = spriteSheet.getSubimage(3*16, 0 * 16, 16, 16);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /** ?------------------? **/
    }

    /**
     * Instantiates a new map.
     */
    public Map() {
        this(0,0);
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *          the new id
     */
    public void setId(final int id) {
        this.id = id;
    }

    public int getNline() {
        return nline;
    }

    public void setNline(int n_line) {
        this.nline = n_line;
    }

    /**
     * Gets the schema.
     *
     * @return the schema
     */
    public String getSchema(int index) {
        return this.schema[index];
    }

    /**
     * Sets the message.
     *
     * @param schema
     *          the new message
     */
    public void setSchema(final int index, final String schema) {
        this.schema[index] = schema;
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
