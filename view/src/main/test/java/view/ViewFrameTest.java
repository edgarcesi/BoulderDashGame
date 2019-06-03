package view;

import model.Model;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewFrameTest {
    private ViewFrame f;
    private Model model = new Model();
    @Before
    public void setUp() throws Exception {
        this.f = new ViewFrame(model);
    }

    @Test
    public void getModel() {
        assertEquals(model, this.f.getModel());
    }

}