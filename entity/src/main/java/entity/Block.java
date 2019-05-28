package entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Block extends Map{
    private int posX, posY, frameIndex;
    private BlockType type;

    private PlayerSprite frame;
    private BufferedImage spriteSheet;

    private Image[] diamondSprites;
    private Image[] rockSprites;
    private Image[] endSprites;

    public Block(final int x, final int y) throws IOException {
        try {
            frameIndex = 0;
            spriteSheet = ImageIO.read(new File("src/level.png"));
            diamondSprites = new Image[2];
            diamondSprites[0] = spriteSheet.getSubimage(4 * 16, 0 * 16, 16, 16);
            diamondSprites[1] = spriteSheet.getSubimage(5 * 16, 0 * 16, 16, 16);

        }
     catch (IOException e) {
        e.printStackTrace();
    }
        setPosX(x);
        setPosY(y);
}


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
