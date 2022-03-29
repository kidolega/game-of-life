package com.epam.jap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

public class GenerationTest {

    Generation currentGeneration;
    Generation futureGeneration;
    Generation pastGeneration;

    @BeforeMethod
    void setUp() {
        currentGeneration = new Generation(new Boolean[3][3]);
        futureGeneration = new Generation(new Boolean[3][3]);
        pastGeneration = new Generation(new Boolean[3][3]);
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

//    @Test (dataProvider = "cellsAndFriends")
//    public void expectedFriendsShouldEqualCellFriends(Boolean[][] cells, int friends) {
//        // given
//        currentGeneration.currentCells = cells;
//        // when
//        int counter = currentGeneration.countFriends(1, 1);
//        // then
//        assertEquals(friends, counter);
//    }
//
//    @Test(dataProvider = "shouldKillMidCell")
//    public void shouldKillCellInMiddle(Boolean[][] cells) {
//        // given
//        currentGeneration.currentCells = cells;
//        futureGeneration.currentCells = currentGeneration.currentCells;
//        // when
//        currentGeneration.evolveCell(1, 1);
//        boolean cell = futureGeneration.currentCells[1][1];
//        // then
//        assertFalse(cell);
//    }

    @Test(dataProvider = "shouldReviveMidCell")
    public void shouldReviveCellInMiddle(Boolean[][] cells) {
        // given
        currentGeneration = new Generation(cells);
        futureGeneration = new Generation(currentGeneration.currentCells);
        // when
        currentGeneration.evolveCell(1, 1);
        boolean cell = futureGeneration.currentCells[1][1];
        // then
        assertTrue(cell);
    }

    @Test(dataProvider = "shouldChangeStateAfterEvolve")
    public void worldShouldChange(Boolean[][] cells) {
        // given
        currentGeneration = new Generation(cells);
        futureGeneration = new Generation(currentGeneration.currentCells);
        // when
        currentGeneration.evolve();
        // then
        assertNotSame(futureGeneration, currentGeneration);
    }

    @Test(dataProvider = "shouldNotChangeStateAfterEvolve")
    public void worldShouldNotChange(Boolean[][] cells) {
        // given
        currentGeneration = new Generation(cells);
        futureGeneration = new Generation(currentGeneration.currentCells);
        // when
        currentGeneration.evolve();
        // then
        assertEquals(futureGeneration, currentGeneration);
    }

    @Test(dataProvider = "shouldChangeStateAfterEvolve")
    public void pastGenerationShouldEqualCurrent(Boolean[][] cells) {
        // given
        currentGeneration = new Generation(cells);
        // when
        currentGeneration.evolve();
        // then
        assertEquals(currentGeneration.currentCells[1][1], currentGeneration.originalCells[1][1]);
    }

    @Test
    public void shouldCopyCurrentGeneration() {
        // given
        currentGeneration.currentCells = ALIVE_2_F;
        // when
        futureGeneration = currentGeneration.clone();
        // then
        assertEquals(currentGeneration, futureGeneration);
    }

    @Test(dataProvider = "shouldChangeStateAfterEvolve")
    public void currentCellsShouldDifferFromOriginalAfterEvolve(Boolean[][] cells) {
        // given
        currentGeneration = new Generation(cells);
        // when
        currentGeneration.evolve();
        // then
        assertNotSame(currentGeneration.originalCells[1][1], currentGeneration.currentCells[1][1]);
    }
}
