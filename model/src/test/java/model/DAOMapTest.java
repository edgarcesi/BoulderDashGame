package model;

import entity.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DAOMapTest {

    private DAOMap daoMap;

    @Before
    public void setUp() throws Exception {
        this.daoMap = new DAOMap(DBConnection.getInstance().getConnection());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void create() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }

    @Test
    public void find() {
        final int expectedLength = 1;
        final Map map = daoMap.find(1);
        assertEquals(expectedLength, map.getLenght());
    }

    @Test
    public void mapInfo() {
    }
}