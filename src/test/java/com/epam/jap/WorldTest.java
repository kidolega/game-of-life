package com.epam.jap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

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

//    @Test (dataProvider = "cellsAndFriends")
//    public void expectedFriendsShouldEqualCellFriends(Boolean[][] cells, int friends) {
//        // given
//        world.pastGeneration.cells = cells;
//        // when
//        int counter = world.countFriends(1, 1);
//        // then
//        assertEquals(friends, counter);
//    }


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
