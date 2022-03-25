package com.epam.jap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@Test
public class PrinterTest {


    static ByteArrayOutputStream outContent;
    static Printer printer;

    @BeforeMethod
    private void setUp() {
        outContent = new ByteArrayOutputStream();
        printer = new Printer(new PrintStream(outContent));
    }

    public void shouldPrintAliveCell() {
        // given
        World world = new World(1, 1);
        world.cells = new Boolean[][]{{true}};
        // when
        printer.printCell(0, 0, world);
        //then
        Assert.assertEquals(outContent.toString(), "\u25CF");
    }

    public void shouldPrintDeadCell() {
        // given
        World world = new World(1, 1);
        world.cells = new Boolean[][]{
                {false}
        };
        // when
        printer.printCell(0, 0, world);
        // then
        Assert.assertEquals(outContent.toString(), " ");
    }

    public void testPrintWorld() {
        // given
        World world = new World(3, 3);
        world.cells = new Boolean[][] {
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
}