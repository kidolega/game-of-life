package com.epam.jap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@Test
public class PrinterTest {


    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    Printer printer = new Printer(new PrintStream(outContent));

    @BeforeMethod
    private void setUp() {
        outContent = new ByteArrayOutputStream();
        printer = new Printer(new PrintStream(outContent));
    }

    public void shouldPrintAliveCell() {
        // given
        World world = new World(1, 1);
        world.cells = new Boolean[][] {{true}};
        // when
        printer.printCell(0, 0, world);
        //then
        Assert.assertEquals(outContent.toString(), "\u25CF");
    }

    public void shouldPrintDeadCell() {
        // given
        World world = new World(1, 1);
        world.cells = new Boolean[][] {{false}};
        // when
        printer.printCell(0, 0, world);
        //then
        Assert.assertEquals(outContent.toString(), " ");
    }

    public void testPrintHorizontalBorderSymbol() {
        // given
        // when
        printer.printHorizontalBorder();
        // then
        Assert.assertEquals(outContent.toString(),"\u2501");
    }

    public void testPrintVerticalBorderSymbol() {
        // given
        // when
        printer.printVerticalBorder();
        // then
        Assert.assertEquals(outContent.toString(), "\u2503");
    }

    public void testPrintTopLeftCorner() {
        // given
        // when
        printer.printTopLeftCorner();
        // then
        Assert.assertEquals(outContent.toString(), "\u250F");
    }

    public void testPrintTopRightCorner() {
        // given
        // when
        printer.printTopRightCorner();
        // then
        Assert.assertEquals(outContent.toString(), "\u2513");
    }

    public void testPrintBotLeftCorner() {
        // given
        // when
        printer.printBotLeftCorner();
        // then
        Assert.assertEquals(outContent.toString(), "\u2517");
    }

    public void testPrintBotRightCorner() {
        // given
        // when
        printer.printBotRightCorner();
        // then
        Assert.assertEquals(outContent.toString(), "\u251B");
    }

    public void testPrintWorld() {
    }
}
