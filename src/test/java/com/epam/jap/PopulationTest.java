package com.epam.jap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.AssertJUnit.*;

public class PopulationTest {

    Population population;
    static Cell a = new Cell(true);
    static Cell d = new Cell(false);

    @BeforeMethod
    void setUp() {
        population = new Population(new Cell[3][3]);
        a = new Cell(true);
        d = new Cell(false);
    }

    @Test (dataProvider = "cellsAndFriends")
    public void expectedFriendsShouldEqualCellFriends(Cell[][] cells, int friends) {
        // given
        population = new Population(cells);
        // when
        int counter = cells[1][1].countFriends(1, 1, population);
        // then
        assertEquals(friends, counter);
    }

    @Test(dataProvider = "shouldKillMidCell")
    public void shouldKillCellInMiddle(Cell[][] cells) {
        // given
        population = new Population(cells);
        // when
        population.currentGeneration[1][1].evolveCell(1, 1, population);
        boolean state = population.evolvedGeneration[1][1].state;
        // then
        assertFalse(state);
    }

    @Test(dataProvider = "shouldReviveMidCell")
    public void shouldReviveCellInMiddle(Cell[][] cells) {
        // given
        population = new Population(cells);
        // when
        population.currentGeneration[1][1].evolveCell(1, 1, population);
        boolean state = population.evolvedGeneration[1][1].state;
        // then
        assertTrue(state);
    }

    @Test(dataProvider = "shouldChangeStateAfterEvolve")
    public void worldShouldChange(Cell[][] cells) {
        // given
        population = new Population(cells);
        // when
        population.evolveGeneration();
        // then
        assertNotSame(population.originalGeneration, population.evolvedGeneration);
    }

    @Test(dataProvider = "shouldNotChangeStateAfterEvolve")
    public void worldShouldNotChange(Cell[][] cells) {
        // given
        population = new Population(cells);
        // when
        population.evolveGeneration();
        // then
        assertTrue(Arrays.deepEquals(population.originalGeneration, population.evolvedGeneration));
    }

    @Test(dataProvider = "shouldChangeStateAfterEvolve")
    public void pastGenerationShouldEqualCurrent(Cell[][] cells) {
        // given
        population = new Population(cells);
        // when
        population.evolveGeneration();
        population.evolvedGeneration[1][1].state = false;
        population.originalGeneration[1][1].state = true;
        // then
        assertNotSame(population.evolvedGeneration[1][1].state, population.originalGeneration[1][1].state);
    }

    @Test(dataProvider = "shouldChangeStateAfterEvolve")
    public void currentCellsShouldDifferFromOriginalAfterEvolve(Cell[][] cells) {
        // given
        Population population = new Population(cells);
        // when
        population.evolveGeneration();
        // then
        assertFalse(Arrays.deepEquals(population.evolvedGeneration, population.currentGeneration));
    }

    @Test(dataProvider = "shouldNotChangeStateAfterEvolve")
    public void currentCellsShouldEqualsOriginalAfterEvolve(Cell[][] cells) {
        // given
        Population population = new Population(cells);
        // when
        population.evolveGeneration();
        // then
        assertTrue(Arrays.deepEquals(population.evolvedGeneration, population.currentGeneration));
    }

    @Test
    public void shouldCloneGeneration() {
        // given
        // when
        Population clonedPopulation = population;
        // then
        assertEquals(population, clonedPopulation);
    }

    @Test
    public void shouldCloneGenerationCells() {
        // given
        Population clonedPopulation = new Population(new Cell[3][3]);
        // when
        clonedPopulation.evolvedGeneration = population.evolvedGeneration.clone();
        // then
        assertEquals(population.evolvedGeneration, clonedPopulation.evolvedGeneration);
    }

    @Test
    public void copiedCellsShouldEqualOriginal() {
        // given
        Cell[][] original = ALIVE_0_F;
        // when
        Cell[][] copied = population.clone(original);
        copied[1][1].kill();
        // then
        assertTrue(Arrays.deepEquals(copied, original));
    }

    @Test
    public void copiedCellsShouldDifferFromOriginal() {
        // given
        Cell[][] original = ALIVE_0_F;
        Cell[][] copied = population.clone(original);
        // when
        copied[1][1].kill();
        // then
        assertFalse(Arrays.deepEquals(copied, original));
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
