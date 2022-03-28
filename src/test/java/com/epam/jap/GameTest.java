package com.epam.jap;

import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayOutputStream;

import static java.lang.System.out;

public class GameTest {

    private World world;
    private Printer printer;
    private Game game;
    static ByteArrayOutputStream outContent;

    @BeforeMethod
    public void setUp() {
        world = new World(0, 0);
        printer = new Printer(out);
        game = new Game(world, printer);
        outContent = new ByteArrayOutputStream();
    }

//    @Test
//    public void copiedCellsEqualsOriginalCells() {
//        // given
//        world.cells = new Boolean[][] {
//                {false, false, false},
//                {false, true, false},
//                {false, false, false}
//        };
//        Boolean[][] cellsCopy;
//        // when
//        cellsCopy = createCellsCopy(world.cells);
//        // then
//        Assert.assertEquals(cellsCopy, world.cells);
//    }
//
//    @Test
//    public void shouldPrintGame() {
//        // given
//        printer = new Printer(new PrintStream(outContent));
//        // when
//        game.play();
//        // then
//        Assert.assertNotNull(outContent.toString());
//    }
//
//    @Test
//    public void passIfReturnedWorldEqualsCurrent() {
//        // given
//        world.initializeWorld();
//        Boolean[][] cellsCopy = createCellsCopy(world.cells);
//        // when
//        printer.printCurrentWorld(game, world);
//        // then
//        Assert.assertEquals(world.futureCells, world.cells);
//    }
}
