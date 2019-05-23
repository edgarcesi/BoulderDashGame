package entity;

public class Block extends Map{
    private int posX, posY;
    private BlockType type;

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
}
