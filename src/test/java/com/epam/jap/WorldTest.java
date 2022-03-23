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

    private static final String DEAD_1x1_ALL_DEAD = "_"; // -> DEAD_1x1
    private static final String DEAD_1x2_ALL_DEAD = "__"; // -> DEAD_1x2
    private static final String ALIVE_1x1 = "X"; // -> DEAD_1x1
    private static final String ALIVE_1x2_1 = "X_"; // -> Dead_1x2
    private static final String ALIVE_1x2_2 = "_X"; // -> Dead_1x2
    private static final String ALIVE_1x2_ALL_ALIVE = "XX"; // -> Dead_1x2
    private static final String ALIVE_2x1_1 =
            """
                    X
                    X
                    """; // -> Dead_2x1
    private static final String ALIVE_2x2_1 =
            """
                    _X
                    X_
                    """; // -> Dead_2x2
    private static final String ALIVE_2x2_2 =
            """
                    XX
                    X_
                    """; // -> Alive_2x2
    private static final String ALIVE_2x2_3 =
            """
                    X_
                    __
                    """; // -> DEAD_2x2
    private static final String ALIVE_2x2_ALL_ALIVE =
            """
                    XX
                    XX
                    """; // -> ALIVE_2x2
    private static final String ALIVE_2x2_ALL_DEAD =
            """
                    XX
                    XX
                    """; // -> ALIVE_2x2

    @DataProvider
    Object[][] only1Alive() {
        return new Object[][] {
                {ALIVE_1x1},
                {ALIVE_1x2_1},
                {ALIVE_1x2_2},
                {ALIVE_2x2_3}
        };
    }

    @DataProvider
    Object[][] onlyDeadCells() {
        return new Object[][] {
                {DEAD_1x1_ALL_DEAD},
                {DEAD_1x2_ALL_DEAD},
                {ALIVE_2x2_ALL_DEAD},
        };
    }

    @DataProvider
    Object[][] shouldNotChange() {
        return new Object[][]{
                {DEAD_1x1_ALL_DEAD},
                {DEAD_1x2_ALL_DEAD},
                {ALIVE_2x2_ALL_DEAD},
                {ALIVE_2x2_ALL_ALIVE}
        };
    }

    @DataProvider
    Object[][] shouldChange() {
        return new Object[][]{
                {ALIVE_1x1},
                {ALIVE_1x2_1},
                {ALIVE_1x2_2},
                {ALIVE_1x2_ALL_ALIVE},
                {ALIVE_2x1_1},
                {ALIVE_2x2_3},
                {ALIVE_2x2_1}
        };
    }

    private Boolean[][] toArray(String cells) {
        String[] rows = cells.split("\n");
        return Arrays.stream(rows)
                .map(this::toRow)
                .toArray(Boolean[][]::new);
    }

    private Boolean[] toRow(String r) {
        return r.chars().mapToObj(c -> c == 'X')
                .toArray(Boolean[]::new);
    }

    private Boolean[][] toState(String state) {
        return toArray(state);
    }

    @Test(dataProvider = "only1Alive")
    public void worldWith1AliveCellShouldChange(String state) {
        // given
        Boolean[][] cells = toState(state);
        int height = cells.length;
        int width = cells[0].length;
        World world = new World(height, width);
        // when
        World evolved = world.evolve();
        // then
        assertNotEquals(world, evolved);
    }

//    @Test(dataProvider = "onlyDeadCells")
//    public void worldWithAllDeadCellShouldNotChange(String state) {
//        // given
//        Boolean[][] cells = toState(state);
//        int height = cells.length;
//        int width = cells[0].length;
//        World world = new World(height, width);
//        // when
//        world.evolve();
//        // then
//        assertEquals(world,);
//    }

    @Test(dataProvider = "shouldChangeState")
    public void worldShouldChangeAfterEvolve(Boolean[][] state) {
        // given
        Boolean[][] cells = state;
        World newWorld = world;
        // when
        world.evolve();
        // then
        assertNotEquals(newWorld, world);
    }

    @Test(dataProvider = "shouldChangeState")
    public void counterShouldReturn0IfAliveCellHas0Friends(Boolean[][] state) {
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
