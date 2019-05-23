package entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Map extends Entity {

    private int	id;
    private int height;
    private int lenght;
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
    public Map(final int id, final int lenght, final int height) {
        this.setId(id);
        this.lenght = lenght;
        this.height = height;
        schema = new String[height];

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
        this(0,0,0);
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int n_line) {
        this.height = n_line;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
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

    public Block[][] getBlocks() {
        return blocks;
    }

    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    public void setBlocks(int x, int y, Block block){
        this.blocks[x][y] = block;
    }

    public void setBlocksSize(int lenght, int height) {
        this.blocks = new Block[height][lenght];
    }
}
