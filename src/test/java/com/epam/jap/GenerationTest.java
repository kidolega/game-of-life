package com.epam.jap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.AssertJUnit.*;

public class GenerationTest {

    Generation currentGeneration;
    static Cell a = new Cell(true);
    static Cell d = new Cell(false);

    @BeforeMethod
    void setUp() {
        currentGeneration = new Generation(new Cell[3][3]);
        a = new Cell(true);
        d = new Cell(false);
    }

    @Test (dataProvider = "cellsAndFriends")
    public void expectedFriendsShouldEqualCellFriends(Cell[][] cells, int friends) {
        // given
        currentGeneration = new Generation(cells);
        // when
        int counter = currentGeneration.countFriends(1, 1);
        // then
        assertEquals(friends, counter);
    }

    @Test(dataProvider = "shouldKillMidCell")
    public void shouldKillCellInMiddle(Cell[][] cells) {
        // given
        currentGeneration = new Generation(cells);
        // when
        currentGeneration.currentCells.evolveCell(1, 1);
        boolean state = currentGeneration.evolvedCells[1][1].state;
        // then
        assertFalse(state);
    }

    @Test(dataProvider = "shouldReviveMidCell")
    public void shouldReviveCellInMiddle(Cell[][] cells) {
        // given
        currentGeneration = new Generation(cells);
        // when
        currentGeneration.evolveCell(1, 1);
        boolean state = currentGeneration.evolvedCells[1][1].state;
        // then
        assertTrue(state);
    }

    @Test(dataProvider = "shouldChangeStateAfterEvolve")
    public void worldShouldChange(Cell[][] cells) {
        // given
        currentGeneration = new Generation(cells);
        // when
        currentGeneration.evolve();
        // then
        assertNotSame(currentGeneration.originalCells, currentGeneration.evolvedCells);
    }

    @Test(dataProvider = "shouldNotChangeStateAfterEvolve")
    public void worldShouldNotChange(Cell[][] cells) {
        // given
        currentGeneration = new Generation(cells);
        // when
        currentGeneration.evolve();
        // then
        assertTrue(Arrays.deepEquals(currentGeneration.originalCells, currentGeneration.evolvedCells));
    }

    @Test(dataProvider = "shouldChangeStateAfterEvolve")
    public void pastGenerationShouldEqualCurrent(Cell[][] cells) {
        // given
        currentGeneration = new Generation(cells);
        // when
        currentGeneration.evolve();
        // then
        assertNotSame(currentGeneration.evolvedCells[1][1].state, currentGeneration.originalCells[1][1].state);
    }

    @Test(dataProvider = "shouldChangeStateAfterEvolve")
    public void currentCellsShouldDifferFromOriginalAfterEvolve(Cell[][] cells) {
        // given
        Generation generation = new Generation(cells);
        // when
        generation.evolve();
        // then
        assertFalse(Arrays.deepEquals(generation.evolvedCells, generation.originalCells));
    }

    @Test(dataProvider = "shouldNotChangeStateAfterEvolve")
    public void currentCellsShouldEqualsOriginalAfterEvolve(Cell[][] cells) {
        // given
        Generation generation = new Generation(cells);
        // when
        generation.evolve();
        // then
        assertTrue(Arrays.deepEquals(generation.evolvedCells, generation.currentCells));
    }

    @Test
    public void shouldCloneGeneration() {
        // given
        // when
        Generation clonedGeneration = currentGeneration.clone();
        // then
        Assert.assertEquals(currentGeneration, clonedGeneration);
    }

    @Test
    public void shouldCloneGenerationCells() {
        // given
        Generation clonedGeneration = new Generation(new Cell[3][3]);
        // when
        clonedGeneration.evolvedCells = currentGeneration.evolvedCells.clone();
        // then
        Assert.assertEquals(currentGeneration.evolvedCells, clonedGeneration.evolvedCells);
    }

    private static final Cell[][] ALIVE_0_F = new Cell[][]{
            {d, d, d},
            {d, a, d},
            {d, d, d}
    };

    private static final Cell[][] ALIVE_1_F = new Cell[][]{
            {a, d, d},
            {d, a, d},
            {d, d, d}
    };

    private static final Cell[][] ALIVE_2_F = new Cell[][]{
            {a, a, d},
            {d, a, d},
            {d, d, d}
    };

    private static final Cell[][] ALIVE_3_F = new Cell[][]{
            {a, a, a},
            {d, a, d},
            {d, d, d}
    };

    private static final Cell[][] ALIVE_4_F = new Cell[][]{
            {a, a, a},
            {a, a, d},
            {d, d, d}
    };

    private static final Cell[][] ALIVE_5_F = new Cell[][]{
            {a, a, a},
            {a, a, d},
            {d, d, a}
    };

    private static final Cell[][] DEAD_0_F = new Cell[][]{
            {d, d, d},
            {d, d, d},
            {d, d, d}
    };
    private static final Cell[][] DEAD_1_F = new Cell[][]{
            {a, d, d},
            {d, d, d},
            {d, d, d}
    };
    private static final Cell[][] DEAD_2_F = new Cell[][]{
            {a, a, d},
            {d, d, d},
            {d, d, d}
    };
    private static final Cell[][] DEAD_3_F = new Cell[][]{
            {a, a, a},
            {d, d, d},
            {d, d, d}
    };
    private static final Cell[][] DEAD_4_F = new Cell[][]{
            {a, a, a},
            {a, d, d},
            {d, d, d}
    };
    private static final Cell[][] DEAD_5_F = new Cell[][]{
            {a, a, a},
            {a, d, a},
            {d, d, d}
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
