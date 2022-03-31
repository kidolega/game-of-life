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
    static final Cell a = new Cell(true);
    static final Cell d = new Cell(false);

    @BeforeMethod
    private void setUp() {
        world = new World(new int[]{3,3});
        outContent = new ByteArrayOutputStream();
        printer = new Printer(new PrintStream(outContent), world);
        game = new Game(world, printer);
        world.population = new Population(new Cell[3][3]);
    }

    public void shouldPrintAliveCell() {
        // given
        world.population.evolvedGeneration[1][1].state = true;
        // when
        printer.printCell(1, 1);
        //then
        Assert.assertEquals(outContent.toString(), "\u25CF");
    }

    public void shouldPrintDeadCell() {
        // given
        world.population.evolvedGeneration[1][1].state = false;
        // when
        printer.printCell(1, 1);
        //then
        Assert.assertEquals(outContent.toString(), " ");
    }

    public void testPrintWorld() {
        // given
        world.population.evolvedGeneration = new Cell[][] {
                {d, d, d},
                {d, a, d},
                {d, d, d}
        };
        String expectedString = """
                \u250F\u2501\u2513
                \u2503\u25CF\u2503
                \u2517\u2501\u251B
                """;
        // when
        printer.printWorld();
        // then
        Assert.assertEquals(outContent.toString(), expectedString);
    }

    @Test
    public void shouldWait500MillisTillNextWorldPrint() {
        // given
        world.population.initializeGeneration(3,3);
        // when
        long start = System.currentTimeMillis();
        game.waitTillNextEvolution();
        long finnish = System.currentTimeMillis();
        long timeElapsed = finnish - start;
        out.println(timeElapsed);
        // then
        Assert.assertTrue(timeElapsed >= 500 && timeElapsed <= 505);
    }
}
