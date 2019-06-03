package entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest {
    Map map = new Map(1, 1,3);
    Block blockTest = new Block(0,0,BlockType.DIRT, 1);
    @Before
    public void setUp() throws Exception {
        int x=0;
        int y=0;
        for (int i = 0; i<3; i++){
            Block block = new Block(x*16,y*16,BlockType.DIRT, 1);
            map.setBlocks(y, x, block);
            x++;
        }
    }

    @Test
    public void isSolid() {
        assertFalse(map.isSolid(0,0));
    }

    @Test
    public void getId() {
        assertEquals(map.getId(), 1);
    }

    @Test
    public void getHeight() {
        assertEquals(map.getHeight(), 1);
    }

    @Test
    public void getLenght() {
        assertEquals(map.getLenght(), 3);
    }


    @Test
    public void getBlocks() {
        assertNotNull(map.getBlocks(0,0));
    }

    @Test
    public void getBlockType() {
        assertEquals(map.getBlockType(0,0), BlockType.DIRT);
    }
}