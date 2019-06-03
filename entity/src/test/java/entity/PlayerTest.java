package entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    Player p1 = new Player(0,0);

    @Test
    public void getPosX() {
        assertEquals(p1.getPosX(), 0);

    }

    @Test
    public void getPosY() {
        assertEquals(p1.getPosY(), 0);
    }

    @Test
    public void getScore() {
        assertEquals(p1.getScore(), 0);
    }
}