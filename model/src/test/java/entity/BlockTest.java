package entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlockTest {

    Block block;

    @Before
    public void setUp() throws Exception {
        block = new Block(0,0,BlockType.ROCK, 0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getSprites() {
        assertEquals(block.getType(), BlockType.ROCK);
    }
}