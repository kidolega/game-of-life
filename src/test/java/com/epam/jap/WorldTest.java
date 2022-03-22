package com.epam.jap;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WorldTest {

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
        World world = new World(height, width, cells);
        // when
        World evolved = world.evolve();
        // then
        assertNotEquals(world, evolved);
    }

    @Test(dataProvider = "onlyDeadCells")
    public void worldWithAllDeadCellShouldNotChange(String state) {
        // given
        Boolean[][] cells = toState(state);
        int height = cells.length;
        int width = cells[0].length;
        World world = new World(height, width, cells);
        // when
        World evolved = world.evolve();
        // then
        assertEquals(world, evolved);
    }

    @Test
    public void worldBeforeShouldNotEqualEvolvedWorld() {
        // given
        Boolean[][] cellsBefore = new Boolean[1][1];
        cellsBefore[0][0] = true;
        World worldBefore = new World(1, 1, cellsBefore);
        // when
        World worldEvolved = worldBefore.evolve();
        // then
        Assert.assertNotEquals(worldBefore, worldEvolved);
    }


}
