package com.epam.jap;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.epam.jap.Game.world;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WorldTest {

    private static final Boolean[][] ALIVE_0_F= new Boolean[][]{
            {false, false, false},
            {false, true, false},
            {false, false, false}
    };

    private static final Boolean[][] ALIVE_1_F= new Boolean[][]{
            {true, false, false},
            {false, true, false},
            {false, false, false}
    };

    private static final Boolean[][] ALIVE_2_F= new Boolean[][]{
            {true, true, false},
            {false, true, false},
            {false, false, false}
    };

    private static final Boolean[][] ALIVE_3_F= new Boolean[][]{
            {true, true, true},
            {false, true, false},
            {false, false, false}
    };

    private static final Boolean[][] ALIVE_4_F= new Boolean[][]{
            {true, true, true},
            {true, true, false},
            {false, false, false}
    };

    private static final Boolean[][] DEAD_0_F= new Boolean[][]{
            {false, false, false},
            {false, false, false},
            {false, false, false}
    };
    private static final Boolean[][] DEAD_1_F= new Boolean[][]{
            {true, false, false},
            {false, false, false},
            {false, false, false}
    };
    private static final Boolean[][] DEAD_2_F= new Boolean[][]{
            {true, true, false},
            {false, false, false},
            {false, false, false}
    };
    private static final Boolean[][] DEAD_3_F= new Boolean[][]{
            {true, true, true},
            {false, false, false},
            {false, false, false}
    };
    private static final Boolean[][] DEAD_4_F= new Boolean[][]{
            {true, true, true},
            {true, false, false},
            {false, false, false}
    };

    @DataProvider
    Object[][] shouldNotChangeState() {
        return new Object[][] {
                ALIVE_2_F,
                ALIVE_3_F,
                DEAD_0_F,
                DEAD_1_F,
                DEAD_2_F,
                DEAD_4_F,

        };
    }

    @DataProvider
    Object[][] shouldChangeState() {
        return new Object[][] {
                ALIVE_0_F,
                ALIVE_1_F,
                ALIVE_4_F,
                DEAD_3_F,

        };
    }

    @Test
    public void counterShouldReturn0IfAliveCellHas0Friends() {
        // given
        Boolean[][] cells = new Boolean[][] {
                {false, false, false},
                {false, true, false},
                {false, false, false},
        };
        // when
        int counter = world.countFriends(1, 1, cells);
        // then
        Assert.assertEquals(0, counter);
    }

    @Test
    public void counterShouldReturn0IfDeadCellHas0Friends() {
        // given
        Boolean[][] cells = new Boolean[][] {
                {false, false, false},
                {false, false, false},
                {false, false, false},
        };
        // when
        int counter = world.countFriends(1, 1, cells);
        // then
        Assert.assertEquals(0, counter);
    }

    @Test
    public void counterShouldReturn1IfAliveCellHas1Friend() {
        // given
        Boolean[][] cells = new Boolean[][] {
                {true, false, false},
                {false, true, false},
                {false, false, false},
        };
        // when
        int counter = world.countFriends(1, 1, cells);
        // then
        Assert.assertEquals(1, counter);
    }

    @Test
    public void counterShouldReturn5IfAliveCellHas5Friend() {
        // given
        Boolean[][] cells = new Boolean[][] {
                {true, true, true},
                {true, true, true},
                {false, false, false},
        };
        // when
        int counter = world.countFriends(1, 1, cells);
        // then
        Assert.assertEquals(5, counter);
    }

    @Test
    public void counterShouldReturn1IfDeadCellHas1Friend() {
        // given
        Boolean[][] cells = new Boolean[][] {
                {true, false, false},
                {false, false, false},
                {false, false, false},
        };
        // when
        int counter = world.countFriends(1, 1, cells);
        // then
        Assert.assertEquals(1, counter);
    }

    @Test (dataProvider = "shouldChangeState")
    public void shouldKillCellInMiddle() {
        // given
        Boolean[][] cells = new Boolean[][] {
                {false, false, false},
                {false, true, true},
                {false, false, false},
        };
        // when
        world.evolveCell(1, 1, cells);
        boolean cell = cells[1][1];
        // then
        Assert.assertFalse(cell);
    }

    @Test
    public void shouldKillCellInMiddleOverPopulated() {
        // given
        Boolean[][] cells = new Boolean[][] {
                {true, true, true},
                {true, true, true},
                {false, false, false},
        };
        // when
        world.evolveCell(1, 1, cells);
        boolean cell = cells[1][1];
        // then
        Assert.assertFalse(cell);
    }

    @Test
    public void shouldResurrectCellInMiddle() {
        // given
        Boolean[][] cells = new Boolean[][] {
                {true, true, true},
                {false, true, false},
                {false, false, false},
        };
        // when
        world.evolveCell(1, 1, cells);
        boolean cell = cells[1][1];
        // then
        Assert.assertTrue(cell);
    }
}
