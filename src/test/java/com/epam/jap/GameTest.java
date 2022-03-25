package com.epam.jap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.epam.jap.Game.createCellsCopy;

public class GameTest {

    private World world;
    private Printer printer;
    private Game game;

    static ByteArrayOutputStream outContent;

    @BeforeMethod
    public void setUp() {
        world = new World(3, 3);
        printer = new Printer(System.out);
        game = new Game(world, printer);
        outContent = new ByteArrayOutputStream();
    }

    @Test
    public void copiedCellsEqualsOriginalCells() {
        // given
        world.cells = new Boolean[][] {
                {false, false, false},
                {false, true, false},
                {false, false, false}
        };
        Boolean[][] cellsCopy;
        // when
        cellsCopy = createCellsCopy(world.cells);
        // then
        Assert.assertEquals(cellsCopy, world.cells);
    }

    @Test
    public void shouldPrintGame() {
        // given
        printer = new Printer(new PrintStream(outContent));
        // when
        game.play();
        // then
        Assert.assertNotNull(outContent.toString());
    }
}
