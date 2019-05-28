package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelTest {
    private Model model;

    @Before
    public void setUp() throws Exception {
        model = new Model();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void realPos() {
        int expected = 95;
        assertEquals(expected, model.RealPos(6));
    }

    @Test
    public void indexPos() {
        int expected = 4;
        assertEquals(expected, model.IndexPos(60));
        }
}