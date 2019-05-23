package entity;

public class Block extends Map{
    private int posX, posY;
    private BlockType type;

    public Block(int x, int y, BlockType t){
        this.posX = x;
        this.posY = y;
        this.type = t;
    }


}
