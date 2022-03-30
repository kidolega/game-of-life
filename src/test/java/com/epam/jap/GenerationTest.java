package com.epam.jap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.AssertJUnit.*;

public class GenerationTest {

    Generation currentGeneration;

    @BeforeMethod
    void setUp() {
        currentGeneration = new Generation(new Boolean[3][3]);
    }

    @Test (dataProvider = "cellsAndFriends")
    public void expectedFriendsShouldEqualCellFriends(Boolean[][] cells, int friends) {
        // given
        currentGeneration = new Generation(cells);
        // when
        int counter = currentGeneration.countFriends(1, 1);
        // then
        assertEquals(friends, counter);
    }

    @Test(dataProvider = "shouldKillMidCell")
    public void shouldKillCellInMiddle(Boolean[][] cells) {
        // given
        currentGeneration = new Generation(cells);
        // when
        currentGeneration.evolveCell(1, 1);
        boolean cell = currentGeneration.currentCells[1][1];
        // then
        assertFalse(cell);
    }

    @Test(dataProvider = "shouldReviveMidCell")
    public void shouldReviveCellInMiddle(Boolean[][] cells) {
        // given
        currentGeneration = new Generation(cells);
        // when
        currentGeneration.evolveCell(1, 1);
        boolean cell = currentGeneration.currentCells[1][1];
        // then
        assertTrue(cell);
    }

    @Test(dataProvider = "shouldChangeStateAfterEvolve")
    public void worldShouldChange(Boolean[][] cells) {
        // given
        currentGeneration = new Generation(cells);
        // when
        currentGeneration.evolve();
        // then
        assertNotSame(currentGeneration.pastCells, currentGeneration.currentCells);
    }

    @Test(dataProvider = "shouldNotChangeStateAfterEvolve")
    public void worldShouldNotChange(Boolean[][] cells) {
        // given
        currentGeneration = new Generation(cells);
        // when
        currentGeneration.evolve();
        // then
        assertTrue(Arrays.deepEquals(currentGeneration.pastCells, currentGeneration.currentCells));
    }

    @Test(dataProvider = "shouldChangeStateAfterEvolve")
    public void pastGenerationShouldEqualCurrent(Boolean[][] cells) {
        // given
        currentGeneration = new Generation(cells);
        // when
        currentGeneration.evolve();
        // then
        assertNotSame(currentGeneration.currentCells[1][1], currentGeneration.pastCells[1][1]);
    }

    @Test(dataProvider = "shouldChangeStateAfterEvolve")
    public void currentCellsShouldDifferFromOriginalAfterEvolve(Boolean[][] cells) {
        // given
        Generation generation = new Generation(cells);
        // when
        generation.evolve();
        // then
        assertFalse(Arrays.deepEquals(generation.currentCells, generation.pastCells));
    }

    @Test(dataProvider = "shouldNotChangeStateAfterEvolve")
    public void currentCellsShouldEqualsOriginalAfterEvolve(Boolean[][] cells) {
        // given
        Generation generation = new Generation(cells);
        // when
        generation.evolve();
        // then
        assertTrue(Arrays.deepEquals(generation.currentCells, generation.originalCells));
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
}
