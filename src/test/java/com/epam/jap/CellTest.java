package com.epam.jap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class CellTest {

    private Cell cell;
    Population currentPopulation;
    static Cell a = new Cell(true);
    static Cell d = new Cell(false);

    @BeforeMethod
    void setUp() {
        this.cell = new Cell();
        currentPopulation = new Population(new Cell[3][3]);
        a = new Cell(true);
        d = new Cell(false);
    }

    @Test
    public void returnsTrueIfAlive() {
        // given
        // when
        cell.revive();
        // then
        Assert.assertTrue(cell.state);
    }

    @Test
    public void returnsTrueIfDead() {
        // given
        // when
        cell.kill();
        // then
        Assert.assertFalse(cell.state);
    }

//    @Test
//    public void copiedCellsShouldEqualOriginal() {
//        // given
//        Cell[][] original = ALIVE_0_F;
//        // when
//        Cell[][] copied = currentPopulation.clone(original);
//        // then
//        assertTrue(Arrays.deepEquals(copied, original));
//    }

    @Test
    public void copiedCellsShouldDifferFromOriginal() {
        // given
        Cell[][] original = ALIVE_0_F;
        Cell[][] copy = original.clone();

//        Cell[][] copy = new Cell[original.length][original[0].length];
//        for (int i = 0; i < original.length; i++) {
//            for (int j = 0; j < original[0].length; j++) {
//                copy[i][i] = original[i][j];
//            }
//        }
        // when
        copy[1][1].kill();
        // then
        Assert.assertNotSame(copy[1][1].state,original[1][1].state);
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
