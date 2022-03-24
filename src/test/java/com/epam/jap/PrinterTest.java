package com.epam.jap;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PrinterTest {


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    Printer printer = new Printer(new PrintStream(outContent));

    public void testPrintCell() {
    }

    @Test
    public void testPrintHorizontalBorder() {
        // given
        // when
        printer.printHorizontalBorder();
        // then
        Assert.assertEquals(outContent.toString(),"\u2501");
    }

    public void testPrintVerticalBorder() {
    }

    public void testPrintTopLeftCorner() {
    }

    public void testPrintTopRightCorner() {
    }

    public void testPrintBotLeftCorner() {
    }

    public void testPrintBotRightCorner() {
    }

    public void testPrintWorld() {
    }
}
