package com.epam.jap;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WorldTest {

    private Game game;
    World world = new World();

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

    private static final Boolean[][] ALIVE_5_F= new Boolean[][]{
            {true, true, true},
            {true, true, false},
            {false, false, true}
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
    private static final Boolean[][] DEAD_5_F= new Boolean[][]{
            {true, true, true},
            {true, false, true},
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
    Object[][] shouldKillMidCell() {
        return new Object[][] {
                ALIVE_0_F,
                ALIVE_1_F,
                ALIVE_4_F,
                ALIVE_5_F
        };
    }

    @DataProvider
    Object[][] shouldReviveMidCell() {
        return new Object[][] {
                DEAD_3_F
        };
    }

    @Test
    public void counterShouldReturn0IfAliveCellHas0Friends() {
        // given
        world.cells = ALIVE_0_F;
        // when
        int counter = world.countFriends(1, 1);
        // then
        Assert.assertEquals(0, counter);
    }

    @Test
    public void counterShouldReturn1IfAliveCellHas1Friend() {
        // given
        world.cells = ALIVE_1_F;
        // when
        int counter = world.countFriends(1, 1);
        // then
        Assert.assertEquals(1, counter);
    }

    @Test
    public void counterShouldReturn5IfAliveCellHas5Friends() {
        // given
        world.cells = ALIVE_5_F;
        // when
        int counter = world.countFriends(1, 1);
        // then
        Assert.assertEquals(5, counter);
    }

    @Test
    public void counterShouldReturn0IfDeadCellHas0Friends() {
        // given
        world.cells = DEAD_0_F;
        // when
        int counter = world.countFriends(1, 1);
        // then
        Assert.assertEquals(0, counter);
    }

    @Test
    public void counterShouldReturn1IfDeadCellHas1Friend() {
        // given
        world.cells = DEAD_1_F;
        // when
        int counter = world.countFriends(1, 1);
        // then
        Assert.assertEquals(1, counter);
    }

    @Test
    public void counterShouldReturn5IfDeadCellHas5Friends() {
        // given
        world.cells = DEAD_5_F;
        // when
        int counter = world.countFriends(1, 1);
        // then
        Assert.assertEquals(5, counter);
    }

    @Test (dataProvider = "shouldKillMidCell")
    public void shouldKillCellInMiddle(Boolean[][] cells) {
        // given
        world.cells = cells;
        // when
        world.evolveCell(1, 1);
        boolean cell = cells[1][1];
        // then
        Assert.assertFalse(cell);
    }

    @Test(dataProvider = "shouldReviveMidCell")
    public void shouldReviveCellInMiddle(Boolean[][] cells) {
        // given
        world.cells = cells;
        // when
        world.evolveCell(1, 1);
        boolean cell = cells[1][1];
        // then
        Assert.assertTrue(cell);
    }
}
