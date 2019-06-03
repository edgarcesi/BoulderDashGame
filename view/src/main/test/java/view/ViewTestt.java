package view;

import model.Model;

import static org.junit.Assert.*;

public class ViewTestt {

    @org.junit.Before
    public void setUp() throws Exception {
        final Model model = new Model();
        View v = new View(model);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void printMessage() {
        fail("Not yet implemented");
    }

    @org.junit.Test
    public void run() {
        fail("Not yet implemented");
    }

    @org.junit.Test
    public void playWinMusic() {
        fail("Not yet implemented");
    }

    @org.junit.Test
    public void playGameoverMusic() {
        fail("Not yet implemented");
    }
}