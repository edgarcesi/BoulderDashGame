package entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
     */
    public Map(final int id, final int height, final int length) {
        this.setId(id);
        this.length = length; // x
        this.height = height; // y
        this.time = 100;

        schema = new String[height];
        blocks = new Block[height][length];

        // fill sprites array
        try {
            spriteSheet = ImageIO.read(new File("src/level.png"));
            sprites = new Image[7];
            // Wall
            sprites[0] = spriteSheet.getSubimage(0*16, id*64 + 0 * 16, 16, 16);
            // Dirt
            sprites[1] = spriteSheet.getSubimage(1*16, id*64 + 0 * 16, 32, 16);
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
        return length;
    }

    public void setLenght(int lenght) {
        this.length = lenght;
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
     * @param content
     *          the new message
     */
    public void setSchema(final int index, final String content) {
        this.schema[index] = content;
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
    public Block getBlocks(int x, int y) {
        return blocks[y][x];
    }


    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    public void setBlocks(int y, int x, Block block){
        this.blocks[y][x] = block;
    }

    public void setBlocksSize(int lenght, int height) {
        this.blocks = new Block[height][lenght];
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public long getTime(){ return time;}
    public void setTime(int time){ this.time = time;}

    public int CoordoneeYNextBlock(Player player, String orientation){
        switch (orientation){
            case "UP":
                return (player.getPosY()) - 16;
            case "DOWN":
                return (player.getPosY()) + 16;
            case "LEFT":
                return (player.getPosY());
            case "RIGHT":
                return (player.getPosY());
            case "NOW":
                return (player.getPosY());
            default:
                return 0;
        }
    }

    public int CoordoneeXNextBlock(Player player, String orientation){
        switch (orientation){
            case "UP":
                return (player.getPosX());
            case "DOWN":
                return (player.getPosX());
            case "LEFT":
                return (player.getPosX() - 16);
            case "RIGHT":
                return (player.getPosX()) + 16;
            case "NOW":
                return (player.getPosX());
            default:
                return 0;
        }
    }

    public BlockType getBlockType(int x, int y){
        return this.blocks[y][x].getType();
    }
    public void TransformToDirt(int x, int y){
        getBlocks(x, y).setType(BlockType.EMPTY);
    }
    public void TransformToRock(int x, int y){
        getBlocks(x, y).setType(BlockType.ROCK);
    }
    public void TransformToStar(int x, int y){
        getBlocks(x, y).setType(BlockType.STAR);
    }
    public BlockType TypeCurrentBlock(Player player){
        return this.blocks[(player.getPosY()/16) - 1][(player.getPosX()/16)].getType();
    }
}
