package com.epam.jap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.lang.System.out;

@Test
public class PrinterTest {

    static World world;
    static ByteArrayOutputStream outContent;
    static Printer printer;
    private Game game;

    @BeforeMethod
    private void setUp() {
        world = new World(3, 3);
        outContent = new ByteArrayOutputStream();
        printer = new Printer(new PrintStream(outContent));
        game = new Game(world, printer);
        world.generation = new Generation(new Boolean[3][3]);
    }

    public void shouldPrintAliveCell() {
        // given
        world.generation.evolvedCells[1][1] = true;
        // when
        printer.printCell(1, 1, world);
        //then
        Assert.assertEquals(outContent.toString(), "\u25CF");
    }

    public void shouldPrintDeadCell() {
        // given
        world.generation.evolvedCells[1][1] = false;
        // when
        printer.printCell(1, 1, world);
        //then
        Assert.assertEquals(outContent.toString(), " ");
    }

    public void testPrintWorld() {
        // given
        world.generation.evolvedCells = new Boolean[][] {
                {false, false, false},
                {false, true, false},
                {false, false, false}
        };
        String expectedString = """
                \u250F\u2501\u2513
                \u2503\u25CF\u2503
                \u2517\u2501\u251B
                """;
        // when
        printer.printWorld(world);
        // then
        Assert.assertEquals(outContent.toString(), expectedString);
    }

    @Test
    public void shouldWait500MillisTillNextWorldPrint() {
        // given
        world.initializeWorld();
        // when
        long start = System.currentTimeMillis();
        printer.printCurrentWorld(game, world);
        long finnish = System.currentTimeMillis();
        long timeElapsed = finnish - start;
        out.println(timeElapsed);
        // then
        Assert.assertTrue(timeElapsed >= 500 && timeElapsed <= 505);
    }
}
