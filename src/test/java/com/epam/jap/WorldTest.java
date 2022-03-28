package com.epam.jap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.epam.jap.Game.createCellsCopy;
import static org.testng.AssertJUnit.*;

public class WorldTest {

    private World world;
    private Printer printer;
    private Game game;

    @BeforeMethod
    public void setUp() {
        world = new World(3, 3);
        printer = new Printer(System.out);
        game = new Game(world, printer);
    }

    private static final Boolean[][] ALIVE_0_F = new Boolean[][]{
            {false, false, false},
            {false, true, false},
            {false, false, false}
    };

    private static final Boolean[][] ALIVE_1_F = new Boolean[][]{
            {true, false, false},
            {false, true, false},
            {false, false, false}
    };

    private static final Boolean[][] ALIVE_2_F = new Boolean[][]{
            {true, true, false},
            {false, true, false},
            {false, false, false}
    };

    private static final Boolean[][] ALIVE_3_F = new Boolean[][]{
            {true, true, true},
            {false, true, false},
            {false, false, false}
    };

    private static final Boolean[][] ALIVE_4_F = new Boolean[][]{
            {true, true, true},
            {true, true, false},
            {false, false, false}
    };

    private static final Boolean[][] ALIVE_5_F = new Boolean[][]{
            {true, true, true},
            {true, true, false},
            {false, false, true}
    };

    private static final Boolean[][] DEAD_0_F = new Boolean[][]{
            {false, false, false},
            {false, false, false},
            {false, false, false}
    };
    private static final Boolean[][] DEAD_1_F = new Boolean[][]{
            {true, false, false},
            {false, false, false},
            {false, false, false}
    };
    private static final Boolean[][] DEAD_2_F = new Boolean[][]{
            {true, true, false},
            {false, false, false},
            {false, false, false}
    };
    private static final Boolean[][] DEAD_3_F = new Boolean[][]{
            {true, true, true},
            {false, false, false},
            {false, false, false}
    };
    private static final Boolean[][] DEAD_4_F = new Boolean[][]{
            {true, true, true},
            {true, false, false},
            {false, false, false}
    };
    private static final Boolean[][] DEAD_5_F = new Boolean[][]{
            {true, true, true},
            {true, false, true},
            {false, false, false}
    };


    @DataProvider
    Object[][] shouldNotChangeStateAfterEvolve() {
        return new Object[][]{
                ALIVE_2_F,
                ALIVE_3_F,
                DEAD_0_F,
                DEAD_1_F,
                DEAD_2_F,
                DEAD_4_F,
                DEAD_5_F
        };
    }

    @DataProvider
    Object[][] shouldChangeStateAfterEvolve() {
        return new Object[][]{
                ALIVE_0_F,
                ALIVE_1_F,
                ALIVE_4_F,
                ALIVE_5_F,
                DEAD_3_F,
        };
    }


    @DataProvider
    Object[][] shouldKillMidCell() {
        return new Object[][]{
                ALIVE_0_F,
                ALIVE_1_F,
                ALIVE_4_F,
                ALIVE_5_F
        };
    }

    @DataProvider
    Object[][] shouldReviveMidCell() {
        return new Object[][]{
                DEAD_3_F
        };
    }

    @DataProvider
    Object[][] cellsAndFriends() {
        return new Object[][] {
                {ALIVE_0_F, 0},
                {ALIVE_1_F, 1},
                {ALIVE_2_F, 2},
                {ALIVE_3_F, 3},
                {ALIVE_5_F, 5},
                {DEAD_0_F, 0},
                {DEAD_1_F, 1},
                {DEAD_2_F, 2},
                {DEAD_3_F, 3},
                {DEAD_5_F, 5},
        };
    }

    @Test (dataProvider = "cellsAndFriends")
    public void expectedFriendsShouldEqualCellFriends(Boolean[][] cells, int friends) {
        // given
        world.pastGeneration = world.currentGeneration.clone();
        // when
        int counter = world.countFriends(1, 1);
        // then
        assertEquals(friends, counter);
    }

//    @Test(dataProvider = "shouldKillMidCell")
//    public void shouldKillCellInMiddle(Boolean[][] cells) {
//        // given
//        world.cells = createCellsCopy(cells);
//        // when
//        world.evolveCell(1, 1);
//        boolean cell = world.futureCells[1][1];
//        // then
//        assertFalse(cell);
//    }
//
//    @Test(dataProvider = "shouldReviveMidCell")
//    public void shouldReviveCellInMiddle(Boolean[][] cells) {
//        // given
//        world.cells = createCellsCopy(cells);
//        // when
//        world.evolveCell(1, 1);
//        boolean cell = world.cells[1][1];
//        // then
//        assertTrue(cell);
//    }
//
//    @Test(dataProvider = "shouldChangeStateAfterEvolve")
//    public void worldShouldChange(Boolean[][] cells) {
//        // given
//        world.cells = createCellsCopy(cells);
//        world.futureCells = createCellsCopy(world.cells);
//        Boolean[][] tempCells = createCellsCopy(cells);
//        // when
//        world.evolveWorld();
//        // then
//        assertFalse(game.compareCurrentCellsWithEvolved(tempCells));
//    }
//
//    @Test(dataProvider = "shouldNotChangeStateAfterEvolve")
//    public void worldShouldNotChange(Boolean[][] cells) {
//        // given
//        world.cells = cells;
//        Boolean[][] tempCells = createCellsCopy(cells);
//        world.futureCells = createCellsCopy(world.cells);
//
//        // when
//        world.evolveWorld();
//        // then
//        assertTrue(game.compareCurrentCellsWithEvolved(tempCells));
//    }
//
//    @Test
//    public void shouldPassIfCreatedWorldIsNotEmpty() {
//        // given
//        World world = new World(3, 3);
//        // when
//        world.initializeWorld();
//        // then
//        Assert.assertNotNull(world.cells[1][1]);
//    }
}
