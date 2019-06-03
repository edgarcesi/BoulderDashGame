package entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * <p>Map class.</p>
 *
 * @author tamed
 * @version $Id: $Id
 */
public class Map extends Entity {

    private int	id, height, length,startX,startY, endX, endY, diamond;
    private long time;
    private String[] schema;
    private BufferedImage spriteSheet;
    private Image[] sprites;

    private Block[][] blocks;

    /**
     * Instantiates a new hello map.
     *
     * @param id
     *          the id
     * @param height a int.
     * @param length a int.
     */
    public Map(final int id, final int height, final int length) {
        this.setId(id);
        this.length = length; // x
        this.height = height; // y
        this.time = 10;

        schema = new String[height];
        blocks = new Block[height][length];

        // fill sprites array
        try {
            spriteSheet = ImageIO.read(Map.class.getClassLoader().getResourceAsStream("level.png"));
            sprites = new Image[7];
            // Wall
            sprites[0] = spriteSheet.getSubimage(0*16, id*64 + 0 * 16, 16, 16);
            // Dirt
            sprites[1] = spriteSheet.getSubimage(1*16, id*64 + 0 * 16, 16, 16);
            // Empty
            sprites[2] = spriteSheet.getSubimage(2*16, id*64 + 0 * 16, 16, 16);
            // Rock
            sprites[3] = spriteSheet.getSubimage(3*16, id*64 + 0 * 16, 16, 16);
            // DIAMOND
            sprites[4] = spriteSheet.getSubimage(5*16, id*64 + 0 * 16, 16, 16);
            //END
            sprites[5] = spriteSheet.getSubimage(7*16, id*64 + 0 * 16, 16, 16);
            //STAR
            sprites[6] = spriteSheet.getSubimage(11*16, id*64 + 0 * 16, 16, 16);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Instantiates a new map.
     */
    public Map() {
        this(0,0,0);
    }


    /**
     * <p>isSolid.</p>
     *
     * @param x a int.
     * @param y a int.
     * @return a boolean.
     */
    public boolean isSolid(int x, int y){
        switch (blocks[y][x].getType()){
            case EMPTY:
                return false;
            case DIRT:
                return false;
            case DIAMOND:
                return false;
            case END:
                return false;
            case STAR:
                return false;
            case ROCK:
                return true;
            case WALL:
                return true;
            default:
                return true;
        }
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

    /**
     * <p>Getter for the field <code>height</code>.</p>
     *
     * @return a int.
     */
    public int getHeight() {
        return height;
    }

    /**
     * <p>Setter for the field <code>height</code>.</p>
     *
     * @param n_line a int.
     */
    public void setHeight(int n_line) {
        this.height = n_line;
    }

    /**
     * <p>getLenght.</p>
     *
     * @return a int.
     */
    public int getLenght() {
        return length;
    }

    /**
     * <p>setLenght.</p>
     *
     * @param lenght a int.
     */
    public void setLenght(int lenght) {
        this.length = lenght;
    }

    /**
     * Gets the schema.
     *
     * @return the schema
     * @param index a int.
     */
    public String getSchema(int index) {
        return this.schema[index];
    }

    /**
     * Sets the message.
     *
     * @param content
     *          the new message
     * @param index a int.
     */
    public void setSchema(final int index, final String content) {
        this.schema[index] = content;
    }

    /**
     * <p>Setter for the field <code>sprites</code>.</p>
     *
     * @param sprites an array of {@link java.awt.Image} objects.
     */
    public void setSprites(Image[] sprites) {
        this.sprites = sprites;
    }

    /**
     * <p>Setter for the field <code>sprites</code>.</p>
     *
     * @param index a int.
     * @param spritesImg a {@link java.awt.Image} object.
     */
    public void setSprites(int index, Image spritesImg) {
        this.sprites[index] =  spritesImg;
    }

    /**
     * <p>setSpriteSize.</p>
     *
     * @param size a int.
     */
    public void setSpriteSize(int size){
        this.sprites = new Image[size];
    }

    /**
     * <p>Getter for the field <code>sprites</code>.</p>
     *
     * @param index a int.
     * @return a {@link java.awt.Image} object.
     */
    public Image getSprites(int index) {
        return sprites[index];
    }

    /**
     * <p>Getter for the field <code>spriteSheet</code>.</p>
     *
     * @return a {@link java.awt.image.BufferedImage} object.
     */
    public BufferedImage getSpriteSheet(){return spriteSheet;
    }

    /**
     * <p>Getter for the field <code>blocks</code>.</p>
     *
     * @return an array of {@link entity.Block} objects.
     */
    public Block[][] getBlocks() {
        return blocks;
    }
    /**
     * <p>Getter for the field <code>blocks</code>.</p>
     *
     * @param x a int.
     * @param y a int.
     * @return a {@link entity.Block} object.
     */
    public Block getBlocks(int x, int y) {
        return blocks[y][x];
    }


    /**
     * <p>Setter for the field <code>blocks</code>.</p>
     *
     * @param blocks an array of {@link entity.Block} objects.
     */
    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    /**
     * <p>Setter for the field <code>blocks</code>.</p>
     *
     * @param y a int.
     * @param x a int.
     * @param block a {@link entity.Block} object.
     */
    public void setBlocks(int y, int x, Block block){
        this.blocks[y][x] = block;
    }

    /**
     * <p>setBlocksSize.</p>
     *
     * @param lenght a int.
     * @param height a int.
     */
    public void setBlocksSize(int lenght, int height) {
        this.blocks = new Block[height][lenght];
    }

    /**
     * <p>Getter for the field <code>startX</code>.</p>
     *
     * @return a int.
     */
    public int getStartX() {
        return startX;
    }

    /**
     * <p>Setter for the field <code>startX</code>.</p>
     *
     * @param startX a int.
     */
    public void setStartX(int startX) {
        this.startX = startX;
    }

    /**
     * <p>Getter for the field <code>startY</code>.</p>
     *
     * @return a int.
     */
    public int getStartY() {
        return startY;
    }

    /**
     * <p>Setter for the field <code>startY</code>.</p>
     *
     * @param startY a int.
     */
    public void setStartY(int startY) {
        this.startY = startY;
    }

    /**
     * <p>Getter for the field <code>endX</code>.</p>
     *
     * @return a int.
     */
    public int getEndX() {
        return endX;
    }

    /**
     * <p>Setter for the field <code>endX</code>.</p>
     *
     * @param endX a int.
     */
    public void setEndX(int endX) {
        this.endX = endX;
    }

    /**
     * <p>Getter for the field <code>endY</code>.</p>
     *
     * @return a int.
     */
    public int getEndY() {
        return endY;
    }

    /**
     * <p>Setter for the field <code>endY</code>.</p>
     *
     * @param endY a int.
     */
    public void setEndY(int endY) {
        this.endY = endY;
    }

    /**
     * <p>Getter for the field <code>diamond</code>.</p>
     *
     * @return a int.
     */
    public int getDiamond() {
        return diamond;
    }

    /**
     * <p>Setter for the field <code>diamond</code>.</p>
     *
     * @param diamond a int.
     */
    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    /**
     * <p>Getter for the field <code>time</code>.</p>
     *
     * @return a long.
     */
    public long getTime(){ return time;}
    /**
     * <p>Setter for the field <code>time</code>.</p>
     *
     * @param time a long.
     */
    public void setTime(long time){ this.time = time;}


    /**
     * <p>getBlockType.</p>
     *
     * @param x a int.
     * @param y a int.
     * @return a {@link entity.BlockType} object.
     */
    public BlockType getBlockType(int x, int y){
        return this.blocks[y][x].getType();
    }
    /**
     * <p>TransformToStar.</p>
     *
     * @param x a int.
     * @param y a int.
     */
    public void TransformToStar(int x, int y){
        getBlocks(x, y).setType(BlockType.STAR);
    }

    /**
     * <p>TypeCurrentBlock.</p>
     *
     * @param player a {@link entity.Player} object.
     * @return a {@link entity.BlockType} object.
     */
    public BlockType TypeCurrentBlock(Player player){
        return this.blocks[(player.getPosY()/16) - 1][(player.getPosX()/16)].getType();
    }
}
