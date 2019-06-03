package entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlockTest {
    private Block block = new Block(16,16,BlockType.ROCK, 1);

    @Test
    public void getPosX() {
        assertEquals(block.getPosX(), 16);
    }

    @Test
    public void getPosY() {
        assertEquals(block.getPosY(), 16);
    }

    @Test
    public void getType() {
        assertEquals(block.getType(), BlockType.ROCK);
    }

    @Test
    public void isFalling() {
        assertFalse(block.isFalling());
    }
}